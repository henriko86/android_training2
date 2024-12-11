package com.yuruneji.myapplication.domain.usecase

import android.content.Context
import timber.log.Timber

/**
 * @author henriko
 * @version 1.0
 */
class LogTree(private val context: Context, private val logFile: LogFile) : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)
        logFile.postLog(context, priority, tag, message, t)
    }
}
