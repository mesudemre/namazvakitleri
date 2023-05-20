package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun TarihArea(miladiTarihUzun: String, hicriTarihUzun: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.sdp),
        shape = NamazvakitleriTheme.shapes.large,
        backgroundColor = Color.Transparent,
        elevation = 0.sdp,
        border = BorderStroke(
            width = 2.sdp,
            color = NamazvakitleriTheme.colors.searchTextBackgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.sdp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = miladiTarihUzun,
                style = NamazvakitleriTheme.typography.tarihInfoStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = hicriTarihUzun,
                modifier = Modifier.padding(start = 8.sdp),
                style = NamazvakitleriTheme.typography.tarihInfoStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
        }
    }
}