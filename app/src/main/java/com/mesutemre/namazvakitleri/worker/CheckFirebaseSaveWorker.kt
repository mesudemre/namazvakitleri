package com.mesutemre.namazvakitleri.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.mesutemre.namazvakitleri.core.ext.createVakitHatirlaticiNotification
import com.mesutemre.namazvakitleri.core.ext.isNetworkAvaliable
import com.mesutemre.namazvakitleri.worker.use_case.CheckAndPushNotificationTokenToFirebase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CheckFirebaseSaveWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters,
    val checkAndPushNotificationTokenToFirebase: CheckAndPushNotificationTokenToFirebase
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        if (context.isNetworkAvaliable()) {
            checkAndPushNotificationTokenToFirebase {
                if (it) {
                    WorkManager.getInstance(context).cancelAllWorkByTag("firebaseSaveWorker")
                }
            }
            return Result.success()
        }
        return Result.success()
    }
}