package com.purna.unsplashdatasource

import com.purna.unsplashdatasource.data.UnSplashImageUrls
import com.purna.unsplashdatasource.data.UnSplashImageListModel
import com.purna.unsplashdatasource.utils.readJsonFromResource
import kotlinx.serialization.list
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
@RunWith(JUnit4::class)
class SerializationHelperTest {

    lateinit var jsonData: String
    lateinit var listObjects: List<UnSplashImageListModel>

    @Before
    fun before() {
        jsonData = readJsonFromResource(javaClass)
        listObjects = (1..20).map {
            UnSplashImageListModel(
                id = "abcd",
                urls = UnSplashImageUrls(
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
    fun testParseJson() {
        val listData = fromJson(UnSplashImageListModel.serializer().list, jsonData, emptyList())
        assert(listData.isNotEmpty())
    }

    @Test
    fun testStringify() {
        val encoded = toJson(UnSplashImageListModel.serializer().list, listObjects)
        assert(encoded != null)
    }

    @Test
    fun testStringifyWithNull() {
        val encoded = toJson(UnSplashImageListModel.serializer().list, null)
        assert(encoded == null)
    }

    @Test
    fun testParseJsonWithEmpty() {
        val empty = emptyList<UnSplashImageListModel>()
        val encoded = fromJson(UnSplashImageListModel.serializer().list, "", empty)
        assert(encoded == empty)
    }

    @Test
    fun testParseJsonWithNull() {
        val empty = emptyList<UnSplashImageListModel>()
        val encoded = fromJson(UnSplashImageListModel.serializer().list, null, empty)
        assert(encoded == empty)
    }
}