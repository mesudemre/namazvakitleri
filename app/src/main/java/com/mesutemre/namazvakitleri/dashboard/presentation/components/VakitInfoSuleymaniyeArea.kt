package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoScreenData
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.delay
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VakitInfoSuleymaniyeArea(
    data: VakitInfoScreenData,
    selectedDistrict: DistrictData?,
    miladiTarihUzun: String,
    hicriTarihUzun: String
) {
    var timer by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay(1000L)
            timer -= 1000L
        } else if ((timer % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt() == 0) {
            timer = Calendar.getInstance().timeInMillis
        }
    }
    val sonrakiVakit = remember {
        derivedStateOf {
            if (timer >= data.bugunVakitInfo.imsak.date && timer < data.bugunVakitInfo.gunes.date)
                data.bugunVakitInfo.gunes.date
            else if (timer >= data.bugunVakitInfo.gunes.date && timer < data.bugunVakitInfo.ogle.date)
                data.bugunVakitInfo.ogle.date
            else if (timer >= data.bugunVakitInfo.ogle.date && timer < data.bugunVakitInfo.ikindi.date)
                data.bugunVakitInfo.ikindi.date
            else if (timer >= data.bugunVakitInfo.ikindi.date && timer < data.bugunVakitInfo.aksam.date)
                data.bugunVakitInfo.aksam.date
            else if (timer >= data.bugunVakitInfo.aksam.date && timer < data.bugunVakitInfo.yatsi.date)
                data.bugunVakitInfo.yatsi.date
            else if (timer > data.bugunVakitInfo.yatsi.date && timer < data.yarinVakitInfo.imsak.date)
                data.yarinVakitInfo.imsak.date
            else
                data.yarinVakitInfo.imsak.date
        }
    }
    val time = (sonrakiVakit.value).minus(Calendar.getInstance().timeInMillis)
    var timerVakit by remember { mutableStateOf(time) }
    LaunchedEffect(key1 = timerVakit) {
        if (timerVakit > 0) {
            delay(1000L)
            timerVakit -= 1000L
        } else if ((timerVakit % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt() == 0) {
            timerVakit = (sonrakiVakit.value).minus(Calendar.getInstance().timeInMillis)
        }
    }

    val hours =
        (timerVakit % Constants.DashboardConstants.DAY_MIL_SEC / Constants.DashboardConstants.HOUR_MIL_SEC).toInt()
    val minutes =
        (timerVakit % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC / Constants.DashboardConstants.MIN_MIL_SEC).toInt()
    val seconds =
        (timerVakit % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt()
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.suleymaniye),
            modifier = Modifier.fillMaxSize(), contentDescription = "Süleymaniye",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(top = 24.sdp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                selectedDistrict?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.sdp, top = 8.sdp),
                        text = it.districtName,
                        style = NamazvakitleriTheme.typography.vakitInfo,
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                } ?: run {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.sdp, top = 8.sdp),
                        text = "",
                        style = NamazvakitleriTheme.typography.vakitInfo,
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.sdp))
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
                var oldSecond by remember {
                    mutableStateOf(seconds)
                }
                SideEffect {
                    oldSecond = seconds
                }
                val secondString = String.format("%02d", seconds)
                val oldSecondString = String.format("%02d", oldSecond)

                for (i in secondString.indices) {
                    val oldChar = oldSecondString.getOrNull(i)
                    val newChar = secondString[i]
                    val char = if (oldChar == newChar) {
                        oldSecondString[i]
                    } else {
                        secondString[i]
                    }

                    AnimatedContent(
                        modifier = Modifier
                            .padding(start = 12.sdp, bottom = 6.sdp)
                            .align(Alignment.Bottom),
                        targetState = char, transitionSpec = {
                            slideInVertically { it } with slideOutVertically { -it }
                        }) {
                        Text(
                            text = char.toString(),
                            style = NamazvakitleriTheme.typography.kalanSureSaniyeTextStyle,
                            color = NamazvakitleriTheme.colors.searchTextBackgroundColor,
                            softWrap = false
                        )
                    }
                }
            }
            TarihArea(miladiTarihUzun = miladiTarihUzun, hicriTarihUzun = hicriTarihUzun)
            Spacer(modifier = Modifier.height(16.sdp))
            OnboardingStepper(activeStep = 1, stepSize = 2)
            Spacer(modifier = Modifier.height(16.sdp))
        }
    }

}