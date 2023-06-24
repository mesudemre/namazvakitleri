package com.mesutemre.namazvakitleri.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

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
    welcomeBackground = listOf(BlackNeutral, AcikMavi, AcikMaviNeutral1),
    welcomeContinueText = BlackNeutral,
    searchTextHintColor = SecondaryGrey,
    searchTextColor = DarkGrey,
    searchTextBackgroundColor = White,
    searchTextBorderColor = AcikPembe,
    errorColor = Error,
    passiveStepper = SecondaryGrey,
    activeStepper = White,
    onboardingCompleteBackgroundColor = listOf(
        Black,
        BlackNeutral,
        BlackNeutral,
        BlackNeutral,
        Orange
    ),
    vakitInfoBackgroundColor = Lacivert,
    shareButtonColor = White,
    topBarIconColor = White,
    topBarBackgroundColor = DarkOrange,
    topBarTextColor = White,
    loadingIndicatorColor = White,
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
    welcomeBackground = listOf(White, Neutral1, Neutral2, AcikMavi, AcikMaviNeutral1),
    welcomeContinueText = White,
    searchTextHintColor = SecondaryGrey,
    searchTextColor = DarkGrey,
    searchTextBackgroundColor = White,
    searchTextBorderColor = AcikPembe,
    errorColor = Error,
    passiveStepper = SecondaryGrey,
    activeStepper = White,
    onboardingCompleteBackgroundColor = listOf(White, Neutral1, Neutral2, Neutral2, Orange),
    vakitInfoBackgroundColor = Lacivert,
    shareButtonColor = DarkGrey,
    topBarIconColor = White,
    topBarBackgroundColor = Orange,
    topBarTextColor = White,
    loadingIndicatorColor = PurpleGrey40,
    isDark = false
)

@Composable
fun NamazvakitleriTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

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
    val onboardingInfoTextStyle: TextStyle,
    val searchTextStyle: TextStyle,
    val errorTextStyle: TextStyle,
    val kalanSureSaatDakikaTextStyle: TextStyle,
    val kalanSureSaniyeTextStyle: TextStyle,
    val ayetHadisContent: TextStyle,
    val tarihInfoStyle: TextStyle,
    val tarihteBugunStyle: TextStyle,
    val kalanSureSaatDakikaStickHeaderTextStyle: TextStyle,
    val settingsDesignedByStyle: TextStyle
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
    searchTextHintColor: Color,
    searchTextColor: Color,
    searchTextBackgroundColor: Color,
    searchTextBorderColor: Color,
    errorColor: Color,
    passiveStepper: Color,
    activeStepper: Color,
    onboardingCompleteBackgroundColor: List<Color>,
    vakitInfoBackgroundColor: Color,
    shareButtonColor: Color,
    topBarIconColor: Color,
    topBarBackgroundColor: Color,
    topBarTextColor: Color,
    loadingIndicatorColor: Color,
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
    var searchTextHintColor by mutableStateOf(searchTextHintColor)
        private set
    var searchTextColor by mutableStateOf(searchTextColor)
        private set
    var searchTextBackgroundColor by mutableStateOf(searchTextBackgroundColor)
        private set
    var searchTextBorderColor by mutableStateOf(searchTextBorderColor)
        private set
    var errorColor by mutableStateOf(errorColor)
        private set
    var passiveStepper by mutableStateOf(passiveStepper)
        private set
    var activeStepper by mutableStateOf(activeStepper)
        private set
    var onboardingCompleteBackgroundColor by mutableStateOf(onboardingCompleteBackgroundColor)
        private set
    var vakitInfoBackgroundColor by mutableStateOf(vakitInfoBackgroundColor)
        private set
    var shareButtonColor by mutableStateOf(shareButtonColor)
        private set
    var topBarIconColor by mutableStateOf(topBarIconColor)
        private set
    var topBarBackgroundColor by mutableStateOf(topBarBackgroundColor)
        private set
    var topBarTextColor by mutableStateOf(topBarTextColor)
        private set
    var loadingIndicatorColor by mutableStateOf(loadingIndicatorColor)
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
        searchTextHintColor = other.searchTextHintColor
        searchTextColor = other.searchTextColor
        searchTextBackgroundColor = other.searchTextBackgroundColor
        searchTextBorderColor = other.searchTextBorderColor
        errorColor = other.errorColor
        passiveStepper = other.passiveStepper
        activeStepper = other.activeStepper
        onboardingCompleteBackgroundColor = other.onboardingCompleteBackgroundColor
        vakitInfoBackgroundColor = other.vakitInfoBackgroundColor
        shareButtonColor = other.shareButtonColor
        topBarIconColor = other.topBarIconColor
        topBarBackgroundColor = other.topBarBackgroundColor
        topBarTextColor = other.topBarTextColor
        loadingIndicatorColor = other.loadingIndicatorColor
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
        searchTextHintColor = searchTextHintColor,
        searchTextColor = searchTextColor,
        searchTextBackgroundColor = searchTextBackgroundColor,
        searchTextBorderColor = searchTextBorderColor,
        errorColor = errorColor,
        passiveStepper = passiveStepper,
        activeStepper = activeStepper,
        onboardingCompleteBackgroundColor = onboardingCompleteBackgroundColor,
        vakitInfoBackgroundColor = vakitInfoBackgroundColor,
        shareButtonColor = shareButtonColor,
        topBarIconColor = topBarIconColor,
        topBarBackgroundColor = topBarBackgroundColor,
        topBarTextColor = topBarTextColor,
        loadingIndicatorColor = loadingIndicatorColor,
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
