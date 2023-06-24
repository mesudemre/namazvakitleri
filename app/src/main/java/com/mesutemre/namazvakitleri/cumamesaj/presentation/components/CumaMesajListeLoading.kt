package com.mesutemre.namazvakitleri.cumamesaj.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect

@Composable
fun CumaMesajListeLoading() {
    repeat(4) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.sdp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.sdp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(12.sdp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(12.sdp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(4.sdp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(12.sdp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(4.sdp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(12.sdp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(4.sdp))
            Box(modifier = Modifier
                .width(200.sdp)
                .height(12.sdp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(4.sdp))
            Box(modifier = Modifier
                .height(12.sdp)
                .width(120.sdp)
                .shimmerEffect())

            Spacer(modifier = Modifier.height(24.sdp))
        }
    }
}