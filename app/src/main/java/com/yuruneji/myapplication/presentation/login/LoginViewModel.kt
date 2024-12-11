package com.yuruneji.myapplication.presentation.login

import androidx.lifecycle.ViewModel
import com.yuruneji.myapplication.data.local.setting.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pref: AppPreferences
) : ViewModel() {
    //
}
