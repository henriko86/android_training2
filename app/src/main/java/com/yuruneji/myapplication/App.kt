package com.yuruneji.myapplication

import android.app.Application
import com.yuruneji.myapplication.domain.usecase.LogFile
import com.yuruneji.myapplication.domain.usecase.LogTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var logFile: LogFile

    override fun onCreate() {
        super.onCreate()

        // ログ出力設定
        Timber.plant(LogTree(context = this, logFile = logFile))
    }

    // /**
    //  * LogTree
    //  * @param context
    //  * @param logFile
    //  */
    // class LogTree(private val context: Context, private val logFile: LogFile) : Timber.DebugTree() {
    //     override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
    //         super.log(priority, tag, message, t)
    //         logFile.postLog(context, priority, tag, message, t)
    //     }
    // }
}
