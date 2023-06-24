package com.mesutemre.namazvakitleri.core.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.flow.Flow

interface IFirebaseCall {

    fun firebaseCall(
        call: () -> DatabaseReference
    ): Flow<BaseResourceEvent<DataSnapshot>>
}