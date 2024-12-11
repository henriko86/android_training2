package com.yuruneji.myapplication.data.local.setting

import android.content.Context
import javax.inject.Inject

/**
 * @author henriko
 * @version 1.0
 */
class BaseDataStore @Inject constructor(
    context: Context
) : DataStoreWrapper(context, PREF_NAME) {
    companion object {
        private const val PREF_NAME = "base_datastore"
    }
}
