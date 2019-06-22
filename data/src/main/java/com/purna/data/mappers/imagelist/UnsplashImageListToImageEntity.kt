package com.purna.data.mappers.imagelist

import com.purna.data.entity.ImageListEntity
import com.purna.data.mappers.Mapper
import com.purna.unsplashdatasource.data.PhotoListItem

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class UnsplashImageListToImageEntity : Mapper<PhotoListItem, ImageListEntity> {
    override suspend fun map(from: PhotoListItem) = ImageListEntity(
        id = from.id,
        imageUrl = from.urls.regular
    )
}