package com.purna.imageviewer.generators

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.httpclient.HttpClient
import com.purna.imageloader.ImageLoader
import com.purna.imageloader.ImageLoaderBuilder
import com.purna.imageviewer.ImageViewerApplication

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