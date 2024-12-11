package com.yuruneji.myapplication.data.remote

import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * @author henriko
 * @version 1.0
 */
interface App2Service {
    @POST("log")
    @Multipart
    suspend fun log(@Part log: MultipartBody.Part): AppResponse
}
