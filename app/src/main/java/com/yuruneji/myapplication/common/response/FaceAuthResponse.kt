package com.yuruneji.myapplication.common.response

/**
 * 通信レスポンス
 * @author henriko
 * @version 1.0
 */
sealed class FaceAuthResponse<T1, T2>(
    val request: T1? = null,
    val response: T2? = null,
    val error: Throwable? = null
) {
    class Success<T1, T2>(request: T1, response: T2) : FaceAuthResponse<T1, T2>(request = request, response = response)
    class Failure<T1, T2>(request: T1, error: Throwable) : FaceAuthResponse<T1, T2>(request = request, error = error)
    class Loading<T1, T2> : FaceAuthResponse<T1, T2>()
}
