package com.purna.unsplashdatasource

import com.purna.unsplashdatasource.data.PhotoListItem

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/
interface DataSourceContract {
    suspend fun getPhotoUrls(page: Int, perPage: Int): List<PhotoListItem>
}