package com.mesutemre.namazvakitleri.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun NoNetworkConnection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.sdp),
        elevation = 8.sdp,
        shape = NamazvakitleriTheme.shapes.medium,
        backgroundColor = NamazvakitleriTheme.colors.uiBackground,
        contentColor = NamazvakitleriTheme.colors.uiBackground,
        border = BorderStroke(width = 1.sdp, color = NamazvakitleriTheme.colors.passiveStepper)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_no_internet),
                modifier = Modifier.padding(12.sdp).size(128.sdp),
                contentDescription = stringResource(id = R.string.common_no_network)
            )
            Text(
                text = stringResource(id = R.string.common_no_network),
                style = NamazvakitleriTheme.typography.errorTextStyle.copy(
                    color = NamazvakitleriTheme.colors.errorColor
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.sdp)
            )
        }
    }

}