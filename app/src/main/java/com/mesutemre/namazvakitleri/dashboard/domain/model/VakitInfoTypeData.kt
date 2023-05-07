package com.mesutemre.namazvakitleri.dashboard.domain.model

import androidx.annotation.StringRes

data class VakitInfoTypeData(
    @StringRes val vakitLabel: Int,
    val saat: String,
    val type: VakitType,
    val isActive: Boolean = false
)

