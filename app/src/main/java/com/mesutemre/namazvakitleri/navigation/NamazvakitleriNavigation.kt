package com.mesutemre.namazvakitleri.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mesutemre.namazvakitleri.onboarding.presentation.city.OnboardingCitySelectionScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.district.OnboardingDistrictSelectionScreen
import com.mesutemre.namazvakitleri.onboarding.presentation.welcome.OnboardingWelcomeScreen
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun NamazvakitleriNavigation(
    modifier: Modifier,
    navController: NavHostController,
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
            OnboardingCitySelectionScreen(navController = navController)
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
            OnboardingDistrictSelectionScreen(navController = navController)
        }
    }
}
