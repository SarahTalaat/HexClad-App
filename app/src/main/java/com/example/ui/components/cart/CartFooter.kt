package com.example.ui.components.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
 * Cart Summary & Checkout Footer displaying:
 * - Subtotal calculation
 * - Estimated shipping & taxes status
 * - Golden Ticket earned entries summary (EARN 556 ENTRIES)
 * - Large "CHECKOUT" primary action button
 */
@Composable
fun CartFooter(
    subtotal: Double,
    totalEntries: Int,
    onCheckoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        tonalElevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .testTag("cart_footer")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Golden Ticket Entries Summary Ribbon
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(
                        1.dp,
                        HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CardGiftcard,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "EARN $totalEntries ENTRIES FOR GORDON'S ITALY TRIP",
                            style = HexCladTheme.typography.labelSmall.copy(
                                letterSpacing = 1.sp,
                                fontSize = 10.sp
                            ),
                            color = HexCladTheme.colors.metallicWarmGoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Breakdown: Subtotal & Estimated Shipping
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Subtotal",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey
                    )
                    Text(
                        text = "$${subtotal.toInt()}",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Estimated Shipping",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey
                    )
                    Text(
                        text = if (subtotal >= 99.0) "FREE" else "$15.00",
                        style = HexCladTheme.typography.labelSmall,
                        color = if (subtotal >= 99.0) HexCladTheme.colors.announcementGreenText else HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Taxes and duties calculated at checkout",
                    style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = HexCladTheme.colors.mutedGrey
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Large Primary Checkout Action Button
                Button(
                    onClick = onCheckoutClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.metallicWarmGold,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("cart_checkout_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "CHECKOUT • $${subtotal.toInt()}",
                        style = HexCladTheme.typography.labelLarge.copy(
                            letterSpacing = 1.2.sp,
                            fontSize = 13.sp
                        ),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Security Tagline
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "🔒 Guaranteed Safe & Secure 256-Bit Checkout",
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 10.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                }
            }
        }
    }
}
