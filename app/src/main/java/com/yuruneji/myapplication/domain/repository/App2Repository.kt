package com.yuruneji.myapplication.domain.repository

import okhttp3.MultipartBody

/**
 * @author henriko
 * @version 1.0
 */
interface App2Repository {
    suspend fun log(log: MultipartBody.Part): com.yuruneji.myapplication.data.remote.AppResponse
}
