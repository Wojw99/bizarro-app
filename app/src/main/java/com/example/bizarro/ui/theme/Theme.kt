package com.example.bizarro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = kBlue500,
    primaryVariant = kBlue700,
    secondary = kDarkGray
)

private val LightColorPalette = lightColors(
    primary = kBlue500,
    primaryVariant = kBlue700,
    secondary = kDarkGray,
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