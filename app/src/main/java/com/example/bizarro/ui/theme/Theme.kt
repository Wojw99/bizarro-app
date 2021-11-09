package com.example.bizarro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColors(
    primary = kBlueDark,
    primaryVariant = kBlueLight,
    secondary = kGray
)

private val LightColorPalette = lightColors(
    primary = kBlueDark,
    primaryVariant = kBlueLight,
    secondary = kGray,
    background = kWhite,
    surface = kWhite,
    onPrimary = kWhite,
    onSecondary = kBlack,
    onBackground = kBlack,
    onSurface = kBlack,
)

@Composable
fun BizarroTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}