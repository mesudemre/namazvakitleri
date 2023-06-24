package com.mesutemre.namazvakitleri.cumamesaj.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shareTextAndImageContent
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun CumaMesajItem(
    mesaj: String,
    resimUrl: String,
    onShare: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.sdp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.sdp)
                .clip(shape = NamazvakitleriTheme.shapes.small),
            model = resimUrl,
            contentScale = ContentScale.FillBounds,
            contentDescription = mesaj
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.sdp)
                        .shimmerEffect()
                )
            } else {
                SubcomposeAsyncImageContent()
            }
        }
        Text(
            text = mesaj,
            style = NamazvakitleriTheme.typography.ayetHadisContent,
            modifier = Modifier.padding(vertical = 12.sdp)
        )

        Row(modifier = Modifier
            .align(Alignment.End)
            .clickable {
                onShare()
            }) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                contentDescription = stringResource(id = R.string.common_share),
                modifier = Modifier
                    .size(24.sdp),
                tint = NamazvakitleriTheme.colors.shareButtonColor
            )
            Text(
                text = stringResource(id = R.string.common_share),
                style = NamazvakitleriTheme.typography.ayetHadisContent,
                color = NamazvakitleriTheme.colors.shareButtonColor,
                modifier = Modifier.padding(start = 6.sdp)
            )
        }
    }
}