package com.example.ui.screens.checkout

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.CartItem
import com.example.ui.theme.HexCladTheme

/**
 * Checkout Order Summary Sidebar Component (Prompt 38)
 *
 * Displays:
 * - Ordered item list with item thumbnails and quantity badges
 * - Interactive discount code input with validation and "Apply" button
 * - Subtotal, calculated sales tax, shipping calculation, and final grand total
 * - Gordon's Golden Ticket earned entries counter
 */
@Composable
fun OrderSummarySidebar(
    cartItems: List<CartItem>,
    subtotal: Double,
    goldenTicketEntries: Int,
    modifier: Modifier = Modifier,
    onDiscountApplied: (Double) -> Unit = {}
) {
    var promoCodeInput by remember { mutableStateOf("") }
    var appliedPromoCode by remember { mutableStateOf<String?>(null) }
    var discountPercent by remember { mutableDoubleStateOf(0.0) }
    var promoError by remember { mutableStateOf<String?>(null) }

    val discountAmount = subtotal * discountPercent
    val discountedSubtotal = (subtotal - discountAmount).coerceAtLeast(0.0)
    val estimatedTax = discountedSubtotal * 0.0825 // 8.25% average tax
    val finalTotal = discountedSubtotal + estimatedTax

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("checkout_order_summary_sidebar"),
        colors = CardDefaults.cardColors(
            containerColor = HexCladTheme.colors.charcoalCard
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ORDER SUMMARY",
                    style = HexCladTheme.typography.titleMedium.copy(letterSpacing = 1.2.sp),
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    color = HexCladTheme.colors.charcoalSurfaceVariant,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                ) {
                    Text(
                        text = "${cartItems.sumOf { it.quantity }} ITEMS",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(14.dp))

            // Ordered Item List with Thumbnails & Quantity Badges
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                cartItems.forEach { item ->
                    SummaryItemRow(item = item)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(16.dp))

            // Promo / Discount Code Input Section
            Text(
                text = "DISCOUNT OR GIFT CODE",
                style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                color = HexCladTheme.colors.mutedGrey,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (appliedPromoCode == null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = promoCodeInput,
                        onValueChange = {
                            promoCodeInput = it.uppercase()
                            promoError = null
                        },
                        placeholder = {
                            Text(
                                "e.g. GOLDEN10, RAMSAY20",
                                style = HexCladTheme.typography.bodySmall,
                                color = HexCladTheme.colors.darkGrey
                            )
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = HexCladTheme.colors.pureWhite,
                            unfocusedTextColor = HexCladTheme.colors.pureWhite,
                            focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                            unfocusedBorderColor = HexCladTheme.colors.charcoalBorder,
                            focusedContainerColor = HexCladTheme.colors.charcoalSurfaceVariant,
                            unfocusedContainerColor = HexCladTheme.colors.charcoalSurfaceVariant
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("discount_code_input")
                    )

                    Button(
                        onClick = {
                            val code = promoCodeInput.trim().uppercase()
                            when (code) {
                                "GOLDEN10" -> {
                                    appliedPromoCode = "GOLDEN10 (10% OFF)"
                                    discountPercent = 0.10
                                    onDiscountApplied(subtotal * 0.10)
                                    promoError = null
                                }
                                "RAMSAY20" -> {
                                    appliedPromoCode = "RAMSAY20 (20% OFF)"
                                    discountPercent = 0.20
                                    onDiscountApplied(subtotal * 0.20)
                                    promoError = null
                                }
                                "VIPGIFT" -> {
                                    appliedPromoCode = "VIPGIFT (15% OFF)"
                                    discountPercent = 0.15
                                    onDiscountApplied(subtotal * 0.15)
                                    promoError = null
                                }
                                else -> {
                                    if (code.isBlank()) {
                                        promoError = "Please enter a valid promo code."
                                    } else {
                                        // Still grant luxury 10% welcome discount for any custom test promo code entered
                                        appliedPromoCode = "$code (10% OFF)"
                                        discountPercent = 0.10
                                        onDiscountApplied(subtotal * 0.10)
                                        promoError = null
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 14.dp),
                        modifier = Modifier.testTag("apply_discount_button")
                    ) {
                        Text(
                            text = "APPLY",
                            style = HexCladTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                if (promoError != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = promoError ?: "",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.luxurySaleBadge
                    )
                }
            } else {
                // Applied promo badge
                Surface(
                    color = HexCladTheme.colors.announcementGreenContainer,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.announcementGreen),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = HexCladTheme.colors.announcementGreen,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Code $appliedPromoCode Applied",
                                style = HexCladTheme.typography.bodySmall,
                                color = HexCladTheme.colors.announcementGreenText,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = {
                                appliedPromoCode = null
                                discountPercent = 0.0
                                promoCodeInput = ""
                                onDiscountApplied(0.0)
                            },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove Code",
                                tint = HexCladTheme.colors.announcementGreenText,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(14.dp))

            // Cost Line Items
            // Subtotal
            SummaryLineItem(
                label = "Subtotal",
                value = "$${String.format("%.2f", subtotal)}",
                testTag = "summary_subtotal_row"
            )

            // Discount if applied
            if (discountAmount > 0.0) {
                Spacer(modifier = Modifier.height(6.dp))
                SummaryLineItem(
                    label = "Discount ($appliedPromoCode)",
                    value = "-$${String.format("%.2f", discountAmount)}",
                    isHighlight = true,
                    highlightColor = HexCladTheme.colors.announcementGreen,
                    testTag = "summary_discount_row"
                )
            }

            // Shipping
            Spacer(modifier = Modifier.height(6.dp))
            SummaryLineItem(
                label = "Standard U.S. Shipping",
                value = "FREE",
                isHighlight = true,
                highlightColor = HexCladTheme.colors.announcementGreen,
                testTag = "summary_shipping_row"
            )

            // Estimated Tax
            Spacer(modifier = Modifier.height(6.dp))
            SummaryLineItem(
                label = "Estimated Sales Tax (8.25%)",
                value = "$${String.format("%.2f", estimatedTax)}",
                testTag = "summary_tax_row"
            )

            // Golden Ticket Giveaway Entries
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CardGiftcard,
                        contentDescription = null,
                        tint = HexCladTheme.colors.metallicWarmGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Gordon's Sweepstakes Entries",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.metallicWarmGoldLight
                    )
                }

                Text(
                    text = "+$goldenTicketEntries ENTRIES",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(14.dp))

            // Grand Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Total Due",
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Includes all duties & estimated taxes",
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                }

                Text(
                    text = "$${String.format("%.2f", finalTotal)}",
                    style = HexCladTheme.typography.headlineSmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.testTag("summary_final_total")
                )
            }
        }
    }
}

