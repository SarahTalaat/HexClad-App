package com.example.ui.components.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
 * Cart Item Row displaying:
 * - Product thumbnail
 * - Title & selected variant
 * - Total price
 * - Golden Ticket entry badge (+556 ENTRIES)
 * - Quantity - / + stepper buttons
 * - Delete button
 */
@Composable
fun CartItemRow(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("cart_item_row_${cartItem.product.id}"),
        colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Thumbnail
            Box(
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(HexCladTheme.colors.charcoalSurfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Kitchen,
                    contentDescription = null,
                    tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.5f),
                    modifier = Modifier.size(28.dp)
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cartItem.product.imageUrls.firstOrNull())
                        .crossfade(true)
                        .build(),
                    contentDescription = cartItem.product.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(76.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Details Column
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItem.product.title,
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = cartItem.selectedVariant,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Golden Ticket Badge
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CardGiftcard,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "+${cartItem.totalEntries} ENTRIES",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                            color = HexCladTheme.colors.metallicWarmGoldLight,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Quantity Stepper
                Surface(
                    color = HexCladTheme.colors.deepCharcoal,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { onQuantityChange(cartItem.quantity - 1) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease",
                                tint = HexCladTheme.colors.pureWhite,
                                modifier = Modifier.size(14.dp)
                            )
                        }

                        Text(
                            text = "${cartItem.quantity}",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )

                        IconButton(
                            onClick = { onQuantityChange(cartItem.quantity + 1) },
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase",
                                tint = HexCladTheme.colors.pureWhite,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Price and Delete Button Column
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.height(84.dp)
            ) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Item",
                        tint = HexCladTheme.colors.mutedGrey,
                        modifier = Modifier.size(16.dp)
                    )
                }

                Text(
                    text = "$${cartItem.totalPrice.toInt()}",
                    style = HexCladTheme.typography.titleLarge,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
