package com.purna.data.repo

import com.purna.base.Dispatchers
import com.purna.data.datasource.imagelist.ImageListDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class ImagesListRepo(
    private val unsplashDataSource: ImageListDataSource,
    private val dispatchers: Dispatchers
) {
    suspend fun getImageList(page: Int, perPage: Int) = coroutineScope {
            unsplashDataSource.getImageList(page, perPage)
    }
}