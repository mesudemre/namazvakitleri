package com.mesutemre.namazvakitleri.cumamesaj.data.mapper

import com.mesutemre.namazvakitleri.cumamesaj.data.remote.dto.CumaMesajDto
import com.mesutemre.namazvakitleri.cumamesaj.domain.model.CumaMesajData
import javax.inject.Inject

class CumaMesajDataMapper @Inject constructor() {

    fun convertCumaMesajDtoToCumaMesajData(data: CumaMesajDto): CumaMesajData {
        return CumaMesajData(
            resimUrl = data.resim,
            mesaj = data.mesaj + " ( Resim : ${data.resimAciklama} )"
        )
    }
}