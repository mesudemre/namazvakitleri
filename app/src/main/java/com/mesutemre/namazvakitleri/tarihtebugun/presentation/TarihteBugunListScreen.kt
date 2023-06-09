package com.mesutemre.namazvakitleri.tarihtebugun.presentation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.presentation.components.TarihteBugunDivider
import com.mesutemre.namazvakitleri.dashboard.presentation.components.TarihteBugunItem
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun TarihteBugunListScreen(state: TarihteBugunListState) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.uiBackground)
        ) {
            when (state.tarihteBugunList) {
                is BaseResourceEvent.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                }
                is BaseResourceEvent.Success -> {

                    val context = LocalContext.current
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                    }
                    state.tarihteBugunList.data?.let { list ->
                        val size = list.size
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.sdp)
                        ) {
                            itemsIndexed(list, key = { index, item ->
                                index
                            }) { index, item ->
                                TarihteBugunItem(item = item, isShareable = true) {
                                    sendIntent.apply {
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            it
                                        )
                                    }
                                    context.startActivity(Intent.createChooser(sendIntent, null))
                                }
                                if (index < size - 1)
                                    TarihteBugunDivider()
                            }
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}