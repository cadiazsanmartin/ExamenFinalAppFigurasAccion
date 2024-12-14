package com.example.appfigurasaccion.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryTextColor,
    onPrimary = TextIconsColor,
    surface = DividerColor,
)

@Composable
fun AppFigurasAccionTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography,
        content = content
    )
}
