package com.mesutemre.namazvakitleri.core.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseCall : IFirebaseCall {

    override fun firebaseCall(call: () -> DatabaseReference): Flow<BaseResourceEvent<DataSnapshot>> =
        callbackFlow {
            trySend(BaseResourceEvent.Loading())
            val ref = call.invoke()
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(BaseResourceEvent.Success(snapshot))
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(BaseResourceEvent.Error(error.message))
                }
            }
            ref.addListenerForSingleValueEvent(listener)
            awaitClose {
                ref.removeEventListener(listener)
            }
        }
}