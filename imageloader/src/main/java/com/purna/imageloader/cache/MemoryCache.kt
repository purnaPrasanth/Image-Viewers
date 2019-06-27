package com.purna.imageloader.cache

import android.graphics.Bitmap
import java.util.*

/**
 * Created by Purna on 2019-06-26 as a part of Image-Viewers
 **/
class MemoryCache : ImageCache {

    private val lock = Any()
    private val cacheMap = Collections.synchronizedMap(
        LinkedHashMap<String, Bitmap>(10, 1.5f, true)
    )
    private var size: Long = 0 //current allocated size
    //use 25% of available heap size
    private val limit: Long = Runtime.getRuntime().maxMemory() / 4 //max memory in bytes

    override fun get(id: String): Bitmap? {
        synchronized(lock) {
            try {
                return if (!cacheMap.containsKey(id)) {
                    null
                } else cacheMap[id]
            } catch (ex: NullPointerException) {
                ex.printStackTrace()
                return null
            }

        }
    }

    override fun put(id: String, bitmap: Bitmap) {
        try {
            if (cacheMap.containsKey(id)) {
                size -= getBitmapSize(cacheMap[id])
            }
            cacheMap[id] = bitmap
            size += getBitmapSize(bitmap)
            verifySize()
        } catch (th: Throwable) {
            th.printStackTrace()
        }

    }

    /**
     * Checks if currently used size hasn't exceeded the defined limit.
     * Cleans the least recently accessed file if size exceed limit to free up space.
     */
    private fun verifySize() {
        if (size > limit) {
            val iter = cacheMap.entries.iterator()//least recently accessed item will be the first one iterated
            while (iter.hasNext()) {
                val entry = iter.next()
                size -= getBitmapSize(entry.value)
                iter.remove()
                if (size <= limit) {
                    break
                }
            }
        }
    }

    override fun clear() {
        try {
            cacheMap.clear()
            size = 0
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        }

    }

    private fun getBitmapSize(bitmap: Bitmap?): Long {
        return if (bitmap == null) {
            0
        } else (bitmap.rowBytes * bitmap.height).toLong()
    }
}