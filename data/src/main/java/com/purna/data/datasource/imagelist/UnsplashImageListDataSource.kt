package com.purna.data.datasource.imagelist

import com.purna.data.datasource.NetworkCallRunner
import com.purna.data.mappers.imagelist.UnsplashImageListToImageEntity
import com.purna.data.mappers.toListMapper
import com.purna.unsplashdatasource.UnsplashImageListService

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * DataSource Implementation for fetching Images from @see <a href="https://unsplash.com/developers">UnSplash</a> Data Source
 *
 * @param unsplashImageListService Unsplash Image List Service for fetching images from UnSplash
 * @param networkCallRunner [NetworkCallRunner] instance for running network calls
 * @param mapper Mapper for mapping unsplash image data to Image Entity for usage in app
 */

class UnsplashImageListDataSource(
    private val unsplashImageListService: UnsplashImageListService,
    private val networkCallRunner: NetworkCallRunner,
    private val mapper: UnsplashImageListToImageEntity
) : ImageListDataSource {
    override suspend fun getImageList(page: Int, perPage: Int) =
        networkCallRunner.executeForResponse(mapper.toListMapper()) {
            unsplashImageListService.getPhotoUrls(page, perPage)
        }

}