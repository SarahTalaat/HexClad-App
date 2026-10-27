package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Specialty Cookware Category Chips providing quick filtering for:
 * SETS, BBQ, PIZZA STEEL, ROASTING PANS, DOUBLE BURNER GRIDDLE, and JAPANESE-STYLE COOKWARE.
 */
@Composable
fun CategorySidebar(
    selectedChip: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val specialtyChips = listOf(
        "SETS",
        "BBQ",
        "PIZZA STEEL",
        "ROASTING PANS",
        "DOUBLE BURNER GRIDDLE",
        "JAPANESE-STYLE COOKWARE"
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .testTag("category_sidebar_chips"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(specialtyChips) { chip ->
            val isSelected = chip.equals(selectedChip, ignoreCase = true)

            Surface(
                color = if (isSelected) {
                    HexCladTheme.colors.metallicWarmGoldContainer
                } else {
                    HexCladTheme.colors.charcoalCard
                },
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(
                    1.dp,
                    if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                ),
                modifier = Modifier
                    .clickable { onCategorySelected(chip) }
                    .testTag("chip_$chip")
            ) {
                Text(
                    text = chip,
                    style = HexCladTheme.typography.labelSmall.copy(
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    ),
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) {
                        HexCladTheme.colors.metallicWarmGoldLight
                    } else {
                        HexCladTheme.colors.lightGrey
                    },
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }
        }
    }
}
