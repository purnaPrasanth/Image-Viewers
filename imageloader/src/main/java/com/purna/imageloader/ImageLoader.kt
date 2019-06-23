package com.purna.imageloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.purna.base.Dispatchers
import com.purna.httpclient.HttpClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class ImageLoader(
    private val httpClient: HttpClient,
    private val dispatchers: Dispatchers
) {

    suspend fun loadImage(url: String, imageView: ImageView) = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            val bitmap = loadImage(url)
            launch(dispatchers.mainDispatcher) { imageView.setImageBitmap(bitmap) }
        }
    }

    suspend fun loadImage(url: String): Bitmap = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            val inputStream = httpClient.httpGet(url)
            BitmapFactory.decodeStream(inputStream)
        }
    }
}