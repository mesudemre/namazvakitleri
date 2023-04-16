package com.mesutemre.namazvakitleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition(object : SplashScreen.KeepOnScreenCondition {
                override fun shouldKeepOnScreen(): Boolean {
                    //TODO : Burada splash conditionu yazılacak
                    return false
                }
            })
        }
        setContent {
            NamazvakitleriApp(NamazvakitleriNavigationItem.OnboardingWelcomeScreen)
        }
    }
}