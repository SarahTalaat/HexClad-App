package com.example.ui.components.cart

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Slide-Over Cart Drawer Header displaying:
 * - Title "Your Cart" with item count
 * - Close button
 * - Free shipping threshold progress bar ("Congrats! You're qualified for FREE SHIPPING")
 */
@Composable
fun CartDrawerHeader(
    itemCount: Int,
    subtotal: Double,
    onCloseClick: () -> Unit,
    freeShippingThreshold: Double = 99.0,
    modifier: Modifier = Modifier
) {
    val isQualified = subtotal >= freeShippingThreshold
    val remainingAmount = if (isQualified) 0.0 else freeShippingThreshold - subtotal
    val progressFraction = (subtotal / freeShippingThreshold).coerceIn(0.0, 1.0).toFloat()

    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        modifier = modifier
            .fillMaxWidth()
            .testTag("cart_drawer_header")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Title & Close Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "YOUR CART",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "($itemCount ${if (itemCount == 1) "item" else "items"})",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.mutedGrey
                    )
                }

                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier.testTag("cart_close_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Cart",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
            }

            // Free Shipping Progress Bar Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HexCladTheme.colors.charcoalCard)
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalShipping,
                        contentDescription = null,
                        tint = if (isQualified) HexCladTheme.colors.announcementGreenText else HexCladTheme.colors.metallicWarmGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isQualified) {
                            "Congrats! You're qualified for FREE SHIPPING"
                        } else {
                            "Add $${remainingAmount.toInt()} more to unlock FREE SHIPPING"
                        },
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = if (isQualified) HexCladTheme.colors.announcementGreenText else HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(HexCladTheme.colors.charcoalSurfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progressFraction)
                            .fillMaxHeight()
                            .background(
                                if (isQualified) HexCladTheme.colors.announcementGreenText else HexCladTheme.colors.metallicWarmGold
                            )
                    )
                }
            }

            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
        }
    }
}
