package com.edwinkapkei.formula1.views.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.edwinkapkei.formula1.R

object AppFont {
    val Titillium =
        FontFamily(
            Font(R.font.regular),
            Font(R.font.regular_italic, style = FontStyle.Italic),
            Font(R.font.medium, FontWeight.Medium),
            Font(R.font.medium_italic, FontWeight.Medium, style = FontStyle.Italic),
            Font(R.font.bold, FontWeight.Bold),
            Font(R.font.bold_italic, FontWeight.Bold, style = FontStyle.Italic),
        )
}

private val defaultTypography = Typography()
val Typography =
    Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = AppFont.Titillium),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = AppFont.Titillium),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = AppFont.Titillium),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = AppFont.Titillium),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = AppFont.Titillium),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = AppFont.Titillium),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = AppFont.Titillium),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = AppFont.Titillium),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = AppFont.Titillium),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = AppFont.Titillium),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = AppFont.Titillium),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = AppFont.Titillium),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = AppFont.Titillium),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = AppFont.Titillium),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = AppFont.Titillium),
    )