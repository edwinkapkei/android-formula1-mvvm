package com.edwinkapkei.formula1.views.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme =
    darkColorScheme(
        primary = ThemeColors.Night.primary,
        onPrimary = Color.White,
        surface = ThemeColors.Night.surface,
        background = ThemeColors.Night.background,
        secondary = ThemeColors.Night.secondary,
        onSecondary = Color.White,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = ThemeColors.Day.primary,
        onPrimary = Color.White,
        surface = ThemeColors.Day.surface,
        background = ThemeColors.Day.background,
        secondary = ThemeColors.Day.secondary,
        onSecondary = Color.White,
    )

@Composable
fun Formula1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}