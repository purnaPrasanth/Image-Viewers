package com.purna.unsplashdatasource.services

import com.purna.unsplashdatasource.data.UnSplashImageListModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface UnSplashListServices {

    @GET("/photos")
    fun getPhotos(@Query("page") page: Int, @Query("per_page") perPage: Int): Deferred<List<UnSplashImageListModel>>
}