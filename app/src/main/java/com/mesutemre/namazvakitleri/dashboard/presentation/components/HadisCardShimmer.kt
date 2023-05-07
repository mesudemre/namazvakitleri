package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun HadisCardShimmer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.sdp, end = 12.sdp, top = 20.sdp),
        elevation = 8.sdp,
        shape = NamazvakitleriTheme.shapes.medium,
        backgroundColor = NamazvakitleriTheme.colors.uiBackground,
        contentColor = NamazvakitleriTheme.colors.uiBackground,
        border = BorderStroke(width = 1.sdp, color = NamazvakitleriTheme.colors.passiveStepper)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.sdp, horizontal = 8.sdp)
        ) {
            Box(
                modifier = Modifier
                    .width(120.sdp)
                    .height(32.sdp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.sdp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.sdp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(2.sdp))
            Box(
                modifier = Modifier
                    .width(200.sdp)
                    .height(12.sdp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(2.sdp))
            Box(
                modifier = Modifier
                    .width(180.sdp)
                    .height(12.sdp)
                    .shimmerEffect()
            )
        }
    }
}