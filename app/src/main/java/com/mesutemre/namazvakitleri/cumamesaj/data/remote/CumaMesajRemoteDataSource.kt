package com.mesutemre.namazvakitleri.cumamesaj.data.remote

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mesutemre.namazvakitleri.core.Constants

class CumaMesajRemoteDataSource : ICumaMesajRemoteDataSource {

    override fun getCumaMesajListFromFirebase(): DatabaseReference {
        val firebaseDatabase =
            FirebaseDatabase.getInstance(Constants.FirebaseConstants.DB_INSTANCE_URL)
        return firebaseDatabase.getReference(Constants.FirebaseConstants.CUMA_MESAJ_DB)
    }
}