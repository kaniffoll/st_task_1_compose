package com.example.task_1_compose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_DESCRIPTION
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_NAME
import com.example.domain.resources.StringResources.REMINDER_CHANEL_ID
import com.example.domain.utilities.ResourceProvider

class Task1Application : Application() {
    override fun onCreate() {
        ResourceProvider.context = applicationContext
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
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}