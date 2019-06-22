package com.purna.data.datasource.imagelist

import com.purna.data.datasource.NetworkCallRunner
import com.purna.data.datasource.Result
import com.purna.data.entity.ImageListEntity
import com.purna.data.mappers.imagelist.UnsplashImageListToImageEntity
import com.purna.data.mappers.toListMapper
import com.purna.unsplashdatasource.UnsplashImageListService

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

class UnsplashImageListDataSource(
    private val unsplashImageListService: UnsplashImageListService,
    private val networkCallRunner: NetworkCallRunner,
    private val mapper: UnsplashImageListToImageEntity
) : ImageListDataSource {
    override suspend fun getImageList(page: Int, perPage: Int): Result<List<ImageListEntity>> {
        return networkCallRunner.executeForResponse(mapper.toListMapper()) {
            unsplashImageListService.getPhotoUrls(page, perPage)
        }
    }

}