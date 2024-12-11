package com.yuruneji.myapplication.domain.usecase

import com.yuruneji.myapplication.domain.repository.App2Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
class LogUploadUseCase @Inject constructor(
    private val repository: App2Repository
) {
    operator fun invoke(log: MultipartBody.Part): Flow<com.yuruneji.myapplication.common.response.DeviceResponse<com.yuruneji.myapplication.data.remote.AppResponse>> = flow {
        try {
            emit(com.yuruneji.myapplication.common.response.DeviceResponse.Loading())
            val data = repository.log(log)
            emit(com.yuruneji.myapplication.common.response.DeviceResponse.Success(data))
        } catch (e: Exception) {
            emit(com.yuruneji.myapplication.common.response.DeviceResponse.Failure(e))
        }
    }
}
