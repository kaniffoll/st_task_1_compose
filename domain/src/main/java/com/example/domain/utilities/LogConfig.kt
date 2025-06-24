package com.example.domain.utilities

import android.util.Log
import com.example.domain.BuildConfig
import com.example.domain.utilities.LogConfig.APP_TAG

object LogConfig {
    const val APP_TAG = "TASK_1_COMPOSE_APP"
}

fun Any.e(appTag: String = APP_TAG) {
    if (BuildConfig.DEBUG) {
        Log.e(appTag, this.toString())
    }
}

fun Any.d(appTag: String = APP_TAG) {
    if (BuildConfig.DEBUG) {
        Log.d(appTag, this.toString())
    }
}