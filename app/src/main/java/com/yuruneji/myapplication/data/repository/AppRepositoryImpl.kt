package com.yuruneji.myapplication.data.repository

import com.yuruneji.myapplication.data.remote.AppRequest
import com.yuruneji.myapplication.data.remote.AppResponse
import com.yuruneji.myapplication.data.remote.AppService
import com.yuruneji.myapplication.domain.repository.AppRepository
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
class AppRepositoryImpl @Inject constructor(
    private val api: AppService
) : AppRepository {
    override suspend fun faceAuth(request: AppRequest): AppResponse {
        return api.faceAuth(request)
    }

    override suspend fun cardAuth(request: AppRequest): AppResponse {
        return api.cardAuth(request)
    }
}
