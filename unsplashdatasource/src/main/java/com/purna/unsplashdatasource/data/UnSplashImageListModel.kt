package com.purna.unsplashdatasource.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Purna on 2019-06-21 as a part of Image-Viewers
 **/

/**
 * Image Data Model for UnSplashImageList
 */

@Serializable
data class UnSplashImageListModel(
    @SerialName("id") val id: String,
    @SerialName("urls") val urls: UnSplashImageUrls
)

/**
 * Data Model for UnSPlash Image Url
 */
@Serializable
data class UnSplashImageUrls(
    @SerialName("raw") val raw: String,
    @SerialName("full") val full: String,
    @SerialName("regular") val regular: String,
    @SerialName("small") val small: String,
    @SerialName("thumb") val thumb: String
)