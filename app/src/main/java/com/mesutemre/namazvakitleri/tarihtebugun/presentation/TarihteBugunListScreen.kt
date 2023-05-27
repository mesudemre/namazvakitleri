package com.mesutemre.namazvakitleri.tarihtebugun.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    state.tarihteBugunList.data?.let { list ->
                        val size = list.size
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(list) { index, item ->
                                TarihteBugunItem(item = item)
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