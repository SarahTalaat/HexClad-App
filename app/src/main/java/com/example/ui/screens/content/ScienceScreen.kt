package com.example.ui.screens.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * HexClad Science & Technology Screen (Prompt 41)
 *
 * Explains:
 * "Elevated cooking. Easy cleanup. Cookware that lasts."
 *
 * Features interactive breakdown cards explaining:
 * 1. Laser-etched stainless steel peaks (searing power & metal utensil defense)
 * 2. High-grade Japanese nonstick valleys (effortless release & rapid cleanup)
 * 3. Tri-ply encapsulated aluminum core (uniform heat conduction, no hot spots)
 * 4. Magnetic stainless steel exterior (induction, gas, ceramic, and oven safe to 500°F)
 */
@Composable
fun ScienceScreen(
    onBackClick: () -> Unit,
    onExploreCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedCardIndex by remember { mutableIntStateOf(0) }

    val goldColor = HexCladTheme.colors.metallicWarmGold
    val greenColor = HexCladTheme.colors.announcementGreen
    val redColor = HexCladTheme.colors.luxurySaleBadge
    val starColor = HexCladTheme.colors.starRatingGold

    val scienceCards = remember(goldColor, greenColor, redColor, starColor) {
        listOf(
            ScienceCardData(
                id = 0,
                title = "Laser-Etched Stainless Steel Peaks",
                subtitle = "Golden caramelization & metal utensil armor",
                icon = Icons.Default.Shield,
                accentColor = goldColor,
                fullDetails = "A microscopic hexagonal matrix of stainless steel ridges rises above the nonstick surface. When you sear proteins, meat contacts the steel directly, creating deep fond and rich caramelized flavor just like a commercial French skillet. Because the peaks are raised, metal spatulas and tongs glide safely across steel without contacting the nonstick below."
            ),
            ScienceCardData(
                id = 1,
                title = "Recessed Japanese Nonstick Valleys",
                subtitle = "Effortless food release & wiping clean in seconds",
                icon = Icons.Default.CleaningServices,
                accentColor = greenColor,
                fullDetails = "Protected in the recessed valleys between each hexagonal peak is our non-toxic, diamond-infused ceramic nonstick compound. Eggs, fragile fish fillets, and melted cheese glide effortlessly over the pan without sticking. Cleanup requires nothing more than hot water and a light sponge pass."
            ),
            ScienceCardData(
                id = 2,
                title = "Tri-Ply Aluminum Thermal Core",
                subtitle = "Immediate conductivity with zero cold spots",
                icon = Icons.Default.LocalFireDepartment,
                accentColor = redColor,
                fullDetails = "Sandwiched between the internal and external stainless steel layers lies a heavy-gauge aluminum core. Aluminum conducts heat four times faster than steel alone, dispersing flame or induction energy instantly across the base and up the sidewalls for perfectly uniform browning."
            ),
            ScienceCardData(
                id = 3,
                title = "Magnetic Steel Base & 500°F Oven Safety",
                subtitle = "Compatible with all cooktops including induction",
                icon = Icons.Default.Whatshot,
                accentColor = starColor,
                fullDetails = "The exterior is formed from heavy magnetic stainless steel, making HexClad compatible with induction, gas, electric, and glass-ceramic cooktops. Both pans and tempered glass lids are oven-safe up to 500°F (260°C), allowing seamless transitions from stovetop searing to oven roasting."
            )
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("science_screen"),
        contentPadding = PaddingValues(bottom = 36.dp)
    ) {
        // Navigation Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("science_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "THE SCIENCE OF HEXCLAD",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Hero Header: Elevated cooking. Easy cleanup. Cookware that lasts.
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                HexCladTheme.colors.charcoalSurfaceVariant,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Science,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PATENTED HYBRID TECHNOLOGY",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = "Elevated cooking.\nEasy cleanup.\nCookware that lasts.",
                        style = HexCladTheme.typography.headlineLarge,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 36.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "The world's only patented cookware that combines the searing power of stainless steel with the easy cleanup of nonstick.",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        }

        // Interactive Layer Explanations Title
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                Text(
                    text = "ANATOMY OF A HEXCLAD PAN",
                    style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Tap layers to explore engineering details",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
        }

        // Interactive Breakdown Cards
        items(scienceCards.size) { index ->
            val card = scienceCards[index]
            val isExpanded = expandedCardIndex == card.id

            ScienceBreakdownItem(
                card = card,
                isExpanded = isExpanded,
                onClick = {
                    expandedCardIndex = if (isExpanded) -1 else card.id
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Quick Stats / Metric Row
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ScienceMetricBox(
                    title = "500°F",
                    subtitle = "Oven Safe Rating",
                    modifier = Modifier.weight(1f)
                )
                ScienceMetricBox(
                    title = "100%",
                    subtitle = "Dishwasher Safe",
                    modifier = Modifier.weight(1f)
                )
                ScienceMetricBox(
                    title = "LIFETIME",
                    subtitle = "Warranty Backed",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Shop Cookware CTA Button
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onExploreCookware,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HexCladTheme.colors.metallicWarmGold,
                    contentColor = HexCladTheme.colors.deepCharcoal
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 16.dp)
                    .testTag("science_shop_now_btn")
            ) {
                Text(
                    text = "SHOP HYBRID COOKWARE",
                    style = HexCladTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private data class ScienceCardData(
    val id: Int,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val accentColor: androidx.compose.ui.graphics.Color,
    val fullDetails: String
)

@Composable
private fun ScienceBreakdownItem(
    card: ScienceCardData,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() }
            .testTag("science_card_${card.id}"),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpanded) HexCladTheme.colors.charcoalSurfaceVariant else HexCladTheme.colors.charcoalCard
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            if (isExpanded) card.accentColor else HexCladTheme.colors.charcoalBorder
        )
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        color = card.accentColor.copy(alpha = 0.15f),
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = card.icon,
                                contentDescription = null,
                                tint = card.accentColor,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = card.title,
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = card.subtitle,
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = card.accentColor
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = card.fullDetails,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey,
                        lineHeight = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun ScienceMetricBox(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = HexCladTheme.typography.titleMedium,
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                color = HexCladTheme.colors.mutedGrey,
                textAlign = TextAlign.Center
            )
        }
    }
}
