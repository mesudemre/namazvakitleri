package com.mesutemre.namazvakitleri.onboarding.data.remote

import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.DistrictDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IOnboardingApi {

    @GET("sehirler/2")
    suspend fun getCityList(): Response<List<CityDto>>

    @GET("ilceler/{cityId}")
    suspend fun getDistrictList(@Path("cityId") cityId: Int): Response<List<DistrictDto>>
}