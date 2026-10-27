package com.example.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.CartItem
import com.example.model.CheckoutDetails
import com.example.ui.theme.HexCladTheme

/**
 * High-fidelity Order Confirmation / Success View:
 * - Gordon's Golden Ticket to Italy earned entry celebration banner
 * - Order Number & Status Tracker
 * - Order items list breakdown
 * - Shipping destination details
 * - HexClad Lifetime Warranty trust seal
 * - Action buttons to continue shopping or visit support
 */
@Composable
fun OrderConfirmationView(
    orderNumber: String,
    goldenTicketEntries: Int,
    subtotal: Double,
    cartItems: List<CartItem>,
    checkoutDetails: CheckoutDetails,
    onContinueShopping: () -> Unit,
    onViewSupport: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("order_confirmation_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Success Hero Banner
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .clip(CircleShape)
                        .background(HexCladTheme.colors.announcementGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = HexCladTheme.colors.pureWhite,
                        modifier = Modifier.size(38.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "ORDER CONFIRMED",
                    style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 2.sp),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Thank You, ${checkoutDetails.firstName.ifBlank { "Chef" }}!",
                    style = HexCladTheme.typography.displayMedium,
                    color = HexCladTheme.colors.pureWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Confirmation #$orderNumber",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.metallicWarmGoldLight
                )

                Text(
                    text = "A confirmation receipt and tracking link have been sent to ${checkoutDetails.email.ifBlank { "your email" }}.",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }
        }

        // 2. Gordon's Golden Ticket Giveaway Celebration Card
        item {
            Surface(
                color = HexCladTheme.colors.deepCharcoal,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.5.dp, HexCladTheme.colors.metallicWarmGold),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("card_golden_ticket_celebration")
            ) {
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
                        .padding(18.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "GORDON'S GOLDEN TICKET TO ITALY",
                                style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "+$goldenTicketEntries GIVEAWAY ENTRIES EARNED",
                            style = HexCladTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp
                            ),
                            color = HexCladTheme.colors.pureWhite,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "You're automatically entered to win an all-expenses-paid culinary trip to Italy with Gordon Ramsay! Keep an eye on your inbox for official drawing results.",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        // 3. Shipping Status Tracker
        item {
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalShipping,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ESTIMATED DELIVERY: 2-3 BUSINESS DAYS",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 4 Step Progress Line
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StepIndicator(label = "Ordered", isCompleted = true, isCurrent = false)
                        StepLine(isCompleted = true)
                        StepIndicator(label = "Preparing", isCompleted = false, isCurrent = true)
                        StepLine(isCompleted = false)
                        StepIndicator(label = "Shipped", isCompleted = false, isCurrent = false)
                        StepLine(isCompleted = false)
                        StepIndicator(label = "Delivered", isCompleted = false, isCurrent = false)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Ship To: ${checkoutDetails.address.ifBlank { "123 Culinary Way" }}, ${checkoutDetails.city.ifBlank { "Los Angeles" }}, ${checkoutDetails.state.ifBlank { "CA" }} ${checkoutDetails.zipCode.ifBlank { "90210" }}",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey
                    )
                }
            }
        }

        // 4. Order Summary Items Header
        item {
            Text(
                text = "ORDER SUMMARY (${cartItems.sumOf { it.quantity }} ITEMS)",
                style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
        }

        // Purchased Items
        items(cartItems) { item ->
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val imageUrl = item.product.imageUrls.firstOrNull().orEmpty()
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = item.product.title,
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(HexCladTheme.colors.charcoalSurfaceVariant),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.product.title,
                            style = HexCladTheme.typography.titleSmall,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Qty: ${item.quantity}  •  ${item.selectedVariant}",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }

                    Text(
                        text = "$${item.totalPrice.toInt()}",
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 5. Total and Trust Badges
        item {
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Total Paid", style = HexCladTheme.typography.titleMedium, color = HexCladTheme.colors.pureWhite)
                        Text(
                            text = "$${subtotal.toInt()}",
                            style = HexCladTheme.typography.headlineSmall,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.VerifiedUser,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Backed by HexClad Lifetime Warranty & 30-Day Free Returns",
                            style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }
                }
            }
        }

        // 6. Action CTAs
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onContinueShopping,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.metallicWarmGold,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("btn_continue_shopping_success")
                ) {
                    Text(
                        text = "EXPLORE MORE COOKWARE",
                        style = HexCladTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onViewSupport,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = HexCladTheme.colors.pureWhite
                    ),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("btn_customer_care_success")
                ) {
                    Text(
                        text = "CUSTOMER CARE & POLICIES",
                        style = HexCladTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun StepIndicator(label: String, isCompleted: Boolean, isCurrent: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isCompleted -> HexCladTheme.colors.announcementGreen
                        isCurrent -> HexCladTheme.colors.metallicWarmGold
                        else -> HexCladTheme.colors.charcoalBorder
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = HexCladTheme.colors.pureWhite,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
            color = if (isCompleted || isCurrent) HexCladTheme.colors.pureWhite else HexCladTheme.colors.mutedGrey
        )
    }
}

@Composable
private fun StepLine(isCompleted: Boolean) {
    Box(
        modifier = Modifier
            .width(28.dp)
            .height(2.dp)
            .background(if (isCompleted) HexCladTheme.colors.announcementGreen else HexCladTheme.colors.charcoalBorder)
    )
}
