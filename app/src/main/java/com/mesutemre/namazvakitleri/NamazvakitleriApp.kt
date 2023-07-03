package com.mesutemre.namazvakitleri

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigation
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import com.mesutemre.namazvakitleri.ui.components.NamazVakitleriSnackbar
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriScaffold
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun NamazvakitleriApp(
    startDestination: NamazvakitleriNavigationItem
) {
    NamazvakitleriTheme {
        val appState = rememberNamazvakitleriAppState()
        val snackbarHostState = remember { SnackbarHostState() }
        NamazvakitleriScaffold(
            scaffoldState = rememberScaffoldState(
                snackbarHostState = snackbarHostState
            ),
            topBar = {
                if (appState.shouldShowTopBar) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = NamazvakitleriTheme.colors.topBarBackgroundColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .padding(start = 12.sdp, bottom = 6.sdp, top = 6.sdp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_topbar_left),
                                contentDescription = "Back",
                                tint = NamazvakitleriTheme.colors.topBarIconColor,
                                modifier = Modifier.clickable {
                                    appState.popBack()
                                }
                            )
                            Text(
                                text = appState.screenTitle,
                                style = NamazvakitleriTheme.typography.ayetHadisTitle,
                                color = NamazvakitleriTheme.colors.topBarTextColor,
                                modifier = Modifier.padding(start = 12.sdp)
                            )
                        }
                    }
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> NamazVakitleriSnackbar(snackbarData) }
                )
            }
        ) {
            NamazvakitleriNavigation(
                modifier = Modifier.padding(it),
                snackbarHostState = snackbarHostState,
                navController = appState.navController,
                startDestinition = startDestination,
                popBack = appState::popBack
            )
        }
    }
}