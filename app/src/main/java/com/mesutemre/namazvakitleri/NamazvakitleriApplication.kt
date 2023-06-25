package com.mesutemre.namazvakitleri

import android.os.StrictMode
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import com.mesutemre.namazvakitleri.core.NamazVakitleriBaseApplication
import com.mesutemre.namazvakitleri.core.ext.enqueePeriodicTimeWorkManager
import com.mesutemre.namazvakitleri.worker.CheckFirebaseSaveWorker
import com.mesutemre.namazvakitleri.worker.CumaNotificationWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NamazvakitleriApplication : NamazVakitleriBaseApplication(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        enqueePeriodicTimeWorkManager<CumaNotificationWorker>(
            "cumaNotification",
            repeatInterval = 1,
            period = TimeUnit.HOURS
        )
        enqueePeriodicTimeWorkManager<CheckFirebaseSaveWorker>(
            "firebaseSaveWorker",
            repeatInterval = 20,
            period = TimeUnit.MINUTES,
            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.REPLACE
        )
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setMinimumLoggingLevel(android.util.Log.DEBUG)
        .setWorkerFactory(workerFactory)
        .build()
}