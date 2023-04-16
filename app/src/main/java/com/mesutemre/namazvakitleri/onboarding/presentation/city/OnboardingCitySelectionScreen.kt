package com.mesutemre.namazvakitleri.onboarding.presentation.city

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface

@Composable
fun OnboardingCitySelectionScreen(
    viewModel: OnboardingCitySelectionViewModel = hiltViewModel()
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {

    }
}