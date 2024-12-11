package com.yuruneji.myapplication.data.remote

import com.yuruneji.myapplication.domain.model.AppResponseModel

/**
 * @author henriko
 * @version 1.0
 */
data class AppResponse(
    val result: Int,
    val name: String,
)

fun AppResponse.toConvert(): AppResponseModel {
    return AppResponseModel(
        result = result,
        name = name,
    )
}
