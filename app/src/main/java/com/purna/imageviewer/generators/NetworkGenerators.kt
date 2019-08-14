package com.purna.imageviewer.generators

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.httpclient.HttpClient
import com.purna.imageloader.ImageLoader
import com.purna.imageloader.ImageLoaderBuilder
import com.purna.imageviewer.ImageViewerApplication
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.internal.cache.DiskLruCache
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Genrators for Network Layer instances
 */

val httpClient: BaseGenerator<HttpClient> = single {
    HttpClient.HttpClientBuilder(
        "https://api.unsplash.com/",
        appDispatchersProvider.getInstance(),
        connectionTimeOut = 15000,
        readTimeOut = 15000
    ).build()
}

val imageLoader: BaseGenerator<ImageLoader> = single {
    ImageLoaderBuilder(
        ImageViewerApplication.applicationContext,
        appDispatchersProvider.getInstance(),
        httpClient.getInstance()
    ).build()
}

val coilImageLoader: BaseGenerator<coil.ImageLoader> = single {
    coil.ImageLoaderBuilder(ImageViewerApplication.applicationContext)
        .availableMemoryPercentage(0.8)
        .bitmapPoolPercentage(0.8)
        .crossfade(true)
        .dispatcher(appDispatchersProvider.getInstance().ioDispatcher)
        .okHttpClient(okHttpClient.getInstance())
        .build()
}

val okHttpClient: BaseGenerator<OkHttpClient> = single {
    OkHttpClient.Builder()
        .callTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addNetworkInterceptor(StethoInterceptor())
        .dispatcher(Dispatcher(appExecutorsProvider.getInstance().ioExecutor as ExecutorService).apply {
            maxRequests = 10
            maxRequestsPerHost = 10
        })
        .build()
}