package com.purna.unsplashdatasource

import com.purna.httpclient.HttpClient
import com.purna.httpclient.exception.BadRequestException
import com.purna.httpclient.exception.HttpException
import com.purna.httpclient.exception.UnAuthorizedException
import com.purna.unsplashdatasource.data.UnSplashImageListModel
import com.purna.unsplashdatasource.utils.readJsonFromResource
import kotlinx.coroutines.*
import kotlinx.serialization.list
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
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

    lateinit var mockWebServer: MockWebServer

    lateinit var jsonData: String

    private val dispatchers: com.purna.base.Dispatchers by lazy {
        com.purna.base.Dispatchers(
            ioDispatcher = Dispatchers.IO,
            commonDispatcher = Dispatchers.Default,
            mainDispatcher = ThreadPoolExecutor(
                1, 1,
                0L, TimeUnit.MILLISECONDS,
                LinkedBlockingQueue<Runnable>()
            ).asCoroutineDispatcher()
        )
    }

    @Before
    fun before() {
        jsonData = readJsonFromResource(javaClass)
        mockWebServer = MockWebServer()
        httpClient = HttpClient.HttpClientBuilder(
            mockWebServer.url("").toString(),
            connectionTimeOut = 15000,
            readTimeOut = 15000,
            dispatchers = dispatchers
        ).build()

        unsplashImageListService = UnsplashImageListService(httpClient, dispatchers)
    }

    @After
    fun after() {
        job.cancel()
        mockWebServer.shutdown()
    }

    @Test
    fun testGetListImages() {
        mockWebServer.enqueue(MockResponse().setBody(jsonData))

        runBlocking {
            val first = unsplashImageListService.getPhotoUrls(0, 30)
            val actualData = fromJson(UnSplashImageListModel.serializer().list, jsonData, emptyList())
            first.forEachIndexed { index, photoListItem ->
                if (actualData[index].id != photoListItem.id) assert(false)
            }
            assert(true)
        }
    }

    @Test
    fun testPageAndperPageParams() {
        mockWebServer.enqueue(MockResponse().setBody(jsonData))

        runBlocking {
            val randomPage = (0..Integer.MAX_VALUE).random()
            val randomPerPage = (0..Integer.MAX_VALUE).random()
            unsplashImageListService.getPhotoUrls(randomPage, randomPerPage)

            val request = mockWebServer.takeRequest()

            assert("/photos?page=$randomPage&per_page=$randomPerPage&client_id=$clientId" == request.path)
        }
    }

    @Test
    fun testUnAuthenticated() {
        mockWebServer.enqueue(MockResponse().setResponseCode(401).setBody(""))

        runBlocking {
            try {
                unsplashImageListService.getPhotoUrls(0, 300)
            } catch (exception: HttpException) {
                assert(exception is UnAuthorizedException)
            }
        }
    }

    @Test
    fun testBadRequest() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody(""))

        runBlocking {
            try {
                unsplashImageListService.getPhotoUrls(0, 300)
            } catch (exception: HttpException) {
                assert(exception is BadRequestException)
            }
        }
    }

    @Test
    fun testForEmptyResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(""))

        runBlocking {
            val images = unsplashImageListService.getPhotoUrls(0, 10)
            assert(images.isEmpty())
        }
    }
}