package com.mesutemre.namazvakitleri.onboarding.presentation.district

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun OnboardingDistrictSelectionScreen(
    navController: NavController,
    viewModel: OnboardingDistrictSelectionViewModel = hiltViewModel()
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.onBoardingBackground)
        ) {

        }
    }
}