package com.example.task_1_compose.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.task_1_compose.notifications.RemindNotificationService

class RemindWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val title = inputData.getString("todo_title") ?: return  Result.failure()
        val notificationService = RemindNotificationService(applicationContext)
        notificationService.showNotification(title)
        return Result.success()
    }
}