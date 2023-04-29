package com.mesutemre.namazvakitleri.onboarding.presentation.components

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.style.TextAlign
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedText(
    isAnimate: Boolean,
    modifier: Modifier,
    text: String
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isAnimate,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + scaleIn(
            transformOrigin = TransformOrigin(0.5f, 0f)
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = NamazvakitleriTheme.colors.contentColor,
            style = NamazvakitleriTheme.typography.onboardingInfoTextStyle
        )
    }
}