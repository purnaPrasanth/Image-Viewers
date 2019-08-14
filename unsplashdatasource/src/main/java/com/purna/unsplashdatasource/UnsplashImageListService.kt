package com.purna.unsplashdatasource

import com.purna.base.Dispatchers
import com.purna.httpclient.HttpClient
import com.purna.unsplashdatasource.data.UnSplashImageListModel
import com.purna.unsplashdatasource.services.UnSplashListServices
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.list

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

/**
 * Service to get List Of Images from UnSplash API
 */
class UnsplashImageListService(
    private val imageListService: UnSplashListServices,
    private val dispatchers: Dispatchers
) {
    suspend fun getPhotoUrls(page: Int, perPage: Int): List<UnSplashImageListModel> = coroutineScope {
        withContext(dispatchers.ioDispatcher) {
            imageListService.getPhotos(page, perPage).await()
        }
    }
}