package com.mesutemre.namazvakitleri.onboarding.presentation.city

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.domain.use_case.GetCityList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingCitySelectionViewModel @Inject constructor(
    private val getCityList: GetCityList
): ViewModel() {

    init {
        viewModelScope.launch {
            getCityList().collectLatest {
                if (it is BaseResourceEvent.Success) {
                    it.data?.let { list->
                        list.forEach { data->
                            Log.d("ŞEHİR",data.cityName)
                        }
                    }
                }
            }
        }
    }
}