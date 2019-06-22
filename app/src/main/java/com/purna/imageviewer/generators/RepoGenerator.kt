package com.purna.imageviewer.generators

import com.purna.baseandroid.creational.BaseGenerator
import com.purna.baseandroid.creational.single
import com.purna.data.datasource.NetworkCallRunner
import com.purna.data.datasource.imagelist.ImageListDataSource
import com.purna.data.datasource.imagelist.UnsplashImageListDataSource
import com.purna.data.mappers.imagelist.UnsplashImageListToImageEntity
import com.purna.data.repo.ImagesListRepo
import com.purna.unsplashdatasource.UnsplashImageListService

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

val networkRunner: BaseGenerator<NetworkCallRunner> = single {
    NetworkCallRunner()
}

val unsplashImageListToImageEntity: BaseGenerator<UnsplashImageListToImageEntity> = single {
    UnsplashImageListToImageEntity()
}

private val unsplashService: BaseGenerator<UnsplashImageListService> = single {
    UnsplashImageListService(httpClient.getInstance(), appDispatchersProvider.getInstance())
}

private val unsplashDataSource: BaseGenerator<ImageListDataSource> = single {
    UnsplashImageListDataSource(
        unsplashService.getInstance(),
        networkRunner.getInstance(),
        unsplashImageListToImageEntity.getInstance()
    )
}

val imageListRepo: BaseGenerator<ImagesListRepo> = single {
    ImagesListRepo(unsplashDataSource.getInstance(), appDispatchersProvider.getInstance())
}