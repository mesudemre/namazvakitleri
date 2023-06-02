package com.mesutemre.namazvakitleri.notification.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NamazVakitleriFirebaseNotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let { notification ->
            
        }
    }
}