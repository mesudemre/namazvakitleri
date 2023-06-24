package com.mesutemre.namazvakitleri.cumamesaj.data.remote

import com.google.firebase.database.DatabaseReference

interface ICumaMesajRemoteDataSource {

    fun getCumaMesajListFromFirebase(): DatabaseReference
}