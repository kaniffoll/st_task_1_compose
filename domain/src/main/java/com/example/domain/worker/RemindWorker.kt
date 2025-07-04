package com.example.domain.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.domain.R
import com.example.domain.resources.AppSettings.NOTIFICATION_REQUEST_ID
import com.example.domain.resources.AppSettings.REQUEST_NOTIFICATION_CODE
import com.example.domain.resources.BundleKeyNames.ACTIVITY_KEY
import com.example.domain.resources.BundleKeyNames.TITLE_KEY
import com.example.domain.resources.StringResources.NOTIFICATION_CHANEL_NAME
import com.example.domain.resources.StringResources.REMINDER_CHANEL_ID

class RemindWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val title = inputData.getString(TITLE_KEY) ?: return Result.failure()
        showNotification(title, applicationContext)
        return Result.success()
    }

    private fun showNotification(title: String, context: Context) {
        val activityIntent = Intent(context, Class.forName(inputData.getString(ACTIVITY_KEY)!!))
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_NOTIFICATION_CODE,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, REMINDER_CHANEL_ID)
            .setSmallIcon(R.drawable.outline_kitesurfing_24)
            .setContentTitle(NOTIFICATION_CHANEL_NAME)
            .setContentText(title)
            .setContentIntent(activityPendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        notificationManager.notify(NOTIFICATION_REQUEST_ID, notification.build())
    }
}


