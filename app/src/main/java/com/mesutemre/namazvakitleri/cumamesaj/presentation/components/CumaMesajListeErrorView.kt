package com.mesutemre.namazvakitleri.cumamesaj.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun CumaMesajListeErrorView(
    onTryAgain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_result),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.settings_cuma_mesaj_not_found),
            style = NamazvakitleriTheme.typography.ayetHadisContent.copy(
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.sdp)
        )

        Button(
            modifier = Modifier
                .padding(top = 32.sdp)
                .align(Alignment.CenterHorizontally),
            onClick = onTryAgain, elevation = ButtonDefaults.elevation(
                defaultElevation = 4.sdp,
                pressedElevation = 8.sdp
            ), colors = ButtonDefaults.buttonColors(
                backgroundColor = NamazvakitleriTheme.colors.searchTextBorderColor
            ), shape = NamazvakitleriTheme.shapes.medium
        ) {
            Text(
                text = stringResource(id = R.string.common_tryAgain),
                style = NamazvakitleriTheme.typography.ayetHadisContent.copy(
                    color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                ),
                modifier = Modifier
                    .padding(vertical = 8.sdp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}