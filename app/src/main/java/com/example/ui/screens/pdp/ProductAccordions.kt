package com.example.ui.screens.pdp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.ui.theme.HexCladTheme

data class AccordionItem(
    val title: String,
    val content: String
)

/**
 * Expandable Accordion List featuring:
 * - What's Included
 * - Hybrid Technology
 * - Features
 * - Product Dimensions
 * - Care & Use
 * - Lifetime Warranty
 */
@Composable
fun ProductAccordions(
    product: Product,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        AccordionItem(
            title = "What's Included",
            content = "Includes (1) ${product.title}, (1) Tempered glass lid with stay-cool steam release valve, and (1) Quick Start Culinary Guide with Michelin-chef Gordon Ramsay seasoning instructions."
        ),
        AccordionItem(
            title = "Hybrid Technology",
            content = "HexClad’s patented laser-etched hexagonal design creates a series of peaks and valleys. The stainless steel peaks give you that golden restaurant sear, while the valleys provide an effortless non-stick release."
        ),
        AccordionItem(
            title = "Features",
            content = "• Tri-Ply Construction: Aluminum core between dual stainless steel layers\n• Metal Utensil Safe: Use metal spatulas, forks, and whisks with complete confidence\n• Oven Safe Up to 500°F (Lids safe up to 400°F)\n• Induction, Gas, Electric, and Ceramic compatible"
        ),
        AccordionItem(
            title = "Product Dimensions",
            content = "• Diameter/Length: 12 Inches (30.5 cm)\n• Depth: 2.2 Inches (5.6 cm)\n• Total Weight: 3.4 lbs (1.54 kg)\n• Handle Length: 8.5 Inches stay-cool ergonomic cast stainless"
        ),
        AccordionItem(
            title = "Care & Use",
            content = "Before first use, wash with warm soapy water and season with 1 tsp of high-smoke point oil (avocado or grapeseed) on medium heat for 2-3 minutes. Dishwasher safe, though hand-washing preserves factory gloss."
        ),
        AccordionItem(
            title = "Lifetime Warranty",
            content = "Covered by HexClad's Lifetime Warranty against manufacturer defects. If your cookware ever warps, delaminates, or fails under normal household culinary use, we will replace it free of charge."
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("pdp_accordions_section")
    ) {
        items.forEachIndexed { index, item ->
            AccordionRow(item = item)
            if (index < items.lastIndex) {
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            }
        }
    }
}

@Composable
fun AccordionRow(item: AccordionItem) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f, label = "chevron")

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(vertical = 14.dp)
                .testTag("accordion_${item.title.replace(" ", "_")}"),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title.uppercase(),
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.2.sp,
                    fontSize = 11.sp
                ),
                color = if (isExpanded) HexCladTheme.colors.metallicWarmGoldLight else HexCladTheme.colors.pureWhite,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                tint = if (isExpanded) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.mutedGrey,
                modifier = Modifier.rotate(rotationAngle)
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                text = item.content,
                style = HexCladTheme.typography.bodyMedium,
                color = HexCladTheme.colors.lightGrey,
                lineHeight = 22.sp,
                modifier = Modifier.padding(bottom = 14.dp)
            )
        }
    }
}
