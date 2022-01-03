package com.example.bizarro.ui.theme

import android.annotation.SuppressLint
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
    onPrimary = kWhite,
    secondary = kBlack,
    secondaryVariant = kDarkGrey,
    onSecondary = kWhite,
    background = kBlack,
    onBackground = kWhite,
    surface = kDarkGrey,
    onSurface = kWhite,
    onError = kBlueLight,
    error = kLightGray
)


private val LightColorPalette = lightColors(

    primary = kBlueDark,
    primaryVariant = kBlueDark,
    onPrimary = kBlack,
    secondary = kWhite,
    secondaryVariant = kLightGray,
    onSecondary = kBlack,
    background = kWhite,
    onBackground = kBlack,
    surface = kWhite,
    onSurface = kBlack,
    onError = kBlueDark,
    error = kDarkGrey
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