package com.mesutemre.namazvakitleri.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
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
    contentColor = White,
    welcomeBackground = listOf(BlackNeutral,AcikMavi, AcikMaviNeutral1),
    welcomeContinueText = BlackNeutral,
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
    onBoardingBackground = FistikYesil,
    uiBackground = White,
    contentColor = Neutral7,
    welcomeBackground = listOf(White, Neutral1, Neutral2,AcikMavi, AcikMaviNeutral1),
    welcomeContinueText = White,
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

    ProvideNamazvakitleriTheme(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            content = content
        )
    }
}

object NamazvakitleriTheme {
    val colors: NamazvakitleriColors
        @Composable
        get() = LocalNamazvakitleriColors.current

    val typography: NamazVakitleriTypography
        @Composable
        get() = LocalNamazvakitleriTypography.current

    val shapes: NamazVakitleriShapes
        @Composable
        get() = LocalNamazvakitleriShapes.current
}


@Composable
fun ProvideNamazvakitleriTheme(
    colors: NamazvakitleriColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }

    colorPalette.update(colors)
    CompositionLocalProvider(
        LocalNamazvakitleriTypography provides namazVakitleriTypographyPalette,
        LocalNamazvakitleriShapes provides namazVakitleriShapePalette,
        LocalNamazvakitleriColors provides colorPalette, content = content
    )
}

@Immutable
data class NamazVakitleriTypography(
    val vakitInfo: TextStyle,
    val title: TextStyle,
    val ayetHadisTitle: TextStyle,
    val onboardingInfoTextStyle: TextStyle
)

@Immutable
data class NamazVakitleriShapes(
    val surface: Shape,
    val small: Shape,
    val circle: Shape,
    val medium: Shape,
    val large: Shape
)

private val LocalNamazvakitleriTypography = staticCompositionLocalOf<NamazVakitleriTypography> {
    error("No Namazvakitleri typo provided")
}

private val LocalNamazvakitleriColors = staticCompositionLocalOf<NamazvakitleriColors> {
    error("No Namazvakitleri provided")
}

val LocalNamazvakitleriShapes = staticCompositionLocalOf<NamazVakitleriShapes> {
    error("No Namazvakitleri shape provided")
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
    contentColor: Color,
    uiBackground: Color,
    welcomeBackground: List<Color>,
    welcomeContinueText: Color,
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
    var contentColor by mutableStateOf(contentColor)
        private set
    var isDark by mutableStateOf(isDark)
        private set
    var welcomeBackground by mutableStateOf(welcomeBackground)
        private set
    var welcomeContinueText by mutableStateOf(welcomeContinueText)
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
        contentColor = other.contentColor
        welcomeBackground = other.welcomeBackground
        welcomeContinueText = other.welcomeContinueText
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
        contentColor = contentColor,
        welcomeBackground = welcomeBackground,
        welcomeContinueText = welcomeContinueText,
        isDark = isDark
    )
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [Namazvakitleri.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
