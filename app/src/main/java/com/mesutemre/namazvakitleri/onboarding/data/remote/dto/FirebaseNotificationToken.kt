package com.mesutemre.namazvakitleri.onboarding.data.remote.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebaseNotificationToken(
    val token: String,
    val ilceId: String
)
