package com.purna.data.mappers.imagelist

import com.purna.data.entity.ImageListEntity
import com.purna.data.mappers.Mapper
import com.purna.unsplashdatasource.data.UnSplashImageListModel

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Mapper for map [UnSplashImageListModel] to [ImageListEntity]
 */

class UnsplashImageListToImageEntity : Mapper<UnSplashImageListModel, ImageListEntity> {
    override suspend fun map(from: UnSplashImageListModel) = ImageListEntity(
        id = from.id,
        imageUrl = from.urls.small
    )
}