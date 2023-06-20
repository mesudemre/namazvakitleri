package com.mesutemre.namazvakitleri.settings.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
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
        Row(
            modifier = Modifier.padding(top = 8.sdp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings_bottom_heart),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.sdp),
                text = stringResource(id = R.string.settings_bottom_copyright),
                style = NamazvakitleriTheme.typography.settingsDesignedByStyle,
                color = NamazvakitleriTheme.colors.normalVakit
            )
        }
    }
}