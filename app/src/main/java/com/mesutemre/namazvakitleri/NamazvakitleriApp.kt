package com.mesutemre.namazvakitleri

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mesutemre.namazvakitleri.core.ext.sdp
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = NamazvakitleriTheme.colors.topBarBackgroundColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .padding(start = 12.sdp, bottom = 16.sdp, top = 8.sdp),
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