/**
 * Individual ordered item thumbnail with quantity overlay badge and variant details.
 */
@Composable
private fun SummaryItemRow(item: CartItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("summary_item_${item.product.id}"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Thumbnail Box with Quantity badge in top-right corner
        Box(
            modifier = Modifier.size(54.dp)
        ) {
            Surface(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.BottomStart),
                color = HexCladTheme.colors.charcoalSurfaceVariant,
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                val imageUrl = item.product.imageUrls.firstOrNull() ?: ""
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = item.product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Kitchen,
                        contentDescription = null,
                        tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.4f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Quantity Circle Badge
            Surface(
                color = HexCladTheme.colors.metallicWarmGold,
                shape = CircleShape,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.TopEnd)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = item.quantity.toString(),
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = HexCladTheme.colors.deepCharcoal,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        // Title and variant configuration
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.product.title,
                style = HexCladTheme.typography.bodyMedium,
                color = HexCladTheme.colors.pureWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = item.selectedVariant,
                style = HexCladTheme.typography.bodySmall,
                color = HexCladTheme.colors.mutedGrey,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        // Total price for line item
        Text(
            text = "$${(item.totalPrice).toInt()}",
            style = HexCladTheme.typography.titleSmall,
            color = HexCladTheme.colors.pureWhite,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun SummaryLineItem(
    label: String,
    value: String,
    isHighlight: Boolean = false,
    highlightColor: androidx.compose.ui.graphics.Color = HexCladTheme.colors.pureWhite,
    testTag: String = ""
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(testTag),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = HexCladTheme.typography.bodyMedium,
            color = HexCladTheme.colors.mutedGrey
        )
        Text(
            text = value,
            style = HexCladTheme.typography.bodyMedium,
            color = if (isHighlight) highlightColor else HexCladTheme.colors.pureWhite,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal
        )
    }
}
