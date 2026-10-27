package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.HexCladTheme

/**
 * Golden Ticket Sweepstakes Modal (Prompt 47)
 *
 * Explains:
 * - $150,000 Trip to Italy with Chef Gordon Ramsay
 * - Entry multiplier tiers ($1 = 1x to 4x entries)
 * - User entry balance tracker
 * - Official sweepstakes disclosure and shop action CTA
 */
@Composable
fun GoldenTicketModal(
    isOpen: Boolean,
    currentEntries: Int,
    onDismissRequest: () -> Unit,
    onShopCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isOpen) return

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth(0.92f)
                .clip(RoundedCornerShape(16.dp))
                .testTag("golden_ticket_modal"),
            color = HexCladTheme.colors.charcoalCard,
            border = BorderStroke(2.dp, HexCladTheme.colors.metallicWarmGold)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Header with Gold Banner & Close Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    HexCladTheme.colors.metallicWarmGoldContainer,
                                    HexCladTheme.colors.charcoalCard
                                )
                            )
                        )
                        .padding(16.dp)
                ) {
                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(32.dp)
                            .testTag("golden_ticket_close_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Surface(
                            color = HexCladTheme.colors.metallicWarmGold,
                            shape = CircleShape,
                            modifier = Modifier.size(54.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CardGiftcard,
                                contentDescription = null,
                                tint = HexCladTheme.colors.deepCharcoal,
                                modifier = Modifier.padding(12.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "GORDON'S GOLDEN TICKET",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            letterSpacing = 2.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "$150,000 TRIP TO ITALY",
                            style = HexCladTheme.typography.headlineMedium,
                            color = HexCladTheme.colors.pureWhite,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Win a 10-day luxury culinary expedition to Rome, Florence & Modena, plus a private cooking masterclass with Chef Gordon Ramsay.",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.lightGrey,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }

                Column(modifier = Modifier.padding(20.dp)) {
                    // Real-Time Entry Tracker Card
                    Surface(
                        color = HexCladTheme.colors.deepCharcoal,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.6f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "YOUR ACTIVE BALANCE",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.mutedGrey,
                                letterSpacing = 1.2.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$currentEntries ENTRIES",
                                style = HexCladTheme.typography.displaySmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Surface(
                                color = HexCladTheme.colors.charcoalSurfaceVariant,
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    text = "VIP STATUS: 4X MULTIPLIER ACTIVE",
                                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                    color = HexCladTheme.colors.metallicWarmGoldLight,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Multiplier Breakdown Tiers
                    Text(
                        text = "ENTRY MULTIPLIER TIERS",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        letterSpacing = 1.2.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    MultiplierTierRow(
                        multiplier = "4X",
                        tierTitle = "Cookware & Knife Sets",
                        example = "Spend $499 = 1,996 Entries",
                        isBestValue = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    MultiplierTierRow(
                        multiplier = "2X",
                        tierTitle = "Individual Pans, Woks & Pots",
                        example = "Spend $179 = 358 Entries",
                        isBestValue = false
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    MultiplierTierRow(
                        multiplier = "1X",
                        tierTitle = "Cutlery, Aprons & Accessories",
                        example = "Spend $30 = 30 Entries",
                        isBestValue = false
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Trip Inclusions
                    Surface(
                        color = HexCladTheme.colors.charcoalSurfaceVariant,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.FlightTakeoff,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.metallicWarmGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "GRAND PRIZE INCLUDES:",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = HexCladTheme.colors.metallicWarmGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "• First Class roundtrip airfare for 2 to Italy\n• 5-star luxury hotel suites in Rome, Florence & Modena\n• Private cooking workshop with Gordon Ramsay\n• $10,000 spending cash stipend + custom engraved HexClad set",
                                style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = HexCladTheme.colors.lightGrey,
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Action Buttons
                    Button(
                        onClick = {
                            onDismissRequest()
                            onShopCookware()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("golden_ticket_shop_btn")
                    ) {
                        Text(
                            text = "SHOP SETS FOR 4X ENTRIES",
                            style = HexCladTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = onDismissRequest,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = HexCladTheme.colors.lightGrey
                        ),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) {
                        Text(
                            text = "CLOSE & CONTINUE SHOPPING",
                            style = HexCladTheme.typography.labelMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "NO PURCHASE NECESSARY. Open to legal residents of US & Canada, 18+. Ends December 31, 2026. Void where prohibited.",
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.darkGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun MultiplierTierRow(
    multiplier: String,
    tierTitle: String,
    example: String,
    isBestValue: Boolean
) {
    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            if (isBestValue) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    color = if (isBestValue) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalCard,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.size(38.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = multiplier,
                            style = HexCladTheme.typography.titleMedium,
                            color = if (isBestValue) HexCladTheme.colors.deepCharcoal else HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = tierTitle,
                            style = HexCladTheme.typography.bodyMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.SemiBold
                        )
                        if (isBestValue) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = HexCladTheme.colors.luxurySaleBadge,
                                shape = RoundedCornerShape(3.dp)
                            ) {
                                Text(
                                    text = "4X ENTRIES",
                                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                                    color = HexCladTheme.colors.pureWhite,
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                    Text(
                        text = example,
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                }
            }
        }
    }
}
