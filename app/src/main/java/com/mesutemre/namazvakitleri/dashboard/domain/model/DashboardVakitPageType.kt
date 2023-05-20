package com.mesutemre.namazvakitleri.dashboard.domain.model

enum class DashboardVakitPageType(val type: Int) {
    DEFAULT(0), SULEYMANIYE(1), CIRCLE(2);

    companion object {
        infix fun from(type: Int): DashboardVakitPageType? =
            values().firstOrNull { it.type == type }
    }
}