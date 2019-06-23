package com.purna.httpclient.connection

import java.io.InputStream
import java.net.URL

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/
interface IHttpConnection {
    fun get(url: URL): String
    fun getStream(url: URL): InputStream
}