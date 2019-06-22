package com.purna.imageviewer.generators

import com.purna.baseandroid.creational.BaseGenerator
import com.purna.baseandroid.creational.single
import com.purna.httpclient.HttpClient
import com.purna.imageloader.ImageLoader

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

val httpClient: BaseGenerator<HttpClient> = single {
    HttpClient.HttpClientBuilder(
        "https://api.unsplash.com",
        appDispatchersProvider.getInstance(),
        connectionTimeOut = 15000,
        readTimeOut = 15000
    ).build()
}

val imageLoader: BaseGenerator<ImageLoader> = single {
    ImageLoader(
        httpClient = httpClient.getInstance(),
        dispatchers = appDispatchersProvider.getInstance()
    )
}