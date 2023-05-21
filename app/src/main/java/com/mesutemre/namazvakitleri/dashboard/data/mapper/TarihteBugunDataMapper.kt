package com.mesutemre.namazvakitleri.dashboard.data.mapper

import com.mesutemre.namazvakitleri.dashboard.data.local.entity.TarihteBugunEntity
import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunItemDto
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TarihteBugunDataMapper @Inject constructor() {

    fun convertTarihteBugunDtoToTarihteBugunData(item: TarihteBugunItemDto): TarihteBugunData {
        return TarihteBugunData(
            tarih = "${item.gun}.${item.ay}.${item.yil}",
            durum = item.durum,
            olay = item.olay
        )
    }

    fun convertTarihteBugunEntityToTarihteBugunData(tarihteBugunEntity: TarihteBugunEntity): TarihteBugunData {
        return TarihteBugunData(
            tarih = tarihteBugunEntity.tarih,
            olay = tarihteBugunEntity.olay,
            durum = tarihteBugunEntity.durum
        )
    }

    fun convertTarihteBugunDataToTarihteBugunEntity(data: TarihteBugunData): TarihteBugunEntity {
        return TarihteBugunEntity(
            durum = data.durum,
            olay = data.olay,
            tarih = data.tarih
        )
    }
}