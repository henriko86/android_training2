package com.yuruneji.myapplication.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author henriko
 * @version 1.0
 */
@JsonClass(generateAdapter = true)
data class AppRequest(
    @Json(name = "img") val img: String = "",
    @Json(name = "card") val card: String = "",
)
