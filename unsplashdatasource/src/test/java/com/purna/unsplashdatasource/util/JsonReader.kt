package com.purna.unsplashdatasource.util

import java.io.BufferedReader
import java.io.InputStreamReader

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