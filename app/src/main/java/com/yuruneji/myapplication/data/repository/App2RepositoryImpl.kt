package com.yuruneji.myapplication.data.repository

import com.yuruneji.myapplication.data.remote.App2Service
import com.yuruneji.myapplication.data.remote.AppResponse
import com.yuruneji.myapplication.domain.repository.App2Repository
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
class App2RepositoryImpl @Inject constructor(
    private val api: App2Service
) : App2Repository {
    override suspend fun log(log: MultipartBody.Part): AppResponse {
        return api.log(log)
    }
}
