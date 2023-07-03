package com.mesutemre.namazvakitleri.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mesutemre.namazvakitleri.cumamesaj.presentation.CumaMesajListeScreen
import com.mesutemre.namazvakitleri.cumamesaj.presentation.CumaMesajListeViewModel
import com.mesutemre.namazvakitleri.dashboard.presentation.DashboardScreen
import com.mesutemre.namazvakitleri.dashboard.presentation.DashboardViewModel
import com.mesutemre.namazvakitleri.kible.presentation.KibleScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.city.OnboardingCitySelectionScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.city.OnboardingCitySelectionViewModel
import com.mesutemre.namazvakitleri.onboarding.presentation.complete.OnboardingCompleteScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.complete.OnboardingCompleteViewModel
import com.mesutemre.namazvakitleri.onboarding.presentation.district.OnboardingDistrictSelectionScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.district.OnboardingDistrictSelectionViewModel
import com.mesutemre.namazvakitleri.onboarding.presentation.welcome.OnboardingWelcomeScreen
import com.mesutemre.namazvakitleri.settings.presentation.SettingsScreen
import com.mesutemre.namazvakitleri.tarihtebugun.presentation.TarihteBugunListScreen
import com.mesutemre.namazvakitleri.tarihtebugun.presentation.TarihteBugunListViewModel
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun NamazvakitleriNavigation(
    modifier: Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    startDestinition: NamazvakitleriNavigationItem,
    popBack: (
        route: String?,
        inclusive: Boolean
    ) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestinition.screenRoute
    ) {
        composable(route = NamazvakitleriNavigationItem.ExampleScreen.screenRoute) {
            NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Merhabalar",
                        style = NamazvakitleriTheme.typography.title,
                        color = NamazvakitleriTheme.colors.currentVakit
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Merhabalar",
                        style = NamazvakitleriTheme.typography.vakitInfo,
                        color = NamazvakitleriTheme.colors.normalVakit
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Merhabalar",
                        style = NamazvakitleriTheme.typography.ayetHadisTitle,
                        color = NamazvakitleriTheme.colors.ayetHadisInfo
                    )
                }
            }
        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingWelcomeScreen.screenRoute
        ) {
            OnboardingWelcomeScreen(navController = navController)
        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingCitySelectionScreen.screenRoute
        ) {
            val viewModel = hiltViewModel<OnboardingCitySelectionViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            OnboardingCitySelectionScreen(navController = navController, state = state.value)
        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingDistrictListScreen.screenRoute,
            arguments = listOf(
                navArgument("cityId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<OnboardingDistrictSelectionViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            OnboardingDistrictSelectionScreen(navController = navController, state = state.value)
        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingCompleteScreen.screenRoute,
            arguments = listOf(
                navArgument(name = "districtId") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel = hiltViewModel<OnboardingCompleteViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            OnboardingCompleteScreen(
                navController = navController,
                state = state.value,
                onComplete = viewModel::saveHadisListWithJson
            )
        }

        composable(
            route = NamazvakitleriNavigationItem.DashboardScreen.screenRoute
        ) {
            val viewModel = hiltViewModel<DashboardViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            DashboardScreen(
                state = state.value,
                snackbarHostState = snackbarHostState,
                onChangeVakitTypePage = viewModel::setActiveVakitPage,
                onClickTarihteBugun = {
                    navController.navigate(NamazvakitleriNavigationItem.TarihteBugunScreen.screenRoute)
                },
                onClickSettings = {
                    navController.navigate(NamazvakitleriNavigationItem.SettingsScreen.screenRoute)
                },
                getAndSaveAyetList = viewModel::getAndSaveAyetList,
                getAndSaveHadisList = viewModel::getAndSaveHadisList
            )
        }

        composable(
            route = NamazvakitleriNavigationItem.TarihteBugunScreen.screenRoute
        ) {
            val viewModel = hiltViewModel<TarihteBugunListViewModel>()
            val state = viewModel.state.collectAsStateWithLifecycle()
            TarihteBugunListScreen(state = state.value)
        }

        composable(
            route = NamazvakitleriNavigationItem.SettingsScreen.screenRoute
        ) {
            SettingsScreen(
                navigateToCumaMesajListe = {
                    navController.navigate(NamazvakitleriNavigationItem.CumaMesajListeScreen.screenRoute)
                },
                navigateToKible = {
                    navController.navigate(NamazvakitleriNavigationItem.KibleScreen.screenRoute)
                },
                navigateToKonumDegistir = {
                    navController.navigate(NamazvakitleriNavigationItem.OnboardingCitySelectionScreen.screenRoute)
                }
            )
        }

        composable(
            route = NamazvakitleriNavigationItem.CumaMesajListeScreen.screenRoute
        ) {
            val viewModel = hiltViewModel<CumaMesajListeViewModel>()
            val state = viewModel.cumaMesajListeState.collectAsStateWithLifecycle()
            CumaMesajListeScreen(state = state.value, tryAgain = viewModel::getCumaMesajList)
        }

        composable(
            route = NamazvakitleriNavigationItem.KibleScreen.screenRoute
        ) {
            KibleScreen()
        }
    }
}
