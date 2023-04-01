package com.mesutemre.namazvakitleri.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                Text(
                    text = "Merhabalar",
                    style = NamazvakitleriTheme.typography.title,
                    color = NamazvakitleriTheme.colors.currentVakit
                )
            }
        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingWelcomeScreen.screenRoute
        ) {

        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingCityListScreen.screenRoute
        ) {

        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingDistrictListScreen.screenRoute
        ) {

        }

        composable(
            route = NamazvakitleriNavigationItem.OnboardingDistrictListScreen.screenRoute
        ) {

        }
    }
}
