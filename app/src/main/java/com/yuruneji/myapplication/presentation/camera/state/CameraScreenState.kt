package com.yuruneji.myapplication.presentation.camera.state

import com.yuruneji.myapplication.domain.model.AppRequestModel
import com.yuruneji.myapplication.domain.model.AppResponseModel

/**
 * @author henriko
 * @version 1.0
 */
data class CameraScreenState(
    /**  */
    val isLoading: Boolean = false,
    /**  */
    val request: AppRequestModel? = null,
    /**  */
    val response: AppResponseModel? = null,
    /**  */
    val error: Throwable? = null,
)
