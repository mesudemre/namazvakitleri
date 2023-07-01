package com.mesutemre.namazvakitleri.settings.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun SettingsBottomInfo() {
    Column(modifier = Modifier.padding(vertical = 4.sdp)) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.settings_bottom_desgined_by),
            style = NamazvakitleriTheme.typography.settingsDesignedByStyle,
            color = NamazvakitleriTheme.colors.normalVakit
        )
        Text(
            modifier = Modifier
                .padding(top = 8.sdp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.settings_bottom_copyright),
            style = NamazvakitleriTheme.typography.settingsDesignedByStyle,
            color = NamazvakitleriTheme.colors.normalVakit
        )

    }
}