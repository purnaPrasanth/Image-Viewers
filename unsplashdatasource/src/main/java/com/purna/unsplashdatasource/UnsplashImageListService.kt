package com.purna.unsplashdatasource

import com.purna.baseandroid.Dispatchers
import com.purna.httpclient.HttpClient
import com.purna.unsplashdatasource.data.PhotoListItem
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.list

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

class UnsplashImageListService(
    private val httpClient: HttpClient,
    private val dispatchers: Dispatchers
) {
    suspend fun getPhotoUrls(page: Int, perPage: Int): List<PhotoListItem> = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            val data = httpClient.httpGet(relativePath = "/photos") {
                listOf(
                    Pair("page", page.toString()),
                    Pair("per_page", perPage.toString()),
                    Pair("client_id", "f7af843e895c61a1f3434e6823743a08fb08ace46e203353f539a30eeb2a67e7")
                )
            }
            fromJson(PhotoListItem.serializer().list, data)
        }
    }
}