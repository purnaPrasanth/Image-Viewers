package com.purna.httpclient

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-20 as a part of Image-Viewers
 **/
public class HttpClient(private val networkDispatcher: CoroutineDispatcher) {

    suspend fun getValues(url: URL): InputStream = coroutineScope {
        withContext(networkDispatcher) {
            try {
                return@withContext url.openStream()
            } catch (exception: Exception) {
                throw exception
            }
        }
    }
}