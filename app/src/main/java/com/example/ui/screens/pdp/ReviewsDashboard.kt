package com.example.ui.screens.pdp

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Reviews Summary & Dashboard showing:
 * - Overall rating score (4.81/5)
 * - Visual star breakdown progress bars (5-star down to 1-star)
 * - "Write a Review" button
 * - Search reviews input field
 * - Sorting dropdown menu
 */
@Composable
fun ReviewsDashboard(
    overallScore: Double = 4.81,
    totalReviews: Int = 89450,
    searchQuery: String = "",
    onSearchChange: (String) -> Unit = {},
    selectedSort: String = "Most Recent",
    onSortChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isSortExpanded by remember { mutableStateOf(false) }

    val sortOptions = listOf("Most Recent", "Highest Rating", "Lowest Rating", "With Photos")

    val starBreakdowns = listOf(
        Pair("5 Stars", 0.88f),
        Pair("4 Stars", 0.08f),
        Pair("3 Stars", 0.02f),
        Pair("2 Stars", 0.01f),
        Pair("1 Star", 0.01f)
    )

    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("reviews_dashboard")
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            // Header Row: Score & Write Review CTA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "CUSTOMER REVIEWS",
                        style = HexCladTheme.typography.labelSmall.copy(
                            letterSpacing = 1.2.sp,
                            fontSize = 11.sp
                        ),
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = "$overallScore",
                            style = HexCladTheme.typography.displaySmall,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Row {
                                repeat(5) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = HexCladTheme.colors.starRatingGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Text(
                                text = "Based on $totalReviews+ reviews",
                                style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = HexCladTheme.colors.lightGrey
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Write a Review dialog opened", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.pureWhite,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.testTag("write_review_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "WRITE REVIEW",
                        style = HexCladTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Star Breakdown Bars
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                starBreakdowns.forEach { (label, fraction) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = label,
                            style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            color = HexCladTheme.colors.mutedGrey,
                            modifier = Modifier.width(55.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(6.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(HexCladTheme.colors.charcoalSurfaceVariant)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(fraction)
                                    .fillMaxHeight()
                                    .background(HexCladTheme.colors.metallicWarmGold)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${(fraction * 100).toInt()}%",
                            style = HexCladTheme.typography.bodySmall.copy(fontSize = 10.sp),
                            color = HexCladTheme.colors.lightGrey,
                            modifier = Modifier.width(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(12.dp))

            // Search Review Input Field & Sorting Dropdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchChange,
                    placeholder = {
                        Text(
                            "Search reviews...",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = HexCladTheme.colors.mutedGrey,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("search_reviews_input")
                )

                // Sort Dropdown
                Box {
                    Surface(
                        color = HexCladTheme.colors.deepCharcoal,
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        modifier = Modifier
                            .clickable { isSortExpanded = true }
                            .height(48.dp)
                            .testTag("reviews_sort_trigger")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedSort,
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.SemiBold
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = HexCladTheme.colors.mutedGrey
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = isSortExpanded,
                        onDismissRequest = { isSortExpanded = false },
                        modifier = Modifier.background(HexCladTheme.colors.charcoalCard)
                    ) {
                        sortOptions.forEach { opt ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = opt,
                                        style = HexCladTheme.typography.bodySmall,
                                        color = if (opt == selectedSort) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.pureWhite
                                    )
                                },
                                onClick = {
                                    onSortChange(opt)
                                    isSortExpanded = false
                                },
                                colors = MenuDefaults.itemColors(textColor = HexCladTheme.colors.pureWhite)
                            )
                        }
                    }
                }
            }
        }
    }
}
