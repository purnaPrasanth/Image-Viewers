package com.purna.imageviewer

import android.app.Application
import android.content.Context

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
class ImageViewerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: ImageViewerApplication

        val applicationContext: Context
            get() = instance
    }
}