package com.mesutemre.namazvakitleri.cumamesaj.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.cumamesaj.presentation.components.CumaMesajItem
import com.mesutemre.namazvakitleri.cumamesaj.presentation.components.CumaMesajListeErrorView
import com.mesutemre.namazvakitleri.cumamesaj.presentation.components.CumaMesajListeLoading
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun CumaMesajListeScreen(
    state: CumaMesajListeState,
    tryAgain: () -> Unit
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = NamazvakitleriTheme.colors.uiBackground)
        ) {
            when (state.cumaMesajListe) {
                is BaseResourceEvent.Loading -> {
                    CumaMesajListeLoading()
                }
                is BaseResourceEvent.Success -> {
                    state.cumaMesajListe.data?.let {
                        if (it.isNotEmpty()) {
                            it.forEachIndexed { index, cumaMesajData ->
                                CumaMesajItem(
                                    mesaj = cumaMesajData.mesaj,
                                    resimUrl = cumaMesajData.resimUrl
                                )
                                if (index < it.size - 1)
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = NamazvakitleriTheme.colors.searchTextHintColor,
                                        thickness = 1.sdp
                                    )
                            }
                        } else {
                            CumaMesajListeErrorView(onTryAgain = tryAgain)
                        }
                    }
                }
                is BaseResourceEvent.Error -> {
                    CumaMesajListeErrorView(onTryAgain = tryAgain)
                }
                else -> Unit
            }
        }
    }
}