package com.yuruneji.myapplication.domain.repository

/**
 * @author henriko
 * @version 1.0
 */
interface AppRepository {
    suspend fun faceAuth(request: com.yuruneji.myapplication.data.remote.AppRequest): com.yuruneji.myapplication.data.remote.AppResponse
    suspend fun cardAuth(request: com.yuruneji.myapplication.data.remote.AppRequest): com.yuruneji.myapplication.data.remote.AppResponse
}
