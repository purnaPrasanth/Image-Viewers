package com.purna.imageviewer.generators

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.data.datasource.NetworkCallRunner
import com.purna.data.datasource.imagelist.ImageListDataSource
import com.purna.data.datasource.imagelist.UnsplashImageListDataSource
import com.purna.data.mappers.imagelist.UnsplashImageListToImageEntity
import com.purna.data.repo.ImagesListRepo
import com.purna.unsplashdatasource.UnSplashRetrofitProvider
import com.purna.unsplashdatasource.UnsplashImageListService

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Genrators for Repo instances
 */

val networkRunner: BaseGenerator<NetworkCallRunner> = single {
    NetworkCallRunner()
}

val unsplashImageListToImageEntity: BaseGenerator<UnsplashImageListToImageEntity> = single {
    UnsplashImageListToImageEntity()
}

private val unSplashRetrofitProvider: BaseGenerator<UnSplashRetrofitProvider> = single {
    UnSplashRetrofitProvider(okHttpClient.getInstance())
}

private val unsplashDataService: BaseGenerator<UnsplashImageListService> = single {
    UnsplashImageListService(
        unSplashRetrofitProvider.getInstance().unsplashImageListService,
        appDispatchersProvider.getInstance()
    )
}

private val unsplashDataSource: BaseGenerator<ImageListDataSource> = single {
    UnsplashImageListDataSource(
        unsplashDataService.getInstance(),
        networkRunner.getInstance(),
        unsplashImageListToImageEntity.getInstance()
    )
}

val imageListRepo: BaseGenerator<ImagesListRepo> = single {
    ImagesListRepo(unsplashDataSource.getInstance(), appDispatchersProvider.getInstance())
}