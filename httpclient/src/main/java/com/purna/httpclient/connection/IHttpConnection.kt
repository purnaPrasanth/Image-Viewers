package com.purna.httpclient.connection

import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/
interface IHttpConnection {
    suspend fun get(url: URL): String
    suspend fun getStream(url: URL): InputStream
}