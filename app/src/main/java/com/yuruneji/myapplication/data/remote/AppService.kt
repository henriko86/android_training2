package com.yuruneji.myapplication.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @author henriko
 * @version 1.0
 */
interface AppService {
    @POST("json/face")
    suspend fun faceAuth(@Body request: AppRequest): AppResponse

    @POST("json/card")
    suspend fun cardAuth(@Body request: AppRequest): AppResponse
}
