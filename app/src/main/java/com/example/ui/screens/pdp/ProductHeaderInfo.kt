package com.example.ui.screens.pdp

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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Product
import com.example.ui.theme.HexCladTheme

/**
 * Product Pricing & Golden Ticket Badge Header Component displaying:
 * - Product Title
 * - Star Rating & Review Count
 * - Price & Savings
 * - Prominent Red Promo Box: "SPEND $1 = 4X ENTRIES | THIS PRODUCT IS WORTH: 556 Entries"
 */
@Composable
fun ProductHeaderInfo(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag("pdp_header_info")
    ) {
        // Category Label
        Text(
            text = product.category.uppercase(),
            style = HexCladTheme.typography.labelSmall.copy(
                letterSpacing = 1.5.sp,
                fontSize = 11.sp
            ),
            color = HexCladTheme.colors.metallicWarmGoldLight,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Product Title
        Text(
            text = product.title,
            style = HexCladTheme.typography.headlineLarge,
            color = HexCladTheme.colors.pureWhite
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Star Rating & Chef Verified Badge
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = HexCladTheme.colors.starRatingGold,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${product.rating} (${product.reviewCount} Reviews)",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.lightGrey,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Surface(
                color = HexCladTheme.colors.announcementGreenContainer,
                shape = RoundedCornerShape(2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = null,
                        tint = HexCladTheme.colors.announcementGreenText,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "CHEF APPROVED",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = HexCladTheme.colors.announcementGreenText,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Price Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "$${product.price.toInt()}",
                style = HexCladTheme.typography.displaySmall,
                color = HexCladTheme.colors.pureWhite,
                fontWeight = FontWeight.Bold
            )

            if (product.hasDiscount && product.originalPrice != null) {
                Text(
                    text = "$${product.originalPrice.toInt()}",
                    style = HexCladTheme.typography.headlineSmall,
                    color = HexCladTheme.colors.mutedGrey,
                    textDecoration = TextDecoration.LineThrough
                )

                Surface(
                    color = HexCladTheme.colors.luxurySaleBadge,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "SAVE $${product.savingsAmount.toInt()}",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Prominent Red Promo Box (Golden Ticket to Italy)
        Surface(
            color = Color(0xFFC02626), // Prominent HexClad crimson red promo container
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(1.dp, Color(0xFFE53935)),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("red_promo_box")
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CardGiftcard,
                    contentDescription = "Golden Ticket Promo",
                    tint = HexCladTheme.colors.pureWhite,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "SPEND $1 = 4X ENTRIES",
                        style = HexCladTheme.typography.labelSmall.copy(
                            letterSpacing = 1.2.sp,
                            fontSize = 11.sp
                        ),
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "THIS PRODUCT IS WORTH: ${product.entryCount} Entries",
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 12.sp),
                        color = HexCladTheme.colors.pureWhite.copy(alpha = 0.95f),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
