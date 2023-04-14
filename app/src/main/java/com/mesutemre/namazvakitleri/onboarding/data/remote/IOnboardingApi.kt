package com.mesutemre.namazvakitleri.onboarding.data.remote

import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import retrofit2.Response
import retrofit2.http.GET

interface IOnboardingApi {

    @GET("sehirler/2")
    suspend fun getCityList(): Response<List<CityDto>>
}