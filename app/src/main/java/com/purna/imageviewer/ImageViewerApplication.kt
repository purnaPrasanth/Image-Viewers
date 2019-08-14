package com.purna.imageviewer

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
class ImageViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        _instance = this
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        private lateinit var _instance: ImageViewerApplication

        val application: ImageViewerApplication
            get() = _instance

        val applicationContext: Context
            get() = _instance
    }
}