package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ==============================================================================
// HexClad Luxury Cookware Shapes
// Precise, architectural corner treatments matching laser-etched cookware
// ==============================================================================

val HexCladShapes = Shapes(
    // Tags, badges ("PATENTED HYBRID"), tiny indicators
    extraSmall = RoundedCornerShape(2.dp),

    // Luxury buttons ("ADD TO CART"), input fields, chip filters
    small = RoundedCornerShape(4.dp),

    // Product cards, review cards, announcement containers
    medium = RoundedCornerShape(8.dp),

    // Modal dialogs, bottom sheets, warranty banners
    large = RoundedCornerShape(12.dp),

    // Full-screen drawer surfaces, hero containers
    extraLarge = RoundedCornerShape(16.dp)
)

val Shapes = HexCladShapes
