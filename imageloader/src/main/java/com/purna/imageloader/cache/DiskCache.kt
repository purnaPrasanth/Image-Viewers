package com.purna.imageloader.cache

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Purna on 2019-06-26 as a part of Image-Viewers
 **/
class DiskCache(context: Context) : ImageCache {

    private val cacheDir: File = File(context.cacheDir!!.path)

    /**
     * Returns file for the corresponding url
     *
     * @param url
     * @return
     */
    private fun getFile(url: String): File {
        return File(cacheDir, url)
    }

    /**
     * Clears all disk cache.
     */
    override fun clear() {
        val files = cacheDir.listFiles() ?: return
        for (f in files) f.delete()
    }

    override fun get(id: String): Bitmap? {
        val file = getFile(id.hashCode().toString())
        if (!file.exists()) return null
        val fileStream = file.inputStream()
        return BitmapFactory.decodeStream(fileStream)
    }

    override fun put(id: String, bitmap: Bitmap) {
        val file = getFile(id.hashCode().toString())
        if (!file.exists()) file.createNewFile()
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: Exception) {
            e.fillInStackTrace()
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (e: Exception) {
                    e.fillInStackTrace()
                }

            }
        }
    }
}