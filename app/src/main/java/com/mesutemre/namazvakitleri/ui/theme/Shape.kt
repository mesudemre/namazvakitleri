package com.mesutemre.namazvakitleri.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import com.mesutemre.namazvakitleri.core.ext.sdp

val namazVakitleriShapePalette = NamazVakitleriShapes(
    surface = RectangleShape,
    small = RoundedCornerShape(4.sdp),
    circle = CircleShape,
    medium = RoundedCornerShape(8.sdp),
    large = RoundedCornerShape(16.sdp)
)