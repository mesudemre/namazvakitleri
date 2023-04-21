package com.mesutemre.namazvakitleri.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun EmptyState(message: String, messageColor: Color = NamazvakitleriTheme.colors.errorColor) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.no_result),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(200.sdp)
                .fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(20.sdp))

        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 10.sdp, start = 25.sdp, end = 25.sdp)
                .fillMaxWidth(),
            style = NamazvakitleriTheme.typography.errorTextStyle,
            color = messageColor,
        )
    }
}