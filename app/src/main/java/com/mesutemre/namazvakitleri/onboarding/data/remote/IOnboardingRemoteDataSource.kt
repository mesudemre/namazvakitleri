package com.mesutemre.namazvakitleri.onboarding.data.remote

import com.google.android.gms.tasks.Task
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.DistrictDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.FirebaseNotificationToken
import retrofit2.Response

interface IOnboardingRemoteDataSource {

    suspend fun getCityList(): Response<List<CityDto>>
    suspend fun getDistrictList(cityId: Int): Response<List<DistrictDto>>
    fun getNotificationTokenFromFirebase(): Task<String>
    fun saveTokenIlceToFirebase(notificationToken: FirebaseNotificationToken)
    fun isTokenExistInFirebase(token: String, onComplete:(Boolean) -> Unit)
}