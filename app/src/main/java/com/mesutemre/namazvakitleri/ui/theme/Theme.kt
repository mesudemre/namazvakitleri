package com.mesutemre.namazvakitleri.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = NamazvakitleriColors(
    gradientCircle = listOf(Pink, Yellow),
    vakitInfo = White,
    kalanSure = White,
    currentVakit = Orange,
    normalVakit = White,
    ayetHadisInfo = White,
    vakitPanelBackground = BlackNeutral,
    ilIlceAd = White,
    onBoardingBackground = KoyuYesil,
    uiBackground = BlackNeutral,
    isDark = true
)

private val LightColorPalette = NamazvakitleriColors(
    gradientCircle = listOf(Pink, Yellow),
    vakitInfo = White,
    kalanSure = White,
    currentVakit = Orange,
    normalVakit = Black,
    ayetHadisInfo = BlackNeutral,
    vakitPanelBackground = White,
    ilIlceAd = White,
    uiBackground = White,
    onBoardingBackground = FistikYesil,
    isDark = false
)

@Composable
fun NamazvakitleriTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideNamazvakitleriColors(colors) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object NamazvakitleriTheme {
    val colors: NamazvakitleriColors
        @Composable
        get() = LocalNamazvakitleriColors.current
}

@Composable
fun ProvideNamazvakitleriColors(
    colors: NamazvakitleriColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalNamazvakitleriColors provides colorPalette, content = content)
}

private val LocalNamazvakitleriColors = staticCompositionLocalOf<NamazvakitleriColors> {
    error("No Namazvakitleri provided")
}

/**
 * Namazvakitleri custom Color Palette
 */
@Stable
class NamazvakitleriColors(
    gradientCircle: List<Color>,
    vakitInfo: Color,
    kalanSure: Color,
    currentVakit: Color,
    vakitPanelBackground: Color,
    normalVakit: Color,
    ayetHadisInfo: Color,
    ilIlceAd: Color,
    onBoardingBackground: Color,
    uiBackground: Color,
    isDark: Boolean
) {
    var gradientCircle by mutableStateOf(gradientCircle)
        private set
    var vakitInfo by mutableStateOf(vakitInfo)
        private set
    var kalanSure by mutableStateOf(kalanSure)
        private set
    var currentVakit by mutableStateOf(currentVakit)
        private set
    var vakitPanelBackground by mutableStateOf(vakitPanelBackground)
        private set
    var normalVakit by mutableStateOf(normalVakit)
        private set
    var ayetHadisInfo by mutableStateOf(ayetHadisInfo)
        private set
    var ilIlceAd by mutableStateOf(ilIlceAd)
        private set
    var onBoardingBackground by mutableStateOf(onBoardingBackground)
        private set
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: NamazvakitleriColors) {
        gradientCircle = other.gradientCircle
        vakitInfo = other.vakitInfo
        kalanSure = other.kalanSure
        currentVakit = other.currentVakit
        normalVakit = other.normalVakit
        ayetHadisInfo = other.ayetHadisInfo
        vakitPanelBackground = other.vakitPanelBackground
        ilIlceAd = other.ilIlceAd
        onBoardingBackground = other.onBoardingBackground
        uiBackground = other.uiBackground
        isDark = other.isDark
    }

    fun copy(): NamazvakitleriColors = NamazvakitleriColors(
        gradientCircle = gradientCircle,
        vakitInfo = vakitInfo,
        kalanSure = kalanSure,
        currentVakit = currentVakit,
        normalVakit = normalVakit,
        ayetHadisInfo = ayetHadisInfo,
        vakitPanelBackground = vakitPanelBackground,
        ilIlceAd = ilIlceAd,
        onBoardingBackground = onBoardingBackground,
        uiBackground = uiBackground,
        isDark = isDark
    )
}

