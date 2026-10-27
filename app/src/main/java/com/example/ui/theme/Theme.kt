package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ==============================================================================
// HexClad Material 3 Color Schemes
// Enforces a signature dark luxury palette across all screens by default
// ==============================================================================

/**
 * Signature Dark Luxury Color Scheme for HexClad:
 * - Deep Charcoal (#121212) foundation
 * - Metallic Warm Gold (#C5A059) primary accents
 * - Announcement Green (#0A382C) promotional/tertiary highlights
 * - Pure White (#FFFFFF) high-contrast text and CTAs
 * - Muted Grey (#8E8E93) subtle borders and specifications
 */
val HexCladDarkColorScheme: ColorScheme = darkColorScheme(
    primary = MetallicWarmGold,
    onPrimary = DeepCharcoal,
    primaryContainer = MetallicWarmGoldContainer,
    onPrimaryContainer = MetallicWarmGoldLight,

    secondary = PureWhite,
    onSecondary = DeepCharcoal,
    secondaryContainer = CharcoalCard,
    onSecondaryContainer = PureWhite,

    tertiary = AnnouncementGreen,
    onTertiary = PureWhite,
    tertiaryContainer = AnnouncementGreenContainer,
    onTertiaryContainer = AnnouncementGreenText,

    background = DeepCharcoal,
    onBackground = PureWhite,

    surface = DeepCharcoal,
    onSurface = PureWhite,
    surfaceVariant = CharcoalSurfaceVariant,
    onSurfaceVariant = MutedGrey,
    surfaceContainer = CharcoalSurface,
    surfaceContainerHigh = CharcoalCard,
    surfaceContainerHighest = CharcoalContainer,

    outline = CharcoalBorder,
    outlineVariant = BorderGrey,

    error = LuxurySaleBadge,
    onError = PureWhite
)

/**
 * Clean Light Luxury Color Scheme preserving the prestigious HexClad brand accents.
 */
val HexCladLightColorScheme: ColorScheme = lightColorScheme(
    primary = MetallicWarmGoldDark,
    onPrimary = PureWhite,
    primaryContainer = MetallicWarmGoldLight,
    onPrimaryContainer = DeepCharcoal,

    secondary = DeepCharcoal,
    onSecondary = PureWhite,
    secondaryContainer = LightGrey,
    onSecondaryContainer = DeepCharcoal,

    tertiary = AnnouncementGreen,
    onTertiary = PureWhite,
    tertiaryContainer = AnnouncementGreenLight,
    onTertiaryContainer = PureWhite,

    background = PureWhite,
    onBackground = DeepCharcoal,

    surface = PureWhite,
    onSurface = DeepCharcoal,
    surfaceVariant = Color(0xFFF2F2F7),
    onSurfaceVariant = DarkGrey,

    outline = LightGrey,
    outlineVariant = Color(0xFFE5E5EA),

    error = LuxurySaleBadge,
    onError = PureWhite
)

/**
 * Central access point for HexClad luxury design tokens.
 *
 * Example usage:
 * ```kotlin
 * Surface(
 *     color = HexCladTheme.colors.charcoalCard,
 *     shape = HexCladTheme.shapes.medium
 * ) {
 *     Text(
 *         text = "PATENTED HYBRID COOKWARE",
 *         color = HexCladTheme.colors.metallicWarmGold,
 *         style = HexCladTheme.typography.labelLarge
 *     )
 * }
 * ```
 */
object HexCladTheme {
    val colors: HexCladCustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalHexCladColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}

/**
 * Main HexClad Theme Composable.
 *
 * Applies the dark luxury color scheme, typography, and Material 3 shapes
 * across all screens by default ([darkTheme] = true).
 *
 * @param darkTheme Enforces dark luxury palette by default.
 * @param dynamicColor Kept false by default to preserve the tailored HexClad luxury branding.
 * @param content The composable content wrapped in the HexClad luxury theme.
 */
@Composable
fun HexCladTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme: ColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> HexCladDarkColorScheme
        else -> HexCladLightColorScheme
    }

    val customColors = HexCladCustomColors()

    // Configure system status & navigation bar edge-to-edge styling
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = DeepCharcoal.toArgb()
                window.navigationBarColor = DeepCharcoal.toArgb()
                val insetsController = WindowCompat.getInsetsController(window, view)
                // For dark luxury theme, icons should be light (isAppearanceLight = false)
                insetsController.isAppearanceLightStatusBars = !darkTheme
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalHexCladColors provides customColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = HexCladTypography,
            shapes = HexCladShapes,
            content = content
        )
    }
}

/**
 * Backward compatibility alias for the default application template theme.
 */
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    HexCladTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
}
