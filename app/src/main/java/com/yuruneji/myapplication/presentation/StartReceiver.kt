package com.yuruneji.myapplication.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class StartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Timber.i("onReceive: ACTION_BOOT_COMPLETED")
        }
    }
}
