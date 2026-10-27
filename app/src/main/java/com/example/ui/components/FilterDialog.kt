package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.HexCladTheme

/**
 * Filter options mapping for each filter group.
 */
val FilterOptionsMap: Map<String, List<String>> = mapOf(
    "Product Type" to listOf(
        "Dutch Ovens",
        "Egg Pans",
        "Griddles",
        "Woks",
        "Fry Pans",
        "Cookware Sets",
        "Chef Knives",
        "Pizza Steels"
    ),
    "Piece Count" to listOf(
        "1-Piece",
        "3-Piece",
        "6-Piece",
        "13-Piece"
    ),
    "Size" to listOf(
        "7-Inch",
        "8-Inch",
        "10-Inch",
        "12-Inch",
        "14-Inch",
        "16-Inch"
    ),
    "Cooking Technique" to listOf(
        "Searing & Browning",
        "Stir-Fry",
        "Simmering & Braising",
        "Roasting & Baking",
        "Outdoor Grilling"
    ),
    "Price" to listOf(
        "Under $150",
        "$150 - $250",
        "$250 - $400",
        "Over $400"
    )
)

/**
 * Filter Popup Dialog displaying multi-select checkbox lists for the selected filter group
 * with Clear All and Apply Filters action buttons.
 */
@Composable
fun FilterDialog(
    filterGroup: String,
    currentSelected: Set<String>,
    onDismiss: () -> Unit,
    onApply: (Set<String>) -> Unit,
    onClearAll: () -> Unit
) {
    val options = FilterOptionsMap[filterGroup] ?: emptyList()
    var tempSelected by remember { mutableStateOf(currentSelected) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            color = HexCladTheme.colors.charcoalCard,
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("filter_dialog_$filterGroup")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "FILTER BY",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = filterGroup,
                            style = HexCladTheme.typography.titleLarge,
                            color = HexCladTheme.colors.pureWhite
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Options list
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                ) {
                    items(options) { option ->
                        val isChecked = tempSelected.contains(option)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    tempSelected = if (isChecked) {
                                        tempSelected - option
                                    } else {
                                        tempSelected + option
                                    }
                                }
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    tempSelected = if (checked) {
                                        tempSelected + option
                                    } else {
                                        tempSelected - option
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = HexCladTheme.colors.metallicWarmGold,
                                    uncheckedColor = HexCladTheme.colors.mutedGrey,
                                    checkmarkColor = HexCladTheme.colors.deepCharcoal
                                )
                            )
                            Text(
                                text = option,
                                style = HexCladTheme.typography.bodyMedium,
                                color = if (isChecked) HexCladTheme.colors.pureWhite else HexCladTheme.colors.lightGrey,
                                fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(14.dp))

                // Action buttons: Clear All and Apply Filters
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            tempSelected = emptySet()
                            onClearAll()
                            onDismiss()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = HexCladTheme.colors.mutedGrey
                        ),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("clear_filter_button")
                    ) {
                        Text(
                            text = "CLEAR ALL",
                            style = HexCladTheme.typography.labelSmall
                        )
                    }

                    Button(
                        onClick = {
                            onApply(tempSelected)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1.5f)
                            .testTag("apply_filter_button")
                    ) {
                        Text(
                            text = "APPLY FILTERS (${tempSelected.size})",
                            style = HexCladTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
