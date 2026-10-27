package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Product
import com.example.ui.theme.HexCladTheme

/**
 * Product Listing Card displaying:
 * - Product image (with laser-etched luxury placeholder)
 * - Golden Ticket Entry multiplier badge (556 ENTRIES)
 * - Title
 * - Star rating with review count
 * - Price (with crossed-out original price & savings)
 * - "Quick Add to Cart" and "View Details" buttons
 */
@Composable
fun ProductCard(
    product: Product,
    onViewDetails: () -> Unit,
    onQuickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onViewDetails() }
            .testTag("product_listing_card_${product.id}"),
        colors = CardDefaults.cardColors(
            containerColor = HexCladTheme.colors.charcoalCard
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Product Image Container with Golden Ticket Badge overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(HexCladTheme.colors.charcoalSurfaceVariant)
            ) {
                val imageUrl = product.imageUrls.firstOrNull() ?: ""

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(160.dp)
                )

                // Fallback luxury geometric icon when image is loading
                Box(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Kitchen,
                        contentDescription = null,
                        tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.5f),
                        modifier = Modifier.size(54.dp)
                    )
                }

                // Golden Ticket Multiplier Badge (Top-Left)
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(bottomEnd = 6.dp),
                    border = BorderStroke(
                        1.dp,
                        HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .testTag("golden_ticket_badge_${product.id}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CardGiftcard,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "+${product.entryCount} ENTRIES",
                            style = HexCladTheme.typography.labelSmall.copy(
                                fontSize = 9.sp,
                                letterSpacing = 1.sp
                            ),
                            color = HexCladTheme.colors.metallicWarmGoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Card Body
            Column(modifier = Modifier.padding(14.dp)) {
                // Category Tag
                Text(
                    text = product.category.uppercase(),
                    style = HexCladTheme.typography.labelSmall.copy(
                        fontSize = 9.sp,
                        letterSpacing = 1.2.sp
                    ),
                    color = HexCladTheme.colors.mutedGrey
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Title
                Text(
                    text = product.title,
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.height(44.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Star Rating & Review Count
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = HexCladTheme.colors.starRatingGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${product.rating} (${product.reviewCount})",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.lightGrey
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Price Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "$${product.price.toInt()}",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    if (product.hasDiscount && product.originalPrice != null) {
                        Text(
                            text = "$${product.originalPrice.toInt()}",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(10.dp))

                // Action Buttons: Quick Add to Cart & View Details
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    OutlinedButton(
                        onClick = onViewDetails,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = HexCladTheme.colors.pureWhite
                        ),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(42.dp)
                            .testTag("view_details_btn_${product.id}")
                    ) {
                        Text(
                            text = "DETAILS",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = onQuickAdd,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.pureWhite,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
                        modifier = Modifier
                            .weight(1.2f)
                            .height(42.dp)
                            .testTag("quick_add_btn_${product.id}")
                    ) {
                        Text(
                            text = "QUICK ADD",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
