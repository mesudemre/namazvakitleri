package com.mesutemre.namazvakitleri

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigation
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriScaffold
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun NamazvakitleriApp(
    startDestination: NamazvakitleriNavigationItem
) {
    NamazvakitleriTheme {
        val appState = rememberNamazvakitleriAppState()

        NamazvakitleriScaffold(
            topBar = {
                if (appState.shouldShowTopBar) {
                    //TODO : Topbar yazılacak...Şimdilik lüzum yok.
                }
            }
        ) {
            NamazvakitleriNavigation(
                modifier = Modifier.padding(it),
                navController = appState.navController,
                startDestinition = startDestination,
                popBack = appState::popBack
            )
        }
    }
}