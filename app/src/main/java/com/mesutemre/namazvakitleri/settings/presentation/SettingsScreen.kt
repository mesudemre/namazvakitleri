package com.mesutemre.namazvakitleri.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.settings.presentation.components.SettingsBottomInfo
import com.mesutemre.namazvakitleri.settings.presentation.components.SettingsItem
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun SettingsScreen(
    navigateToCumaMesajListe: () -> Unit,
    navigateToKible: () -> Unit,
    navigateToKonumDegistir: () -> Unit
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.uiBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.sdp)
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                SettingsItem(
                    modifier = Modifier.align(Alignment.Start),
                    title = stringResource(id = com.mesutemre.namazvakitleri.R.string.settings_item_cuma_mesaj),
                    isImage = true,
                    icon = com.mesutemre.namazvakitleri.R.drawable.ic_settings_cuma_mesaj,
                    onClick = navigateToCumaMesajListe
                )
                SettingsItem(
                    modifier = Modifier.align(Alignment.End),
                    title = stringResource(id = com.mesutemre.namazvakitleri.R.string.settings_item_kible),
                    icon = com.mesutemre.namazvakitleri.R.drawable.ic_settings_kabe,
                    onClick = navigateToKible
                )
                SettingsItem(
                    modifier = Modifier.align(Alignment.Start),
                    title = stringResource(id = com.mesutemre.namazvakitleri.R.string.settings_item_change_city),
                    icon = com.mesutemre.namazvakitleri.R.drawable.ic_settings_location,
                    onClick = navigateToKonumDegistir
                )
                SettingsItem(
                    modifier = Modifier.align(Alignment.End),
                    title = stringResource(id = com.mesutemre.namazvakitleri.R.string.settings_item_tercihler),
                    icon = com.mesutemre.namazvakitleri.R.drawable.ic_options
                ) {
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(), contentAlignment = Alignment.Center
            ) {
                SettingsBottomInfo()
            }
        }
    }
}



