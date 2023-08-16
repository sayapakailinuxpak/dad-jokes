package com.eldisprojects.dadjokes.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.eldisprojects.dadjokes.R

val DarkColorPalette = darkColors(
    primary = Night,
    primaryVariant = Night,
    secondary = Color.White,
    background = Night,
    surface = EerieBlack2
)
val LightColorPalette = lightColors(
    primary = Color(0xFFFFFFFF),
    primaryVariant = Color(0xFFFFFFFF),
    secondary = EerieBlack
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun DadJokesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = myTypography,
        shapes = Shapes,
        content = content
    )
}