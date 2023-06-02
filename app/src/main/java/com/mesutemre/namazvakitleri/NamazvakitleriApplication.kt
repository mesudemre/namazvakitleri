package com.mesutemre.namazvakitleri

import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.mesutemre.namazvakitleri.core.NamazVakitleriBaseApplication
import com.mesutemre.namazvakitleri.core.ext.enqueePeriodicTimeWorkManager
import com.mesutemre.namazvakitleri.worker.CumaNotificationWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NamazvakitleriApplication : NamazVakitleriBaseApplication(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        enqueePeriodicTimeWorkManager<CumaNotificationWorker>("cumaNotification")
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setMinimumLoggingLevel(android.util.Log.DEBUG)
        .setWorkerFactory(workerFactory)
        .build()
}