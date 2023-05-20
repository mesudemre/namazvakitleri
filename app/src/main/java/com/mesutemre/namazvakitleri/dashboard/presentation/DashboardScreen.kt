package com.mesutemre.namazvakitleri.dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mesutemre.namazvakitleri.core.Constants
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.VakitType
import com.mesutemre.namazvakitleri.dashboard.presentation.components.*
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun DashboardScreen(
    state: DashboardState
) {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        var timer by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
        LaunchedEffect(key1 = timer) {
            if (timer > 0) {
                delay(1000L)
                timer -= 1000L
            } else if ((timer % Constants.DashboardConstants.DAY_MIL_SEC % Constants.DashboardConstants.HOUR_MIL_SEC % Constants.DashboardConstants.MIN_MIL_SEC / Constants.DashboardConstants.SEC_MIL_SEC).toInt() == 0) {
                timer = Calendar.getInstance().timeInMillis
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.uiBackground)
        ) {
            val lazyListState = rememberLazyListState()
            LazyColumn(state = lazyListState) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
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
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.sdp)
                            .background(NamazvakitleriTheme.colors.vakitInfoBackgroundColor)
                    ) {
                        when (state.vakitInfo) {
                            is BaseResourceEvent.Loading -> {
                                VakitInfoAreaLoading()
                            }
                            is BaseResourceEvent.Success -> {
                                val data = state.vakitInfo.data
                                data?.let { vakitInfo ->
                                    val sonrakiVakit = remember {
                                        derivedStateOf {
                                            if (timer >= data.bugunVakitInfo.imsak.date && timer < data.bugunVakitInfo.gunes.date)
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
                                    VakitInfoArea(
                                        sonrakiVakit = sonrakiVakit.value,
                                        miladiTarihUzun = vakitInfo.bugunVakitInfo.miladiTakvimInfo,
                                        hicriTarihUzun = vakitInfo.bugunVakitInfo.hicriTakvimInfo
                                    )
                                }
                            }
                            else -> Unit
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-10).sdp)
                            .clip(RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp))
                            .background(
                                color = NamazvakitleriTheme.colors.uiBackground,
                                shape = RoundedCornerShape(topStart = 16.sdp, topEnd = 16.sdp)
                            ),
                    ) {
                        GodProphetArea()
                    }
                }
                item {
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
                                var timer by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
                                LaunchedEffect(key1 = timer) {
                                    if (timer > 0) {
                                        delay(1000L)
                                        timer -= 1000L
                                    } else if (timer.toInt() == 0) {
                                        timer = Calendar.getInstance().timeInMillis
                                    }
                                }
                                val currentVakit = remember {
                                    derivedStateOf {
                                        if (timer >= data.bugunVakitInfo.imsak.date && timer < data.bugunVakitInfo.gunes.date)
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
                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.sdp, end = 8.sdp, top = 8.sdp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val list = listOf(
                                        data.bugunVakitInfo.imsak,
                                        data.bugunVakitInfo.gunes,
                                        data.bugunVakitInfo.ogle,
                                        data.bugunVakitInfo.ikindi,
                                        data.bugunVakitInfo.aksam,
                                        data.bugunVakitInfo.yatsi
                                    )
                                    itemsIndexed(list, itemContent = { index, item ->
                                        if (index > 0)
                                            Spacer(modifier = Modifier.width(8.sdp))
                                        VakitInfoItem(
                                            vakitAd = stringResource(id = item.vakitLabel),
                                            saat = item.saat,
                                            isActive = item.type == currentVakit.value
                                        )
                                    })
                                }
                            }
                        }
                        else -> Unit
                    }
                }
                item {
                    when (state.gunlukAyet) {
                        is BaseResourceEvent.Loading -> {
                            HadisCardShimmer()
                        }
                        is BaseResourceEvent.Success -> {
                            AyetCard(
                                content = state.gunlukAyet.data?.content ?: "",
                                onShare = {})
                        }
                        else -> Unit
                    }
                }
                item {
                    when (state.gunlukHadis) {
                        is BaseResourceEvent.Loading -> {
                            HadisCardShimmer()
                        }
                        is BaseResourceEvent.Success -> {
                            HadisCard(
                                content = state.gunlukHadis.data?.content ?: "",
                                onShare = {})
                            Spacer(modifier = Modifier.height(16.sdp))
                        }
                        else -> Unit
                    }
                }
                item {
                    TarihteBugunCard(tarihteBugunData = state.tarihteBugunList) {

                    }
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