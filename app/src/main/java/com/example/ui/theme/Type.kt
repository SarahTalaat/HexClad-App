package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.R

// ==============================================================================
// Luxury Cookware Typography System (Material 3)
// Crafted for high-end culinary & hybrid cookware brands (HexClad aesthetic)
// ==============================================================================

/**
 * Bold, high-contrast serif font family for luxury culinary editorial headlines,
 * hero showcases, and prestigious brand titles.
 */
val LuxurySerifFontFamily: FontFamily = FontFamily(
    Font(R.font.playfair_display, FontWeight.Normal),
    Font(R.font.playfair_display, FontWeight.Medium),
    Font(R.font.playfair_display, FontWeight.SemiBold),
    Font(R.font.playfair_display, FontWeight.Bold)
)

/**
 * Clean, architectural sans-serif font family for sharp technical cookware specs,
 * high-contrast section titles, tracked action labels, and legible body text.
 */
val LuxurySansFontFamily: FontFamily = FontFamily(
    Font(R.font.inter, FontWeight.Normal),
    Font(R.font.inter, FontWeight.Medium),
    Font(R.font.inter, FontWeight.SemiBold),
    Font(R.font.inter, FontWeight.Bold)
)

// Aliases for brand compatibility
val HexCladSerifFamily = LuxurySerifFontFamily
val HexCladSansFamily = LuxurySansFontFamily

/**
 * Material 3 Typography definition engineered for a luxury cookware e-commerce experience:
 * - Bold, high-contrast serif & sans-serif display headlines
 * - Sharp, architectural section titles with deliberate tracking
 * - Clean, highly legible body copy with balanced line heights
 */
val LuxuryCookwareTypography = Typography(
    // --------------------------------------------------------------------------
    // 1. DISPLAY: Bold, High-Contrast Culinary & Hero Headlines
    // --------------------------------------------------------------------------
    displayLarge = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 54.sp,
        lineHeight = 62.sp,
        letterSpacing = (-0.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 42.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.25).sp
    ),
    displaySmall = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),

    // --------------------------------------------------------------------------
    // 2. HEADLINES: Bold Hero Titles, Collection Banners & Feature Highlights
    // --------------------------------------------------------------------------
    headlineLarge = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.15.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.15.sp
    ),

    // --------------------------------------------------------------------------
    // 3. TITLES: Sharp Section Titles & Cookware Product Headers
    // --------------------------------------------------------------------------
    titleLarge = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp
    ),
    titleSmall = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),

    // --------------------------------------------------------------------------
    // 4. BODY: Clean, Legible Cookware Descriptions & Technical Specs
    // --------------------------------------------------------------------------
    bodyLarge = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 17.sp,
        letterSpacing = 0.4.sp
    ),

    // --------------------------------------------------------------------------
    // 5. LABELS: Sharp Uppercase Badges, Tracked Buttons & Premium Accents
    // --------------------------------------------------------------------------
    labelLarge = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 1.2.sp
    ),
    labelSmall = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 1.5.sp
    )
)

// Standard aliases for theme and system interoperability
val Typography = LuxuryCookwareTypography
val HexCladTypography = LuxuryCookwareTypography

/**
 * Specialized typographic presets tailored for cookware e-commerce elements
 * (laser-etched patent badges, Michelin chef endorsements, price tags).
 */
@Immutable
object CookwareTextStyles {
    /** Editorial quote from renowned chefs (e.g., Gordon Ramsay) */
    val ChefEndorsement: TextStyle = TextStyle(
        fontFamily = LuxurySerifFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.2.sp
    )

    /** Sharp, tracked uppercase badge (e.g., "PATENTED HYBRID TECHNOLOGY") */
    val TrackedBadge: TextStyle = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 1.75.sp
    )

    /** High-contrast price hero display (e.g., "$699") */
    val PriceHeadline: TextStyle = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.25).sp
    )

    /** Technical cookware spec label (e.g., "OVEN SAFE UP TO 500°F") */
    val CookwareSpec: TextStyle = TextStyle(
        fontFamily = LuxurySansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
}
