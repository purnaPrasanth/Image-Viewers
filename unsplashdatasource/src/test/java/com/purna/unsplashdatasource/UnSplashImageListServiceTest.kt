package com.purna.unsplashdatasource

import com.purna.baseandroid.Dispatchers
import com.purna.httpclient.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.coroutines.CoroutineContext

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

@RunWith(JUnit4::class)
class UnSplashImageListServiceTest : CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = dispatchers.ioDispatcher + job

    lateinit var httpClient: HttpClient

    lateinit var unsplashImageListService: UnsplashImageListService

    private val dispatchers: Dispatchers by lazy {
        Dispatchers(
            ioDispatcher = kotlinx.coroutines.Dispatchers.IO,
            commonDispatcher = kotlinx.coroutines.Dispatchers.Default,
            mainDispatcher = newSingleThreadContext("main")
        )
    }

    @Before
    fun before() {
        httpClient = HttpClient.HttpClientBuilder(
            "https://api.unsplash.com",
            connectionTimeOut = 15000,
            readTimeOut = 15000,
            dispatchers = dispatchers
        ).build()

        unsplashImageListService = UnsplashImageListService(httpClient, dispatchers)
    }

    @After
    fun after() {
        job.cancel()
    }

    @Test
    fun getListImages() {
        val listOfImages = runBlocking {
            unsplashImageListService.getPhotoUrls(0, 30)
        }

        assert(listOfImages.isNotEmpty())
    }
}