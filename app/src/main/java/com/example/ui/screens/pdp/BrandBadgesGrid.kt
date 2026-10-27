package com.example.ui.screens.pdp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

data class BrandBadge(
    val title: String,
    val subtitle: String,
    val icon: ImageVector
)

/**
 * Brand Feature Badges Grid displaying:
 * - TerraBond™ Ceramic Nonstick
 * - PFAS-Free
 * - Dishwasher Safe
 * - Metal Utensil Safe
 * - Oven Safe up to 500°F
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BrandBadgesGrid(
    modifier: Modifier = Modifier
) {
    val badges = listOf(
        BrandBadge("TerraBond™", "Ceramic Nonstick", Icons.Default.WorkspacePremium),
        BrandBadge("PFAS-Free", "Non-Toxic Matrix", Icons.Default.Shield),
        BrandBadge("Dishwasher Safe", "Effortless Cleanup", Icons.Default.CleanHands),
        BrandBadge("Metal Utensil Safe", "Zero Surface Scratches", Icons.Default.Build),
        BrandBadge("Oven Safe 500°F", "Broil & Sear Ready", Icons.Default.LocalFireDepartment)
    )

    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("brand_badges_grid")
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ENGINEERED FOR A LIFETIME",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.5.sp,
                    fontSize = 10.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.spacedBy(14.dp),
                maxItemsInEachRow = 3
            ) {
                badges.forEach { badge ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(96.dp)
                            .testTag("badge_${badge.title.replace(" ", "_")}")
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(HexCladTheme.colors.metallicWarmGoldContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = badge.icon,
                                contentDescription = badge.title,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = badge.title,
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = badge.subtitle,
                            style = HexCladTheme.typography.bodySmall.copy(fontSize = 9.sp),
                            color = HexCladTheme.colors.mutedGrey,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
