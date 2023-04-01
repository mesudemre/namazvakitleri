package com.mesutemre.namazvakitleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NamazvakitleriApp(NamazvakitleriNavigationItem.ExampleScreen)
        }
    }
}