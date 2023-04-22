package com.mesutemre.namazvakitleri.onboarding.data.remote

import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.CityDto
import com.mesutemre.namazvakitleri.onboarding.data.remote.dto.DistrictDto
import retrofit2.Response
import javax.inject.Inject

class OnboardingRemoteDataSource @Inject constructor(
    private val api: IOnboardingApi
) : IOnboardingRemoteDataSource {

    override suspend fun getCityList(): Response<List<CityDto>> = api.getCityList()
    override suspend fun getDistrictList(cityId: Int): Response<List<DistrictDto>> =
        api.getDistrictList(cityId)
}