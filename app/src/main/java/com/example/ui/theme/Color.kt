package com.example.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ==============================================================================
// HexClad Luxury E-Commerce Palette
// Canonical Colors as requested for https://hexclad.com/
// ==============================================================================

/** Deep Charcoal / Black - Signature HexClad dark luxury backdrop */
val DeepCharcoal = Color(0xFF121212)
val DeepBlack = Color(0xFF0A0A0A)

/** Pure White - Crisp contrast for high-end typography and call-to-actions */
val PureWhite = Color(0xFFFFFFFF)

/** Metallic Warm Gold - Premium accent for ratings, awards, and luxury highlights */
val MetallicWarmGold = Color(0xFFC5A059)
val MetallicWarmGoldLight = Color(0xFFDFBE76)
val MetallicWarmGoldDark = Color(0xFF9E7B36)
val MetallicWarmGoldContainer = Color(0xFF2B2211)
val MetallicWarmGoldGlow = Color(0x33C5A059)

/** Announcement Green - Iconic HexClad top banner & promo ribbon tone */
val AnnouncementGreen = Color(0xFF0A382C)
val AnnouncementGreenLight = Color(0xFF125241)
val AnnouncementGreenContainer = Color(0xFF06231B)
val AnnouncementGreenText = Color(0xFF7CE4C7)

/** Muted Grey - Refined secondary labels, specs, and understated borders */
val MutedGrey = Color(0xFF8E8E93)
val LightGrey = Color(0xFFC7C7CC)
val BorderGrey = Color(0xFF2C2C2E)
val DarkGrey = Color(0xFF48484A)

// Supplementary luxury dark surfaces
val CharcoalSurface = Color(0xFF181818)
val CharcoalCard = Color(0xFF1C1C1E)
val CharcoalSurfaceVariant = Color(0xFF242426)
val CharcoalContainer = Color(0xFF2C2C30)
val CharcoalBorder = Color(0xFF38383C)

// Semantic status & badges
val LuxurySaleBadge = Color(0xFFD32F2F)
val StarRatingGold = Color(0xFFFFB800)

/**
 * Custom semantic luxury colors providing direct, type-safe access
 * to HexClad-specific brand tokens via [HexCladTheme.colors].
 */
@Immutable
data class HexCladCustomColors(
    val deepCharcoal: Color = DeepCharcoal,
    val deepBlack: Color = DeepBlack,
    val pureWhite: Color = PureWhite,
    val metallicWarmGold: Color = MetallicWarmGold,
    val metallicWarmGoldLight: Color = MetallicWarmGoldLight,
    val metallicWarmGoldDark: Color = MetallicWarmGoldDark,
    val metallicWarmGoldContainer: Color = MetallicWarmGoldContainer,
    val metallicWarmGoldGlow: Color = MetallicWarmGoldGlow,
    val announcementGreen: Color = AnnouncementGreen,
    val announcementGreenLight: Color = AnnouncementGreenLight,
    val announcementGreenContainer: Color = AnnouncementGreenContainer,
    val announcementGreenText: Color = AnnouncementGreenText,
    val mutedGrey: Color = MutedGrey,
    val lightGrey: Color = LightGrey,
    val borderGrey: Color = BorderGrey,
    val darkGrey: Color = DarkGrey,
    val charcoalSurface: Color = CharcoalSurface,
    val charcoalCard: Color = CharcoalCard,
    val charcoalSurfaceVariant: Color = CharcoalSurfaceVariant,
    val charcoalContainer: Color = CharcoalContainer,
    val charcoalBorder: Color = CharcoalBorder,
    val luxurySaleBadge: Color = LuxurySaleBadge,
    val starRatingGold: Color = StarRatingGold,
)

val LocalHexCladColors = staticCompositionLocalOf { HexCladCustomColors() }
