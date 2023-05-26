package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VakitStickyHeader(
    isVisible: Boolean,
    hours: Int,
    minutes: Int,
    seconds: Int
) {
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth()
            .background(NamazvakitleriTheme.colors.vakitInfoBackgroundColor)
            .height(60.sdp),
        visible = isVisible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = 500)) { fullWidth ->
            -fullWidth / 3
        } + fadeIn(
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            500
        } + fadeOut()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.sdp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 8.sdp),
                text = "Vaktin Çıkmasına",
                style = NamazvakitleriTheme.typography.vakitInfo,
                color = NamazvakitleriTheme.colors.searchTextBackgroundColor
            )
            Row(
                modifier = Modifier
                    .padding(end = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = String.format("%02d", hours),
                    style = NamazvakitleriTheme.typography.kalanSureSaatDakikaStickHeaderTextStyle,
                    color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                )
                Text(
                    text = ":",
                    modifier = Modifier.padding(start = 6.sdp),
                    style = NamazvakitleriTheme.typography.kalanSureSaatDakikaStickHeaderTextStyle,
                    color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                )
                Text(
                    text = String.format("%02d", minutes),
                    modifier = Modifier.padding(start = 6.sdp),
                    style = NamazvakitleriTheme.typography.kalanSureSaatDakikaStickHeaderTextStyle,
                    color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                )
                Text(
                    text = ":",
                    modifier = Modifier.padding(start = 6.sdp),
                    style = NamazvakitleriTheme.typography.kalanSureSaatDakikaStickHeaderTextStyle,
                    color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                )
                Spacer(modifier = Modifier.width(6.sdp))
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
                        targetState = char, transitionSpec = {
                            slideInVertically { it } with slideOutVertically { -it }
                        }) {
                        Text(
                            text = char.toString(),
                            style = NamazvakitleriTheme.typography.kalanSureSaatDakikaStickHeaderTextStyle,
                            color = NamazvakitleriTheme.colors.searchTextBackgroundColor,
                            softWrap = false
                        )
                    }
                }
            }
        }
    }
}