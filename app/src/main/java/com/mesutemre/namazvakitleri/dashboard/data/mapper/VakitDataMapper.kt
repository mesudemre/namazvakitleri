package com.mesutemre.namazvakitleri.dashboard.data.mapper

import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.VakitInfoDto
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoTypeData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class VakitDataMapper @Inject constructor() {

    fun convertVakitDtoToVakitData(vakitInfoDto: VakitInfoDto): VakitInfoData {
        return VakitInfoData(
            imsak = VakitInfoTypeData(
                vakitLabel = R.string.vakit_imsak,
                type = VakitType.IMSAK,
                saat = vakitInfoDto.imsak,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.yatsi
                )
            ),
            gunes = VakitInfoTypeData(
                vakitLabel = R.string.vakit_gunes,
                type = VakitType.GUNES,
                saat = vakitInfoDto.gunes,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.imsak
                )
            ),
            ogle = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ogle,
                type = VakitType.OGLE,
                saat = vakitInfoDto.ogle,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.gunes
                )
            ),
            ikindi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ikindi,
                type = VakitType.IKINDI,
                saat = vakitInfoDto.ikindi,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.ogle
                )
            ),
            aksam = VakitInfoTypeData(
                vakitLabel = R.string.vakit_aksam,
                type = VakitType.AKSAM,
                saat = vakitInfoDto.aksam,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.ikindi
                )
            ),
            yatsi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_yatsi,
                type = VakitType.YATSI,
                saat = vakitInfoDto.yatsi,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoDto.miladiTarihKisa,
                    oncekiVakitSaat = vakitInfoDto.aksam
                )
            ),
            hicriTakvimInfo = vakitInfoDto.hicriTarihUzun,
            miladiTakvimInfo = vakitInfoDto.miladiTarihUzun,
            miladiTarih = vakitInfoDto.miladiTarihKisa
        )
    }

    fun convertVakitInfoDataToVakitInfoEntity(vakitInfoData: VakitInfoData): VakitInfoEntity {
        return VakitInfoEntity(
            imsak = vakitInfoData.imsak.saat,
            gunes = vakitInfoData.gunes.saat,
            ogle = vakitInfoData.ogle.saat,
            ikindi = vakitInfoData.ikindi.saat,
            aksam = vakitInfoData.aksam.saat,
            yatsi = vakitInfoData.yatsi.saat,
            miladiTarih = vakitInfoData.miladiTarih,
            hicriTarihUzun = vakitInfoData.hicriTakvimInfo,
            miladiTarihUzun = vakitInfoData.miladiTakvimInfo
        )
    }

    fun convertVakitInfoEntityToVakitInfoData(vakitInfoEntity: VakitInfoEntity): VakitInfoData {
        return VakitInfoData(
            imsak = VakitInfoTypeData(
                vakitLabel = R.string.vakit_imsak,
                type = VakitType.IMSAK,
                saat = vakitInfoEntity.imsak,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.yatsi
                )
            ),
            gunes = VakitInfoTypeData(
                vakitLabel = R.string.vakit_gunes,
                type = VakitType.GUNES,
                saat = vakitInfoEntity.gunes,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.imsak
                )
            ),
            ogle = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ogle,
                type = VakitType.OGLE,
                saat = vakitInfoEntity.ogle,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.gunes
                )
            ),
            ikindi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ikindi,
                type = VakitType.IKINDI,
                saat = vakitInfoEntity.ikindi,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.ogle
                )
            ),
            aksam = VakitInfoTypeData(
                vakitLabel = R.string.vakit_aksam,
                type = VakitType.AKSAM,
                saat = vakitInfoEntity.aksam,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.ikindi
                )
            ),
            yatsi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_yatsi,
                type = VakitType.YATSI,
                saat = vakitInfoEntity.yatsi,
                isActive = controlIsVakitActive(
                    vakitTarih = vakitInfoEntity.miladiTarih,
                    oncekiVakitSaat = vakitInfoEntity.aksam
                )
            ),
            hicriTakvimInfo = vakitInfoEntity.hicriTarihUzun,
            miladiTakvimInfo = vakitInfoEntity.miladiTarihUzun,
            miladiTarih = vakitInfoEntity.miladiTarih
        )
    }

    private fun controlIsVakitActive(
        vakitTarih: String,
        oncekiVakitSaat: String
    ): Boolean {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val current = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter)
        val oncekiVakit = LocalDateTime.parse("$vakitTarih $oncekiVakitSaat", formatter)
        return current.isAfter(oncekiVakit)
    }
}