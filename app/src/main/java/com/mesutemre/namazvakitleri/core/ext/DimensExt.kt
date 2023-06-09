package com.mesutemre.namazvakitleri.core.ext

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.mesutemre.namazvakitleri.core.NamazVakitleriBaseApplication
import java.lang.Math.abs

val Float.sdp: Dp
    get() {
        val value = (this / 1.2f).toInt()
        val context = NamazVakitleriBaseApplication.instance
        val name = when {
            value in 1..599 -> "_${value}sdp"
            value > -60 && value < 0 -> "_minus${abs(value)}sdp"
            else -> null
        }
        return name?.let {
            val id = context.resources.getIdentifier(name, "dimen", context.packageName)
            val dimensionPx = context.resources.getDimension(id)
            val dp: Float = dimensionPx / context.resources.displayMetrics.density
            Dp(dp)
        } ?: this.dp
    }

val Int.sdp: Dp
    get() {
        val value = this.toInt()
        val context = NamazVakitleriBaseApplication.instance
        val name = when {
            value in 1..599 -> "_${value}sdp"
            value > -60 && value < 0 -> "_minus${abs(value)}sdp"
            else -> null
        }
        return name?.let {
            val id = context.resources.getIdentifier(name, "dimen", context.packageName)
            val dimensionPx = context.resources.getDimension(id)
            val dp: Float = dimensionPx / context.resources.displayMetrics.density
            Dp(dp)
        } ?: this.dp
    }

val Int.ssp: TextUnit
    @Composable
    get() {
        val value = this
        return with(LocalDensity.current) {
            value.sdp.toSp()
        }
    }