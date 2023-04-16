package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCityList @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<CityData>>> = onboardingRepository.getCityListFromAPI()
}