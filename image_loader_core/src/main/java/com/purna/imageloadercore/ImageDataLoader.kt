package com.purna.imageloadercore

import com.purna.baseandroid.Dispatchers
import com.purna.httpclient.HttpClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-20 as a part of Image-Viewers
 **/

class ImageDataLoader(
    private val dispatchers: Dispatchers,
    private val httpClient: HttpClient
) {
    suspend fun loadImageData(url: String): InputStream = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            try {
                return@withContext httpClient.httpGet(url)
            } catch (e: Throwable) {
                throw e
            }
        }
    }
}