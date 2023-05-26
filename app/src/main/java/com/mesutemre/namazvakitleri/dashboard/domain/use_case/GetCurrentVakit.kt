package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoScreenData
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitType
import javax.inject.Inject

class GetCurrentVakit @Inject constructor() {

    operator fun invoke(data: VakitInfoScreenData, timer: Long): VakitType {
        return if (timer >= data.bugunVakitInfo.imsak.date && timer < data.bugunVakitInfo.gunes.date)
            VakitType.IMSAK
        else if (timer >= data.bugunVakitInfo.gunes.date && timer < data.bugunVakitInfo.ogle.date)
            VakitType.GUNES
        else if (timer >= data.bugunVakitInfo.ogle.date && timer < data.bugunVakitInfo.ikindi.date)
            VakitType.OGLE
        else if (timer >= data.bugunVakitInfo.ikindi.date && timer < data.bugunVakitInfo.aksam.date)
            VakitType.IKINDI
        else if (timer >= data.bugunVakitInfo.aksam.date && timer < data.bugunVakitInfo.yatsi.date)
            VakitType.AKSAM
        else if (timer > data.bugunVakitInfo.yatsi.date && timer < data.yarinVakitInfo.imsak.date)
            VakitType.IMSAK
        else
            VakitType.IMSAK
    }
}