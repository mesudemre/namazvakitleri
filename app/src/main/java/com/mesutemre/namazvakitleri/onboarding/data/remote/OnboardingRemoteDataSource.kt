package com.mesutemre.namazvakitleri.onboarding.data.remote

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.DistrictDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.FirebaseNotificationToken
import retrofit2.Response
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val api: IOnboardingApi
) : IOnboardingRemoteDataSource {

    override suspend fun getCityList(): Response<List<CityDto>> = api.getCityList()
    override suspend fun getDistrictList(cityId: Int): Response<List<DistrictDto>> =
        api.getDistrictList(cityId)

    override fun getNotificationTokenFromFirebase(): Task<String> {
        return FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("PUSH_TOKEN", token)
            }
            .addOnFailureListener { exception ->
                Log.e("PUSH_TOKEN_ERROR", exception.localizedMessage)
            }
    }

    override fun saveTokenIlceToFirebase(notificationToken: FirebaseNotificationToken) {
        val fireBaseDataBase =
            FirebaseDatabase.getInstance(Constants.FirebaseConstants.DB_INSTANCE_URL)
        val refPushNotification =
            fireBaseDataBase.getReference(Constants.FirebaseConstants.PUSH_TOKEN_DB)
        refPushNotification.push().setValue(notificationToken)
    }

    override fun isTokenExistInFirebase(token: String,onComplete:(Boolean) -> Unit) {
        val firebaseDatabase = FirebaseDatabase.getInstance(Constants.FirebaseConstants.DB_INSTANCE_URL)
        val refPushNotification =
            firebaseDatabase.getReference(Constants.FirebaseConstants.PUSH_TOKEN_DB)
        val query = refPushNotification.orderByChild(Constants.FirebaseConstants.PUSH_TOKEN_DB_ORDERBY_CHILD).equalTo(token)
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onComplete(snapshot.exists())
            }

            override fun onCancelled(error: DatabaseError) {
                onComplete(false)
            }
        })
    }
}