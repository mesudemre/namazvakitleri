package com.mesutemre.namazvakitleri.cumamesaj.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shareTextAndImageContent
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
    val context = LocalContext.current
    var isSharing by remember {
        mutableStateOf(false)
    }
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = NamazvakitleriTheme.colors.uiBackground)) {
            if (isSharing) {
                CircularProgressIndicator(
                    color = NamazvakitleriTheme.colors.loadingIndicatorColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Column(
                Modifier
                    .alpha(if (isSharing) 0.4f else 1f)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
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
                                    ) {
                                        context.shareTextAndImageContent(
                                            imageUrl = cumaMesajData.resimUrl,
                                            message = cumaMesajData.mesaj,
                                            title = context.getString(R.string.settings_cuma_mesaj_share_title),
                                            onPrepareLoad = { sharing ->
                                                isSharing = sharing
                                            }
                                        )
                                    }
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
}