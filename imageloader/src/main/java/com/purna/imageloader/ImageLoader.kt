package com.purna.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.purna.base.Dispatchers
import com.purna.httpclient.HttpClient
import com.purna.imageloader.cache.DiskCache
import com.purna.imageloader.cache.ImageCache
import com.purna.imageloader.cache.MemoryCache
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.*

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class ImageLoader(
    private val httpClient: HttpClient,
    private val dispatchers: Dispatchers,
    private val memoryCache: ImageCache,
    private val diskCache: ImageCache
) {
    private val mapImages = Collections.synchronizedMap(WeakHashMap<ImageView, String>())

    /**
     * Load Image Into an [ImageView]
     */
    suspend fun loadImage(url: String, imageView: ImageView) = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            mapImages[imageView] = url
            val bitmap = memoryCache[url]
            if (bitmap != null) {
                launch(dispatchers.mainDispatcher) { imageView.setImageBitmap(bitmap) }
            } else {
                queueImage(url, imageView)
            }
        }
    }

    /**
     * Add load image task to the queue.
     *
     * @param url
     * @param imageView
     */
    private suspend fun queueImage(url: String, imageView: ImageView) = coroutineScope {
        val imageItem = ImageItem(url, imageView)
        if (imageViewReused(imageItem)) {
            return@coroutineScope
        }

        val bmp = loadImage(imageItem.url)
        if (bmp != null) memoryCache.put(imageItem.url, bmp)

        launch(dispatchers.mainDispatcher) {
            if (imageViewReused(imageItem)) {
                return@launch
            }
            imageItem.imageView.setImageBitmap(bmp)
        }
    }

    /**
     * Load Image from url
     */
    private suspend fun loadImage(url: String): Bitmap? = coroutineScope {

        // Check for Image in DiskCache
        val diskCacheCopy = diskCache[url]

        // If Found Return It Copy From Disk Cache
        if (diskCacheCopy != null) {
            return@coroutineScope diskCacheCopy
        }
        val inputStream = httpClient.httpGet(url)
        return@coroutineScope loadImage(url, inputStream)
    }

    /**
     * Convert an [InputStream] into a Bitmap
     */
    private suspend fun loadImage(url: String, inputStream: InputStream): Bitmap = coroutineScope {
        val bitmap = BitmapFactory.decodeStream(inputStream)
        // store the bitmap in disk cache
        diskCache.put(url, bitmap)
        return@coroutineScope bitmap
    }

    /**
     * Check if [ImageView] is used with a different url
     */
    private fun imageViewReused(imageItem: ImageItem): Boolean {
        val tag = mapImages[imageItem.imageView]
        return tag == null || tag != imageItem.url
    }

    private inner class ImageItem internal constructor(internal val url: String, internal val imageView: ImageView)
}

class ImageLoaderBuilder(
    private val context: Context,
    private val dispatchers: Dispatchers,
    private val httpClient: HttpClient
) {
    fun build(): ImageLoader {
        return ImageLoader(httpClient, dispatchers, MemoryCache(), DiskCache(context))
    }
}