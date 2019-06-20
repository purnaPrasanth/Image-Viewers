package com.purna.imageloadercore

import com.purna.httpclient.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-20 as a part of Image-Viewers
 **/

public class ImageDataLoader(
    private val dispatcher: CoroutineDispatcher,
    private val httpClient: HttpClient
) {
    public suspend fun loadImageData(url: URL): InputStream = coroutineScope {
        withContext(dispatcher) {
            try {
                return@withContext httpClient.getValues(url)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}