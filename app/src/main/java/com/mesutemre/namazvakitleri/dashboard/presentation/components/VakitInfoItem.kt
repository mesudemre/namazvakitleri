package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun VakitInfoItem(vakitAd: String, saat: String, isActive: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = vakitAd, style = NamazvakitleriTheme.typography.vakitInfo,
            color = if (isActive) NamazvakitleriTheme.colors.currentVakit else NamazvakitleriTheme.colors.normalVakit
        )
        Spacer(modifier = Modifier.height(4.sdp))
        Text(
            text = saat, style = NamazvakitleriTheme.typography.vakitInfo,
            color = if (isActive) NamazvakitleriTheme.colors.currentVakit else NamazvakitleriTheme.colors.normalVakit
        )
    }
}

@Preview
@Composable
fun VakitInfoItemPreview() {
    VakitInfoItem(vakitAd = "Yatsı", saat = "21:05", isActive = false)
}

@Preview
@Composable
fun VakitInfoActiveItemPreview() {
    VakitInfoItem(vakitAd = "Yatsı", saat = "21:05", isActive = true)
}
