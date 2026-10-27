package com.example.ui.screens.pdp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ProductVariant
import com.example.ui.theme.HexCladTheme

/**
 * Interactive Selection Chips for Product Options & Configurations
 * (e.g. Egg Pan vs. Rice Pot, pan dimensions, piece counts).
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun VariantSelector(
    variants: List<ProductVariant>,
    selectedVariant: ProductVariant?,
    onVariantSelected: (ProductVariant) -> Unit,
    modifier: Modifier = Modifier
) {
    if (variants.isEmpty()) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("variant_selector")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "CONFIGURATION",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.2.sp,
                    fontSize = 10.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )

            selectedVariant?.let {
                Text(
                    text = it.name,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            variants.forEach { variant ->
                val isSelected = selectedVariant?.id == variant.id

                Surface(
                    color = if (isSelected) {
                        HexCladTheme.colors.charcoalSurfaceVariant
                    } else {
                        HexCladTheme.colors.charcoalCard
                    },
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(
                        width = if (isSelected) 1.5.dp else 1.dp,
                        color = if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                    ),
                    modifier = Modifier
                        .clickable { onVariantSelected(variant) }
                        .testTag("variant_chip_${variant.id}")
                ) {
                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)) {
                        Text(
                            text = variant.name,
                            style = HexCladTheme.typography.bodyMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) HexCladTheme.colors.pureWhite else HexCladTheme.colors.lightGrey
                        )

                        Text(
                            text = if (variant.inStock) "In Stock • Ships Free" else "Pre-Order",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = if (variant.inStock) HexCladTheme.colors.announcementGreenText else HexCladTheme.colors.mutedGrey
                        )
                    }
                }
            }
        }
    }
}
