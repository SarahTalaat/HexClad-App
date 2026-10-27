package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Sticky Category Horizontal Sub-Bar implementing smooth horizontal navigation for:
 * Top Picks, Bakeware, Hybrid Cookware, Specialty Cookware, Knives/Cutting Boards,
 * HexMills, Kitchen Tools, HexWear, and Cocktail Shaker.
 */
@Composable
fun CategorySubBar(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        "Top Picks",
        "Bakeware",
        "Hybrid Cookware",
        "Specialty Cookware",
        "Knives/Cutting Boards",
        "HexMills",
        "Kitchen Tools",
        "HexWear",
        "Cocktail Shaker"
    )

    val listState = rememberLazyListState()

    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        tonalElevation = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            LazyRow(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("category_sub_bar"),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(categories) { category ->
                    val isSelected = category.equals(selectedCategory, ignoreCase = true)

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onCategorySelected(category) }
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .testTag("cat_tab_$category")
                    ) {
                        Text(
                            text = category.uppercase(),
                            style = HexCladTheme.typography.labelSmall.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.2.sp
                            ),
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) {
                                HexCladTheme.colors.pureWhite
                            } else {
                                HexCladTheme.colors.mutedGrey
                            }
                        )

                        // Active Indicator bar
                        Box(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .height(2.dp)
                                .fillMaxWidth()
                                .background(
                                    color = if (isSelected) {
                                        HexCladTheme.colors.metallicWarmGold
                                    } else {
                                        androidx.compose.ui.graphics.Color.Transparent
                                    },
                                    shape = RoundedCornerShape(1.dp)
                                )
                        )
                    }
                }
            }

            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
        }
    }
}
