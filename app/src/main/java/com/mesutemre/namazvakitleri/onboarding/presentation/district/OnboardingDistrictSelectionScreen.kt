package com.mesutemre.namazvakitleri.onboarding.presentation.district

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.onboarding.presentation.city.OnboardingRowItem
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun OnboardingDistrictSelectionScreen(
    navController: NavController,
    viewModel: OnboardingDistrictSelectionViewModel = hiltViewModel()
) {
    val districtList = viewModel.state.collectAsState()
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.onBoardingBackground)
                .statusBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(16.sdp))
            when (districtList.value.districtList) {
                is BaseResourceEvent.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                }
                is BaseResourceEvent.Error -> {
                }
                is BaseResourceEvent.Success -> {
                    districtList.value.districtList.data?.let { list ->
                        val size = list.size
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            itemsIndexed(list) { index, item ->
                                OnboardingRowItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.sdp)
                                        .clickable {

                                        }, text = item.districtName
                                )
                                if (index < (size - 1))
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.sdp),
                                        thickness = (1 / 2).sdp,
                                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                                    )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.sdp))
                        OnboardingStepper(activeStep = 2)
                        Spacer(modifier = Modifier.height(12.sdp))
                    }
                }
                else -> Unit
            }
        }
    }
}