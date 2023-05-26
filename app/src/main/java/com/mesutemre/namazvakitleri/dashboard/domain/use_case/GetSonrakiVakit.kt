package com.mesutemre.namazvakitleri.dashboard.domain.use_case

import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitInfoScreenData
import javax.inject.Inject

class GetSonrakiVakit @Inject constructor() {

    operator fun invoke(data: VakitInfoScreenData, timer: Long): Long {
        return if (timer >= data.bugunVakitInfo.imsak.date && timer < data.bugunVakitInfo.gunes.date)
            data.bugunVakitInfo.gunes.date
        else if (timer >= data.bugunVakitInfo.gunes.date && timer < data.bugunVakitInfo.ogle.date)
            data.bugunVakitInfo.ogle.date
        else if (timer >= data.bugunVakitInfo.ogle.date && timer < data.bugunVakitInfo.ikindi.date)
            data.bugunVakitInfo.ikindi.date
        else if (timer >= data.bugunVakitInfo.ikindi.date && timer < data.bugunVakitInfo.aksam.date)
            data.bugunVakitInfo.aksam.date
        else if (timer >= data.bugunVakitInfo.aksam.date && timer < data.bugunVakitInfo.yatsi.date)
            data.bugunVakitInfo.yatsi.date
        else if (timer > data.bugunVakitInfo.yatsi.date && timer < data.yarinVakitInfo.imsak.date)
            data.yarinVakitInfo.imsak.date
        else
            data.yarinVakitInfo.imsak.date
    }
}