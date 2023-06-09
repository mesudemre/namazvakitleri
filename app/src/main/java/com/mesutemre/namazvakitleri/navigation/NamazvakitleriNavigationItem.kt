package com.mesutemre.namazvakitleri.navigation

import androidx.annotation.StringRes
import com.mesutemre.namazvakitleri.R

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

    object OnboardingCitySelectionScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_city_selection_screen")

    object OnboardingDistrictListScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_districtlist_screen?cityId={cityId}")

    object OnboardingCompleteScreen :
        NamazvakitleriNavigationItem(screenRoute = "namazvakitleri_onboarding_complete_screen?districtId={districtId}")

    object DashboardScreen : NamazvakitleriNavigationItem(
        screenRoute = "namazvakitleri_dashboard_screen"
    )

    object TarihteBugunScreen: NamazvakitleriNavigationItem(
        screenRoute = "namazvakitleri_tarihte_bugun_list",
        showTopBar = true,
        pageTitle = R.string.tarihte_bugun_list_screen_title
    )
}
