package com.edwinkapkei.formula1.views.theme

import androidx.compose.ui.graphics.Color

val PrimaryBlue = Color(0xFF0B99CD)
val Gray = Color(0XFF121212)
val LightGray = Color(0xFF313131)

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val secondary: Color,
    val text: Color,
) {
    data object Night : ThemeColors(
        background = Gray,
        surface = LightGray,
        primary = PrimaryBlue,
        secondary = PrimaryBlue,
        text = Color.White,
    )

    data object Day : ThemeColors(
        background = Color.White,
        surface = Color.White,
        primary = PrimaryBlue,
        secondary = PrimaryBlue,
        text = Color.Black,
    )
}