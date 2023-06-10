package com.mesutemre.namazvakitleri.dashboard.data.mapper

import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity
import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.VakitInfoDto
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoTypeData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitType
import java.util.*
import javax.inject.Inject

class VakitDataMapper @Inject constructor() {

    fun convertVakitDtoToVakitData(vakitInfoDto: VakitInfoDto): VakitInfoData {
        return VakitInfoData(
            imsak = VakitInfoTypeData(
                vakitLabel = R.string.vakit_imsak,
                type = VakitType.IMSAK,
                saat = vakitInfoDto.imsak,
                date = getDateOfVakit(vakitInfoDto.imsak, vakitInfoDto.miladiTarihKisa)
            ),
            gunes = VakitInfoTypeData(
                vakitLabel = R.string.vakit_gunes,
                type = VakitType.GUNES,
                saat = vakitInfoDto.gunes,
                date = getDateOfVakit(vakitInfoDto.gunes, vakitInfoDto.miladiTarihKisa)
            ),
            ogle = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ogle,
                type = VakitType.OGLE,
                saat = vakitInfoDto.ogle,
                date = getDateOfVakit(vakitInfoDto.ogle, vakitInfoDto.miladiTarihKisa)
            ),
            ikindi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ikindi,
                type = VakitType.IKINDI,
                saat = vakitInfoDto.ikindi,
                date = getDateOfVakit(vakitInfoDto.ikindi, vakitInfoDto.miladiTarihKisa)
            ),
            aksam = VakitInfoTypeData(
                vakitLabel = R.string.vakit_aksam,
                type = VakitType.AKSAM,
                saat = vakitInfoDto.aksam,
                date = getDateOfVakit(vakitInfoDto.aksam, vakitInfoDto.miladiTarihKisa)
            ),
            yatsi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_yatsi,
                type = VakitType.YATSI,
                saat = vakitInfoDto.yatsi,
                date = getDateOfVakit(vakitInfoDto.yatsi, vakitInfoDto.miladiTarihKisa)
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
                date = getDateOfVakit(vakitInfoEntity.imsak, vakitInfoEntity.miladiTarih)
            ),
            gunes = VakitInfoTypeData(
                vakitLabel = R.string.vakit_gunes,
                type = VakitType.GUNES,
                saat = vakitInfoEntity.gunes,
                date = getDateOfVakit(vakitInfoEntity.gunes, vakitInfoEntity.miladiTarih)
            ),
            ogle = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ogle,
                type = VakitType.OGLE,
                saat = vakitInfoEntity.ogle,
                date = getDateOfVakit(vakitInfoEntity.ogle, vakitInfoEntity.miladiTarih)
            ),
            ikindi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_ikindi,
                type = VakitType.IKINDI,
                saat = vakitInfoEntity.ikindi,
                date = getDateOfVakit(vakitInfoEntity.ikindi, vakitInfoEntity.miladiTarih)
            ),
            aksam = VakitInfoTypeData(
                vakitLabel = R.string.vakit_aksam,
                type = VakitType.AKSAM,
                saat = vakitInfoEntity.aksam,
                date = getDateOfVakit(vakitInfoEntity.aksam, vakitInfoEntity.miladiTarih)
            ),
            yatsi = VakitInfoTypeData(
                vakitLabel = R.string.vakit_yatsi,
                type = VakitType.YATSI,
                saat = vakitInfoEntity.yatsi,
                date = getDateOfVakit(vakitInfoEntity.yatsi, vakitInfoEntity.miladiTarih)
            ),
            hicriTakvimInfo = vakitInfoEntity.hicriTarihUzun,
            miladiTakvimInfo = vakitInfoEntity.miladiTarihUzun,
            miladiTarih = vakitInfoEntity.miladiTarih
        )
    }

    private fun getDateOfVakit(
        vakit: String,
        tarih: String
    ): Long {
        val saat = vakit.split(":")[0]
        val dakika = vakit.split(":")[1]
        val gun = tarih.split(".")[0]
        val cal = Calendar.getInstance()
        cal.set(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            gun.toInt(),
            saat.toInt(),
            dakika.toInt(),
            0
        )
        return cal.timeInMillis
    }
}