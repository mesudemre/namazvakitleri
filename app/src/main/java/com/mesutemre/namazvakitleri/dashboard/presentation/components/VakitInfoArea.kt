package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun VakitInfoArea(
    sonrakiVakit: String,
    miladiTarihUzun: String,
    hicriTarihUzun: String
) {
    Column(
        modifier = Modifier.padding(top = 24.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.dashboard_vakit_cikmasi),
            style = NamazvakitleriTheme.typography.vakitInfo,
            color = NamazvakitleriTheme.colors.searchTextBackgroundColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.sdp, horizontal = 16.sdp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "01",
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = ":",
                modifier = Modifier.padding(start = 12.sdp),
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = "10",
                modifier = Modifier.padding(start = 12.sdp),
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = ":",
                modifier = Modifier
                    .padding(start = 12.sdp, bottom = 6.sdp)
                    .align(Alignment.Bottom),
                style = NamazvakitleriTheme.typography.kalanSureSaniyeTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = "45",
                modifier = Modifier
                    .padding(start = 12.sdp, bottom = 6.sdp)
                    .align(Alignment.Bottom),
                style = NamazvakitleriTheme.typography.kalanSureSaniyeTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
        }
        TarihArea(miladiTarihUzun = miladiTarihUzun, hicriTarihUzun = hicriTarihUzun)

    }
}

@Composable
private fun TarihArea(miladiTarihUzun: String, hicriTarihUzun: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.sdp),
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
                style = NamazvakitleriTheme.typography.vakitInfo,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = hicriTarihUzun,
                modifier = Modifier.padding(start = 24.sdp),
                style = NamazvakitleriTheme.typography.vakitInfo,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
        }
    }
}

@Composable
fun VakitInfoAreaLoading() {
    Column(
        modifier = Modifier.padding(top = 24.sdp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.dashboard_vakit_cikmasi),
            style = NamazvakitleriTheme.typography.vakitInfo,
            color = NamazvakitleriTheme.colors.searchTextBackgroundColor
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.sdp, horizontal = 16.sdp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "00",
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = ":",
                modifier = Modifier.padding(start = 12.sdp),
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = "00",
                modifier = Modifier.padding(start = 12.sdp),
                style = NamazvakitleriTheme.typography.kalanSureSaatDakikaTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = ":",
                modifier = Modifier
                    .padding(start = 12.sdp, bottom = 6.sdp)
                    .align(Alignment.Bottom),
                style = NamazvakitleriTheme.typography.kalanSureSaniyeTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Text(
                text = "00",
                modifier = Modifier
                    .padding(start = 12.sdp, bottom = 6.sdp)
                    .align(Alignment.Bottom),
                style = NamazvakitleriTheme.typography.kalanSureSaniyeTextStyle,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
        }
    }
}