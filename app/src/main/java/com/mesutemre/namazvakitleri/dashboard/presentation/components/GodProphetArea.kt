package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp

@Composable
fun GodProphetArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.sdp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_god),
            contentDescription = "God",
            modifier = Modifier.size(48.sdp)
        )
        Image(
            painter = painterResource(id = R.drawable.mohammad),
            contentDescription = "Prophet",
            modifier = Modifier
                .padding(start = 16.sdp)
                .size(48.sdp)
        )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_mosque),
            contentDescription = "Mosque",
            modifier = Modifier
                .padding(start = 16.sdp)
                .size(48.sdp)
        )
    }
}