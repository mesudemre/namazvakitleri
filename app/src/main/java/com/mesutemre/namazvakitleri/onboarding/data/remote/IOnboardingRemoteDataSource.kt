package com.mesutemre.namazvakitleri.onboarding.data.remote

import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import retrofit2.Response

interface IOnboardingRemoteDataSource {

    suspend fun getCityList(): Response<List<CityDto>>
}