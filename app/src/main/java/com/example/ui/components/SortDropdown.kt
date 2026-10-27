package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Supported sorting options for the cookware catalog.
 */
enum class SortOption(val displayName: String) {
    BEST_SELLING("Best Selling"),
    PRICE_LOW_HIGH("Price Low-High"),
    PRICE_HIGH_LOW("Price High-Low"),
    NEWEST("Newest")
}

/**
 * Sorting Dropdown menu displaying:
 * Price Low-High, Price High-Low, Best Selling, and Newest.
 */
@Composable
fun SortDropdown(
    selectedSort: SortOption,
    onSortSelected: (SortOption) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Surface(
            color = HexCladTheme.colors.charcoalCard,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier
                .clickable { expanded = true }
                .testTag("sort_dropdown_button")
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = null,
                    tint = HexCladTheme.colors.metallicWarmGold,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "SORT: ${selectedSort.displayName.uppercase()}",
                    style = HexCladTheme.typography.labelSmall.copy(
                        fontSize = 10.sp,
                        letterSpacing = 1.sp
                    ),
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = HexCladTheme.colors.mutedGrey,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(HexCladTheme.colors.charcoalCard)
                .testTag("sort_dropdown_menu")
        ) {
            SortOption.values().forEach { option ->
                val isSelected = option == selectedSort
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.displayName,
                            style = HexCladTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) {
                                HexCladTheme.colors.metallicWarmGold
                            } else {
                                HexCladTheme.colors.pureWhite
                            }
                        )
                    },
                    onClick = {
                        onSortSelected(option)
                        expanded = false
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = HexCladTheme.colors.pureWhite
                    )
                )
            }
        }
    }
}
