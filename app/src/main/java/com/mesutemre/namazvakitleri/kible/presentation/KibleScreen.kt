package com.mesutemre.namazvakitleri.kible.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.kible.domain.listener.SensorDataManager
import com.mesutemre.namazvakitleri.kible.domain.model.SensorData
import com.mesutemre.namazvakitleri.ui.components.NamazvakitleriSurface
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun KibleScreen() {
    NamazvakitleriSurface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        var data by remember {
            mutableStateOf<SensorData?>(null)
        }

        DisposableEffect(Unit) {
            val dataManager = SensorDataManager(context)
            dataManager.init()

            val job = scope.launch {
                dataManager.data
                    .receiveAsFlow()
                    .onEach {
                        data = it
                    }
                    .collect()
            }

            onDispose {
                dataManager.cancel()
                job.cancel()
            }
        }



        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NamazvakitleriTheme.colors.uiBackground),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.compass),
                modifier = Modifier
                    .size(256.sdp)
                    .rotate(data?.let {
                        (-it.azimut * 360 / (2 * 3.14159f)) + 180f
                    } ?: 0f),
                contentDescription = stringResource(
                    id = R.string.settings_item_kible
                )
            )
        }
    }
}