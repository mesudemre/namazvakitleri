package com.mesutemre.namazvakitleri.settings.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun SettingsItem(
    modifier: Modifier,
    title: String,
    @DrawableRes icon: Int,
    isImage: Boolean = false,
    onClick: () -> Unit
) {
    val color = NamazvakitleriTheme.colors.topBarBackgroundColor
    Column(
        modifier = modifier
            .width(140.sdp)
            .height(140.sdp)
            .clickable {
                onClick()
            }
            .drawBehind {
                drawPath(
                    path = drawCustomHexagonPath(size),
                    color = color,
                    style = Stroke(
                        width = 10.sdp.toPx(),
                        pathEffect = PathEffect.cornerPathEffect(40f)
                    )
                )
            }
    ) {
        if (isImage) {
            Image(
                painter = painterResource(id = icon), contentDescription = "", modifier = Modifier
                    .size(48.sdp)
                    .padding(top = 24.sdp)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon),
                tint = Color.Unspecified,
                contentDescription = null, modifier = Modifier
                    .size(48.sdp)
                    .padding(top = 24.sdp)
                    .weight(1f)
                    .align(Alignment.CenterHorizontally)
            )
        }


        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.sdp),
            text = title,
            style = NamazvakitleriTheme.typography.tarihteBugunStyle.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = NamazvakitleriTheme.colors.normalVakit
        )
    }
}

private fun drawCustomHexagonPath(size: Size): Path {
    return Path().apply {
        val radius = java.lang.Float.min(size.width / 2f, size.height / 2f)
        customHexagon(radius, size)
    }
}

private fun Path.customHexagon(radius: Float, size: Size) {
    val triangleHeight = (Math.sqrt(3.0) * radius / 2)
    val centerX = size.width / 2
    val centerY = size.height / 2

    moveTo(centerX, centerY + radius)
    lineTo((centerX - triangleHeight).toFloat(), centerY + radius / 2)
    lineTo((centerX - triangleHeight).toFloat(), centerY - radius / 2)
    lineTo(centerX, centerY - radius)
    lineTo((centerX + triangleHeight).toFloat(), centerY - radius / 2)
    lineTo((centerX + triangleHeight).toFloat(), centerY + radius / 2)

    close()
}