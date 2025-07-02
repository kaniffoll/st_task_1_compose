package com.example.task_1_compose.notifications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_NAME
import com.example.task_1_compose.MainActivity
import com.example.task_1_compose.R

class RemindNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, REMINDER_CHANEL_ID)
            .setSmallIcon(R.drawable.outline_kitesurfing_24)
            .setContentTitle(NOTIFICATION_CHANEL_NAME)
            .setContentText(title)
            .setContentIntent(activityPendingIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        const val REMINDER_CHANEL_ID = "reminder chanel"
    }
}