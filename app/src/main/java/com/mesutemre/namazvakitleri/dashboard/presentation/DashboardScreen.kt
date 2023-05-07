package com.mesutemre.namazvakitleri.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.presentation.components.*
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun DashboardScreen(
    state: DashboardState
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.uiBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(290.sdp)
                    .background(NamazvakitleriTheme.colors.vakitInfoBackgroundColor)
            ) {
                state.selectedDistrict?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.sdp, top = 32.sdp),
                        text = it.districtName,
                        style = NamazvakitleriTheme.typography.vakitInfo,
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                } ?: run {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.sdp, top = 32.sdp),
                        text = "",
                        style = NamazvakitleriTheme.typography.vakitInfo,
                        color = NamazvakitleriTheme.colors.searchTextBackgroundColor
                    )
                }
                //TODO : Vakit hesaplaması yapılacak...
                when (state.vakitInfo) {
                    is BaseResourceEvent.Loading -> {
                        VakitInfoAreaLoading()
                    }
                    is BaseResourceEvent.Success -> {
                        VakitInfoArea(
                            sonrakiVakit = "12:34",
                            miladiTarihUzun = "2 Mayıs 2023",
                            hicriTarihUzun = "4 Muharrem 1445"
                        )
                    }
                    else -> Unit
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 270.sdp)
                    .clip(RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp))
                    .background(
                        color = NamazvakitleriTheme.colors.uiBackground,
                        shape = RoundedCornerShape(topStart = 12.sdp, topEnd = 12.sdp)
                    )
            ) {
                GodProphetArea()
                when (state.vakitInfo) {
                    is BaseResourceEvent.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.sdp, end = 8.sdp, top = 16.sdp)
                                .height(48.sdp)
                                .shimmerEffect()
                        )
                    }
                    is BaseResourceEvent.Success -> {
                        state.vakitInfo.data?.let { data ->
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 8.sdp, end = 8.sdp, top = 16.sdp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                val list = listOf(
                                    data.imsak,
                                    data.gunes,
                                    data.ogle,
                                    data.ikindi,
                                    data.aksam,
                                    data.yatsi
                                )
                                itemsIndexed(list, itemContent = { index, item ->
                                    if (index > 0 && index < list.size - 1)
                                        Spacer(modifier = Modifier.width(8.sdp))
                                    VakitInfoItem(
                                        vakitAd = stringResource(id = item.vakitLabel),
                                        saat = item.saat,
                                        isActive = item.isActive
                                    )
                                })
                            }
                        }
                    }
                    else -> Unit
                }
                when (state.gunlukHadis) {
                    is BaseResourceEvent.Loading -> {
                        HadisCardShimmer()
                    }
                    is BaseResourceEvent.Success -> {
                        HadisCard(
                            content = state.gunlukHadis.data?.content ?: "",
                            onShare = {})
                    }
                    else -> Unit
                }
            }
        }

    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(state = DashboardState())
}