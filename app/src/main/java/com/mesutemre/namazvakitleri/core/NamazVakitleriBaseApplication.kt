package com.mesutemre.namazvakitleri.core

import android.app.Application

/**
 * @Author: mesutemre.celenk
 * @Date: 26.03.2023
 */

open class NamazVakitleriBaseApplication: Application() {

    companion object {
        lateinit var instance: NamazVakitleriBaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}