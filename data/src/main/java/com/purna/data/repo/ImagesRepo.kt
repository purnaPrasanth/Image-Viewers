package com.purna.data.repo

import com.purna.base.Dispatchers
import com.purna.data.datasource.imagelist.ImageListDataSource
import kotlinx.coroutines.coroutineScope

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Repo for List Of Images
 * @param unsplashDataSource A [ImageListDataSource] for fetching images from
 * @param dispatchers [Dispatchers] for performing various operations
 */

class ImagesListRepo(
    private val unsplashDataSource: ImageListDataSource,
    private val dispatchers: Dispatchers
) {
    suspend fun getImageList(page: Int, perPage: Int) = coroutineScope {
        unsplashDataSource.getImageList(page, perPage)
    }
}