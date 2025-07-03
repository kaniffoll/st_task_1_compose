package com.example.domain.interactors

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.domain.resources.BundleKeyNames.ACTIVITY_KEY
import com.example.domain.resources.BundleKeyNames.TITLE_KEY
import com.example.domain.worker.RemindWorker
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class RemindWorkManagerInteractor {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createWorkRequest(
        context: Context,
        triggerTime: LocalDateTime,
        title: String,
        activityClass: Class<*>
    ) {

        val delayMillis =
            Duration.between(LocalDateTime.now(), triggerTime).toMillis().coerceAtLeast(0L)

        val data = Data.Builder()
            .putString(TITLE_KEY, title)
            .putString(ACTIVITY_KEY, activityClass.name)
            .build()

        val request = OneTimeWorkRequestBuilder<RemindWorker>()
            .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context)
            .enqueue(request)
    }

}