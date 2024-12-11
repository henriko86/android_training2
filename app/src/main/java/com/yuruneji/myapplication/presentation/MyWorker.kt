package com.yuruneji.myapplication.presentation

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author henriko
 * @version 1.0
 */
class MyWorker constructor(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            val today = LocalDateTime.now()
            val fileName = "${today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}.log"
            // val logFile = File(context.filesDir, fileName)
            val logFile = File(fileName)

            // logUpload(fileName, logFile)
        }

        return Result.success()
    }

    /**
     * ログアップロード
     * @param fileName ログファイル名
     * @param file     ログファイル
     */
    // private suspend fun logUpload(fileName: String, file: File) {
    //     withContext(Dispatchers.IO) {
    //         val log = MultipartBody.Part.createFormData(
    //             "LogFile", fileName, file.asRequestBody("text/plain".toMediaType())
    //         )
    //
    //         Timber.d("ログアップロード 開始 [" + Thread.currentThread().name + "]")
    //
    //         val job = launch {
    //
    //             repeat(6) {
    //                 var result1 = false
    //
    //                 logUploadUseCase(log).onEach { result ->
    //                     when (result) {
    //                         is DeviceResponse.Success -> {
    //                             // Timber.d("${result.res}")
    //                             Timber.d("ログアップロード!!!! [" + Thread.currentThread().name + "]")
    //
    //                             result1 = true
    //                         }
    //
    //                         is DeviceResponse.Failure -> {
    //                             Timber.w("${result.error}")
    //                             Timber.d("ログアップロードエラー [" + Thread.currentThread().name + "]")
    //                         }
    //
    //                         is DeviceResponse.Loading -> {
    //                             Timber.d("ログアップロード 読み込み中..... [" + Thread.currentThread().name + "]")
    //                         }
    //                     }
    //                 }.launchIn(this)
    //
    //                 if (result1) return@launch
    //
    //                 Timber.d("リピート $it")
    //
    //                 delay(10000)
    //             }
    //         }
    //         job.join()
    //
    //         Timber.d("ログアップロード 終了 [" + Thread.currentThread().name + "]")
    //
    //     }
    // }
}
