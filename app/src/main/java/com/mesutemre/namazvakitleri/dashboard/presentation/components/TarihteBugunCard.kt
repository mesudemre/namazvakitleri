package com.mesutemre.namazvakitleri.dashboard.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.core.ext.shimmerEffect
import com.mesutemre.namazvakitleri.core.model.BaseResourceEvent
import com.mesutemre.namazvakitleri.dashboard.domain.model.TarihteBugunData
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun TarihteBugunCard(
    tarihteBugunData: BaseResourceEvent<List<TarihteBugunData>>,
    onClickAll: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.sdp),
        elevation = 8.sdp,
        shape = NamazvakitleriTheme.shapes.medium,
        backgroundColor = NamazvakitleriTheme.colors.uiBackground,
        contentColor = NamazvakitleriTheme.colors.uiBackground,
        border = BorderStroke(width = 1.sdp, color = NamazvakitleriTheme.colors.passiveStepper)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.sdp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.sdp, vertical = 8.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_history),
                        contentDescription = "Tarihte Bugün",
                        modifier = Modifier.size(32.sdp)
                    )
                    Text(
                        text = "Tarihte Bugün",
                        style = NamazvakitleriTheme.typography.ayetHadisTitle,
                        color = NamazvakitleriTheme.colors.normalVakit,
                        modifier = Modifier.padding(start = 12.sdp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Tarihte Bugün",
                    tint = NamazvakitleriTheme.colors.ayetHadisInfo,
                    modifier = Modifier
                        .size(32.sdp)
                        .clickable {
                            onClickAll()
                        })
            }
            when (tarihteBugunData) {
                is BaseResourceEvent.Loading -> {
                    TarihteBugünCardShimmer()
                }
                is BaseResourceEvent.Success -> {
                    tarihteBugunData.data?.let { list ->
                        val liste = remember {
                            derivedStateOf {
                                if (list.size > 2)
                                    list.take(3)
                                else
                                    list
                            }
                        }
                        Column(modifier = Modifier.fillMaxWidth()) {
                            liste.value.forEachIndexed { index, tarihteBugunItem ->
                                TarihteBugunItem(item = tarihteBugunItem)
                                if (index < 2)
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

@Composable
private fun TarihteBugünCardShimmer() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TarihteBugünCardItemShimmer()
        TarihteBugunDivider()
        TarihteBugünCardItemShimmer()
        TarihteBugunDivider()
        TarihteBugünCardItemShimmer()
    }
}

@Composable
fun TarihteBugunItem(
    item: TarihteBugunData,
    isShareable: Boolean = false,
    onShare: ((String) -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.sdp),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_researcher),
            modifier = Modifier.size(32.sdp),
            contentDescription = item.olay
        )
        Column(modifier = Modifier.padding(horizontal = 6.sdp).fillMaxWidth()) {
            Text(
                text = "${item.tarih} , ${item.durum}",
                style = NamazvakitleriTheme.typography.tarihteBugunStyle.copy(fontWeight = FontWeight.SemiBold),
                color = NamazvakitleriTheme.colors.normalVakit
            )
            Spacer(modifier = Modifier.height(4.sdp))
            Text(
                text = item.olay,
                style = NamazvakitleriTheme.typography.tarihteBugunStyle,
                color = NamazvakitleriTheme.colors.normalVakit
            )
            if (isShareable) {
                Row(modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.sdp)
                    .clickable {
                        onShare?.let {
                            it("${item.tarih} tarihinde : ${item.olay}")
                        }
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_share),
                        contentDescription = "Share",
                        modifier = Modifier
                            .size(24.sdp),
                        tint = NamazvakitleriTheme.colors.shareButtonColor
                    )
                    Text(
                        text = "Paylaş",
                        style = NamazvakitleriTheme.typography.ayetHadisContent,
                        color = NamazvakitleriTheme.colors.shareButtonColor,
                        modifier = Modifier.padding(start = 6.sdp)
                    )
                }
            }

        }
    }
}

@Composable
private fun TarihteBugünCardItemShimmer() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.sdp)
                .background(
                    color = NamazvakitleriTheme.colors.uiBackground,
                    shape = NamazvakitleriTheme.shapes.circle
                )
                .clip(shape = NamazvakitleriTheme.shapes.circle)
                .shimmerEffect()
        )
        Column(modifier = Modifier.padding(horizontal = 6.sdp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.sdp)
                    .background(
                        color = NamazvakitleriTheme.colors.uiBackground,
                        shape = NamazvakitleriTheme.shapes.medium
                    )
                    .clip(shape = NamazvakitleriTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.sdp))
            Spacer(
                modifier = Modifier
                    .width(180.sdp)
                    .height(6.sdp)
                    .background(
                        color = NamazvakitleriTheme.colors.uiBackground,
                        shape = NamazvakitleriTheme.shapes.medium
                    )
                    .clip(shape = NamazvakitleriTheme.shapes.medium)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(8.sdp))
            Spacer(
                modifier = Modifier
                    .width(100.sdp)
                    .height(6.sdp)
                    .background(
                        color = NamazvakitleriTheme.colors.uiBackground,
                        shape = NamazvakitleriTheme.shapes.medium
                    )
                    .clip(shape = NamazvakitleriTheme.shapes.medium)
                    .shimmerEffect()
            )
        }
    }
}

@Composable
fun TarihteBugunDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.sdp, vertical = 6.sdp),
        color = NamazvakitleriTheme.colors.searchTextHintColor
    )
}