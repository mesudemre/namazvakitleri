package com.mesutemre.namazvakitleri.notification.service

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mesutemre.namazvakitleri.core.ext.createVakitHatirlaticiNotification

class NamazVakitleriFirebaseNotificationService : FirebaseMessagingService() {

    override fun handleIntent(intent: Intent?) {
        intent?.let {
            it.getStringExtra("gcm.notification.title")?.let { title ->
                if (title.isNotEmpty()) {
                    this.createVakitHatirlaticiNotification(
                        title = title,
                        description = it.getStringExtra("gcm.notification.body") ?: ""
                    )
                }
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let { notification ->
            val title = notification.title
            val description = notification.body
            title?.let { t ->
                this.createVakitHatirlaticiNotification(
                    title = t,
                    description = description ?: ""
                )
            }
        }
    }
}