package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun AyetCard(content: String, onShare: (String) -> Unit) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_god),
                    contentDescription = "God",
                    modifier = Modifier.size(32.sdp)
                )
                Text(
                    text = "Günün Ayeti",
                    style = NamazvakitleriTheme.typography.ayetHadisTitle,
                    color = NamazvakitleriTheme.colors.normalVakit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.sdp)
                )
            }
            Spacer(modifier = Modifier.height(8.sdp))
            Text(
                text = content,
                style = NamazvakitleriTheme.typography.ayetHadisContent,
                color = NamazvakitleriTheme.colors.normalVakit,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier
                .align(Alignment.End)
                .padding(top = 12.sdp)
                .clickable {
                    onShare(content)
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                    contentDescription = "Share",
                    modifier = Modifier
                        .size(24.sdp),
                    tint = NamazvakitleriTheme.colors.shareButtonColor
                )
                Text(
                    text = "Paylaş",
                    style = NamazvakitleriTheme.typography.ayetHadisContent,
                    color = NamazvakitleriTheme.colors.shareButtonColor,
                    modifier = Modifier.padding(start = 6.sdp)
                )
            }
        }
    }
}