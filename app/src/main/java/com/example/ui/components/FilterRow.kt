package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
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
 * Filter group categories for the luxury cookware catalog.
 */
val FilterCategories = listOf(
    "Product Type",
    "Piece Count",
    "Size",
    "Cooking Technique",
    "Price"
)

/**
 * Horizontal scroll row containing interactive multi-select filter dropdown pills.
 */
@Composable
fun FilterRow(
    activeFilters: Map<String, Set<String>>,
    onFilterPillClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .testTag("filter_row"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(FilterCategories) { filterCategory ->
            val selectedOptions = activeFilters[filterCategory] ?: emptySet()
            val hasActiveFilter = selectedOptions.isNotEmpty()

            Surface(
                color = if (hasActiveFilter) {
                    HexCladTheme.colors.metallicWarmGoldContainer
                } else {
                    HexCladTheme.colors.charcoalCard
                },
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(
                    1.dp,
                    if (hasActiveFilter) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                ),
                modifier = Modifier
                    .clickable { onFilterPillClick(filterCategory) }
                    .testTag("filter_pill_$filterCategory")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = filterCategory.uppercase(),
                        style = HexCladTheme.typography.labelSmall.copy(
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        ),
                        fontWeight = if (hasActiveFilter) FontWeight.Bold else FontWeight.Medium,
                        color = if (hasActiveFilter) {
                            HexCladTheme.colors.metallicWarmGoldLight
                        } else {
                            HexCladTheme.colors.pureWhite
                        }
                    )

                    if (hasActiveFilter) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Badge(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ) {
                            Text(
                                text = selectedOptions.size.toString(),
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown",
                        tint = if (hasActiveFilter) {
                            HexCladTheme.colors.metallicWarmGold
                        } else {
                            HexCladTheme.colors.mutedGrey
                        },
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
