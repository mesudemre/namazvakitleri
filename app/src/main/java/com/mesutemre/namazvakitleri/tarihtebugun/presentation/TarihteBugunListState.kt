package com.mesutemre.namazvakitleri.tarihtebugun.presentation

import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData

data class TarihteBugunListState(
    val tarihteBugunList: BaseResourceEvent<List<TarihteBugunData>> = BaseResourceEvent.Loading()
)
