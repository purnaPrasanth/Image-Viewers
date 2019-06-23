package com.purna.httpclient.util

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

fun readJsonFromResource(javaClass: Class<Any>): String {
    val classLoader = javaClass.classLoader

    classLoader.getResourceAsStream("ListOfPhotos.json")!!.use { inputStream ->
        val reader = BufferedReader(InputStreamReader(inputStream))

        val strBuilder = StringBuilder()

        reader.lines().forEach {
            strBuilder.append(it.trim())
        }

        reader.close()

        return strBuilder.toString()
    }
}