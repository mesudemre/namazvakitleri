package com.mesutemre.namazvakitleri.onboarding.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.delay

@Composable
fun OnboardingStepper(activeStep: Int, stepSize: Int = 3) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 0..stepSize) {
            if (activeStep == index)
                OnboardingActiveStepper()
            else
                OnboardingPassiveStepper()
            Spacer(modifier = Modifier.width(8.sdp))
        }
    }
}

@Composable
fun OnboardingPassiveStepper() {
    Box(
        modifier = Modifier
            .size(10.sdp)
            .background(
                color = NamazvakitleriTheme.colors.activeStepper,
                shape = NamazvakitleriTheme.shapes.circle
            )
    )
}

@Composable
fun OnboardingActiveStepper() {
    var animate by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        delay(100)
        animate = true
    }
    val animatedWidthSize by animateDpAsState(
        targetValue = if (animate) 16.sdp else 10.sdp, animationSpec = tween(
            durationMillis = 300,
            easing = LinearEasing
        )
    )
    Box(
        modifier = Modifier
            .width(animatedWidthSize)
            .height(10.sdp)
            .background(
                color = NamazvakitleriTheme.colors.activeStepper,
                shape = RoundedCornerShape(4.sdp)
            )
    )
}

