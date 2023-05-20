package com.mesutemre.namazvakitleri.onboarding.presentation.welcome

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.findActivity
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import com.mesutemre.namazvakitleri.onboarding.presentation.components.OnboardingStepper
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun OnboardingWelcomeScreen(
    navController: NavController
) {
    val onStartClick = remember<() -> Unit> {
        {
            navController.navigate(NamazvakitleriNavigationItem.OnboardingCitySelectionScreen.screenRoute)
        }
    }
    val context = LocalContext.current
    BackHandler {
        context.findActivity().finishAffinity()
    }
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(brush = Brush.linearGradient(colors = NamazvakitleriTheme.colors.welcomeBackground))
        ) {
            val (welcomeAnim, welcomeInfo) = createRefs()
            Box(modifier = Modifier.constrainAs(welcomeAnim) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 32.sdp)
            }) {
                WelcomeAnimArea()
            }
            Column(modifier = Modifier
                .padding(start = 16.sdp, end = 16.sdp, bottom = 32.sdp)
                .constrainAs(welcomeInfo) {
                    bottom.linkTo(parent.bottom)
                }) {
                WelcomeInfoArea {
                    onStartClick()
                }
                Spacer(modifier = Modifier.height(12.sdp))
                OnboardingStepper(activeStep = 0)
            }
        }
    }
}

@Composable
private fun WelcomeAnimArea() {
    val anim: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            R.raw.welcome_anim
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = anim.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1.0f
    )
    LottieAnimation(
        composition = anim.value, progress,
        Modifier
            .wrapContentHeight()
            .height(400.sdp)
    )
}

@Composable
fun WelcomeInfoArea(onClickNext: () -> Unit) {
    Text(
        text = stringResource(id = R.string.onboarding_welcome_screen_welcome),
        style = NamazvakitleriTheme.typography.onboardingInfoTextStyle,
        textAlign = TextAlign.Center,
        color = NamazvakitleriTheme.colors.welcomeContinueText
    )

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
            text = stringResource(id = R.string.onboarding_welcome_screen_start),
            style = NamazvakitleriTheme.typography.onboardingInfoTextStyle,
            color = NamazvakitleriTheme.colors.welcomeContinueText
        )
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = "",
            modifier = Modifier
                .padding(start = 8.sdp)
                .size(32.sdp),
            tint = NamazvakitleriTheme.colors.welcomeContinueText
        )
    }
}