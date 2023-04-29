package com.mesutemre.namazvakitleri.onboarding.presentation.city

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavController
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.onboarding.presentation.components.SearchInput
import com.mesutemre.namazvakitleri.ui.components.EmptyState
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun OnboardingCitySelectionScreen(
    navController: NavController,
    state: OnboardingCitySelecionState
) {
    var searchText by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.onBoardingBackground)
        ) {
            SearchInput(
                hint = stringResource(id = R.string.common_search),
                text = searchText,
                onChange = {
                    coroutineScope.launch(Dispatchers.Default) {
                        searchText = it
                    }
                },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.sdp)
            )

            when (state.cityList) {
                is BaseResourceEvent.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                }
                is BaseResourceEvent.Error -> {

                }
                is BaseResourceEvent.Success -> {
                    state.cityList.data?.let { list ->
                        val citySize = list.size
                        var liste = remember {
                            derivedStateOf {
                                if (searchText.isEmpty()) list
                                else list.filter {
                                    it.cityName.toLowerCase(Locale.current).contains(
                                        searchText.toLowerCase(
                                            Locale.current
                                        )
                                    )
                                }
                            }
                        }
                        if (liste.value.isEmpty()) {
                            EmptyState(
                                message = stringResource(id = R.string.city_not_found_filter),
                                messageColor = NamazvakitleriTheme.colors.searchTextBackgroundColor
                            )
                        } else {
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                itemsIndexed(liste.value) { index, item ->
                                    OnboardingRowItem(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.sdp)
                                            .clickable {
                                                navController.navigate(
                                                    NamazvakitleriNavigationItem.OnboardingDistrictListScreen.screenRoute
                                                        .replace("{cityId}", item.cityId.toString())
                                                )
                                            }, text = item.cityName
                                    )
                                    if (index < (citySize - 1))
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
                            OnboardingStepper(activeStep = 1)
                            Spacer(modifier = Modifier.height(12.sdp))
                        }
                    }
                }
                else -> Unit
            }
        }

    }
}