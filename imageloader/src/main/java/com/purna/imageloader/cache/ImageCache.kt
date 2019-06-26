package com.purna.imageloader.cache

import android.graphics.Bitmap

/**
 * Created by Purna on 2019-06-26 as a part of Image-Viewers
 **/
interface ImageCache {

    /**
     * Get bitmap from memory for the corresponding id.
     *
     * @param id
     * @return
     */
    operator fun get(id: String): Bitmap?

    /**
     * Puts bitmap in memory for the corresponding id.
     *
     * @param id
     * @param bitmap
     */
    fun put(id: String, bitmap: Bitmap)

    /**
     * Clears the Cache
     */
    fun clear()
}