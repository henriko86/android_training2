package com.yuruneji.myapplication.domain.usecase

import com.yuruneji.myapplication.data.remote.toConvert
import com.yuruneji.myapplication.domain.model.AppRequestModel
import com.yuruneji.myapplication.domain.model.AppResponseModel
import com.yuruneji.myapplication.domain.model.toConvert
import com.yuruneji.myapplication.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
class CardAuthUseCase @Inject constructor(
    private val repository: AppRepository
) {
    operator fun invoke(request: AppRequestModel): Flow<com.yuruneji.myapplication.common.response.FaceAuthResponse<AppRequestModel, AppResponseModel>> = flow {
        try {
            emit(com.yuruneji.myapplication.common.response.FaceAuthResponse.Loading())
            val data = repository.cardAuth(request.toConvert()).toConvert()
            emit(com.yuruneji.myapplication.common.response.FaceAuthResponse.Success(request, data))
        } catch (e: Exception) {
            emit(com.yuruneji.myapplication.common.response.FaceAuthResponse.Failure(request, e))
        }
    }
}
