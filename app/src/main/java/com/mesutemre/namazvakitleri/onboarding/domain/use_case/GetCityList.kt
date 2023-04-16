package com.mesutemre.namazvakitleri.onboarding.domain.use_case

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.data.repository.IOnboardingRepository
import com.mesutemre.namazvakitleri.onboarding.domain.model.CityData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@ViewModelScoped
class GetCityList @Inject constructor(
    private val onboardingRepository: IOnboardingRepository
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<CityData>>> {
        val isCitySaved = onboardingRepository.isCitiesSaved().first()
        return if (isCitySaved) {
            onboardingRepository.getCityListFromDB()
        } else {
            val list = onboardingRepository.getCityListFromAPI()
            list.collectLatest {
                if (it is BaseResourceEvent.Success) {
                    it.data?.let { list ->
                        onboardingRepository.saveCity(list)
                    }
                    onboardingRepository.saveCityControl()
                }
            }
            list
        }
    }
}