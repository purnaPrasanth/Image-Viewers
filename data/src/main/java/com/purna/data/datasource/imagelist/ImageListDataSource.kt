package com.purna.data.datasource.imagelist

import com.purna.data.datasource.Result
import com.purna.data.entity.ImageListEntity

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Base class for Image List Data Sources should be implemented by multiple source if multiple sources of Images introduced
 */
interface ImageListDataSource {
    /**
     * To get a list of images from a [ImageListDataSource]
     */
    suspend fun getImageList(page: Int, perPage: Int): Result<List<ImageListEntity>>
}