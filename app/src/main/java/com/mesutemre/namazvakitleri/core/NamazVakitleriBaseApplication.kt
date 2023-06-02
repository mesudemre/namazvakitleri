package com.mesutemre.namazvakitleri.core

import android.app.Application
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.createAppChannel

/**
 * @Author: mesutemre.celenk
 * @Date: 26.03.2023
 */

open class NamazVakitleriBaseApplication : Application() {

    companion object {
        lateinit var instance: NamazVakitleriBaseApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        this.createAppChannel(
            channelId = Constants.ChannelConstants.VAKIT_PUSH_CHANNEL_ID,
            channelName = Constants.ChannelConstants.VAKIT_PUSH_CHANNEL_NAME,
            description = R.string.notification_channel_vakit_info_description
        )
        this.createAppChannel(
            channelId = Constants.ChannelConstants.CUMA_HATIRLATICI_CHANNEL_ID,
            channelName = Constants.ChannelConstants.CUMA_HATIRLATICI_CHANNEL_NAME,
            description = R.string.notification_channel_cuma_hatirlatici_description
        )
    }
}