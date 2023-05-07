package com.mesutemre.namazvakitleri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.mesutemre.namazvakitleri.navigation.NamazvakitleriNavigationItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition(object : SplashScreen.KeepOnScreenCondition {
                override fun shouldKeepOnScreen(): Boolean {
                    return viewModel.loading.value
                }
            })
        }
        setContent {
            NamazvakitleriApp(
                /*if (viewModel.startDashboard.value) NamazvakitleriNavigationItem.DashboardScreen
                else
                    NamazvakitleriNavigationItem.OnboardingWelcomeScreen */
                NamazvakitleriNavigationItem.DashboardScreen
            )
        }
    }
}