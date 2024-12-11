package com.yuruneji.myapplication.domain.model

/**
 * @author henriko
 * @version 1.0
 */
data class AppRequestModel(
    val img: String = "",
    val card: String = ""
)

fun AppRequestModel.toConvert(): com.yuruneji.myapplication.data.remote.AppRequest {
    return com.yuruneji.myapplication.data.remote.AppRequest(
        img = img,
        card = card
    )
}
