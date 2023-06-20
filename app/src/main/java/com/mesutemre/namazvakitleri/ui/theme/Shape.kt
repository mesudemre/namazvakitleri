package com.mesutemre.namazvakitleri.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.mesutemre.namazvakitleri.core.ext.sdp

val namazVakitleriShapePalette = NamazVakitleriShapes(
    surface = RectangleShape,
    small = RoundedCornerShape(4.sdp),
    circle = CircleShape,
    medium = RoundedCornerShape(8.sdp),
    large = RoundedCornerShape(16.sdp)
)
