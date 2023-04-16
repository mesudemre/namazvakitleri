package com.mesutemre.namazvakitleri.onboarding.presentation.city

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun OnboardingRowItem(
    modifier: Modifier,
    text: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(12.sdp),
            style = NamazvakitleriTheme.typography.title,
            color = NamazvakitleriTheme.colors.searchTextBackgroundColor
        )
        Icon(
            modifier = Modifier.size(24.sdp),
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "",
            tint = NamazvakitleriTheme.colors.searchTextBackgroundColor
        )
    }
}