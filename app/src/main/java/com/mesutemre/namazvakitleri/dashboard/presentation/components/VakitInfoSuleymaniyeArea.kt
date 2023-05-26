package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.onboarding.domain.model.DistrictData
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VakitInfoSuleymaniyeArea(
    hours: Int,
    minutes: Int,
    seconds: Int,
    selectedDistrict: DistrictData?,
    miladiTarihUzun: String,
    hicriTarihUzun: String
) {
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
            OnboardingStepper(activeStep = 1, stepSize = 1)
            Spacer(modifier = Modifier.height(16.sdp))
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}