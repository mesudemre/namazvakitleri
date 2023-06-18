package com.mesutemre.namazvakitleri.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mesutemre.namazvakitleri.core.ext.createCumaHatirlaticiNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.*

@HiltWorker
class CumaNotificationWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted val workerParams: WorkerParameters
) : Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val calendar = Calendar.getInstance()
        val canCreateNotification = (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                &&
                calendar.get(Calendar.HOUR_OF_DAY)  in 11..12
        if (canCreateNotification) {
            appContext.createCumaHatirlaticiNotification()
        }
        return Result.success()
    }
}