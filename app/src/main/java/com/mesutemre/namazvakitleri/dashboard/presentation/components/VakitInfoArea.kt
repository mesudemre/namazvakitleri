package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun VakitInfoArea(
    sonrakiVakit: Long,
    miladiTarihUzun: String,
    hicriTarihUzun: String
) {
    val time = (sonrakiVakit).minus(Calendar.getInstance().timeInMillis)
    var timer by remember { mutableStateOf(time) }
    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1000L
        } else if ((timer % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt() == 0) {
            timer = (sonrakiVakit).minus(Calendar.getInstance().timeInMillis)
        }
    }

    val hours =
        (timer % Constants.DashboardConstants.DAY_MIL_SEC / Constants.DashboardConstants.HOUR_MIL_SEC).toInt()
    val minutes =
        (timer % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC / Constants.DashboardConstants.MIN_MIL_SEC).toInt()
    val seconds =
        (timer % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt()

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
                text = String.format("%02d", hours),
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
                text = String.format("%02d", minutes),
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
                text = String.format("%02d", seconds),
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