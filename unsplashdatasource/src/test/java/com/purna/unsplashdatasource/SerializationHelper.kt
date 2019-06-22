package com.purna.unsplashdatasource

import com.purna.unsplashdatasource.data.ImageUrls
import com.purna.unsplashdatasource.data.PhotoListItem
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
@RunWith(JUnit4::class)
class SerializationHelperTest {

    lateinit var jsonData: String
    lateinit var listObjects: List<PhotoListItem>

    @Before
    fun before() {
        jsonData = readJsonFromResource()
        listObjects = (1..20).map {
            PhotoListItem(
                id = "abcd",
                urls = ImageUrls(
                    raw = "https://images.unsplash.com/photo-1558980394-34764db076b4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ",
                    full = "https://images.unsplash.com/photo-1558980394-34764db076b4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ",
                    regular = "https://images.unsplash.com/photo-1558980394-34764db076b4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ",
                    small = "https://images.unsplash.com/photo-1558980394-34764db076b4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ",
                    thumb = "https://images.unsplash.com/photo-1558980394-34764db076b4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ"
                )
            )
        }
    }

    @Test
    fun testToJson() {
        val listData = fromJson(PhotoListItem.serializer().list, jsonData)
        assert(listData.isNotEmpty())
    }

    @Test
    fun testFromJson() {
        val encoded = toJson(PhotoListItem.serializer().list, listObjects)
        assert(encoded.isNotEmpty())
    }


    private fun readJsonFromResource(): String {
        val classLoader = javaClass.classLoader

        classLoader.getResourceAsStream("ListOfPhotos.json")!!.use { inputStream ->
            val reader = BufferedReader(InputStreamReader(inputStream))

            val strBuilder = StringBuilder()

            reader.lines().forEach {
                strBuilder.append(it)
            }

            reader.close()

            return strBuilder.toString()
        }
    }
}