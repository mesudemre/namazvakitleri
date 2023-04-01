package com.mesutemre.namazvakitleri.navigation

import androidx.annotation.StringRes

sealed class NamazvakitleriNavigationItem(
    val screenRoute: String,
    val showTopBar: Boolean = false,
    val data: Any? = null,
    @StringRes val pageTitle: Int? = null
) {
    object ExampleScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_example_screen")

    object OnboardingWelcomeScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_welcome_screen")

    object OnboardingCityListScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_citylist_screen")

    object OnboardingDistrictListScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_districtlist_screen")
}
