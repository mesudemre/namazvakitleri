package com.mesutemre.namazvakitleri.onboarding.presentation.complete

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.onboarding.presentation.components.AnimatedText
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.delay

@Composable
fun OnboardingCompleteScreen(navController: NavController) {
    var animateFirstOne by remember {
        mutableStateOf(false)
    }
    var animateSecondOne by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        animateFirstOne = true
        delay(500)
        animateSecondOne = true
    }

    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.linearGradient(colors = NamazvakitleriTheme.colors.onboardingCompleteBackgroundColor))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.sdp, bottom = 24.sdp, start = 32.sdp, end = 32.sdp)
                    .weight(1f)
            ) {
                AnimArea()
                AnimatedText(
                    isAnimate = animateFirstOne,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.sdp),
                    text = stringResource(id = R.string.onboarding_complete_screen_welcome)
                )
                AnimatedText(
                    isAnimate = animateSecondOne,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.sdp),
                    text = stringResource(id = R.string.onboarding_complete_screen_description)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.sdp, end = 16.sdp, bottom = 32.sdp)
            ) {
                FinishInfoArea {}
                Spacer(modifier = Modifier.height(12.sdp))
                OnboardingStepper(activeStep = 3)
            }
        }
    }
}

@Composable
private fun ColumnScope.AnimArea() {
    val anim: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.completed_anim
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = anim.value,
        isPlaying = true,
        speed = 1.0f
    )
    LottieAnimation(
        composition = anim.value, progress,
        Modifier
            .wrapContentHeight()
            .height(150.sdp)
            .align(alignment = Alignment.CenterHorizontally)
    )
}

@Composable
private fun FinishInfoArea(onClickNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.sdp)
            .clickable {
                onClickNext()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_complete_screen_finish),
            style = NamazvakitleriTheme.typography.onboardingInfoTextStyle,
            color = NamazvakitleriTheme.colors.welcomeContinueText
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = stringResource(id = R.string.onboarding_complete_screen_finish),
            modifier = Modifier
                .padding(start = 2.sdp)
                .size(32.sdp),
            tint = NamazvakitleriTheme.colors.welcomeContinueText
        )
    }
}