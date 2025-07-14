package com.example.task_1_compose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_DESCRIPTION
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_NAME
import com.example.domain.resources.StringResources.REMINDER_CHANEL_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Task1Application : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            REMINDER_CHANEL_ID,
            NOTIFICATION_CHANEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = NOTIFICATION_CHANEL_DESCRIPTION

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}