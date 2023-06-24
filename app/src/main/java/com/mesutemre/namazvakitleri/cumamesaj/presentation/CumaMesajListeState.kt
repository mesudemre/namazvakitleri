package com.mesutemre.namazvakitleri.cumamesaj.presentation

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.cumamesaj.domain.model.CumaMesajData

data class CumaMesajListeState(
    val cumaMesajListe: BaseResourceEvent<List<CumaMesajData>> = BaseResourceEvent.Loading()
)
