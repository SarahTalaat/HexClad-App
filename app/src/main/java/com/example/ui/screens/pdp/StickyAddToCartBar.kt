package com.example.ui.screens.pdp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Sticky Bottom Add to Cart Bar staying visible during PDP scrolling,
 * featuring quantity increment/decrement controls and a prominent red ADD TO CART button.
 */
@Composable
fun StickyAddToCartBar(
    unitPrice: Double,
    onAddToCart: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var quantity by remember { mutableIntStateOf(1) }
    val totalAmount = unitPrice * quantity

    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        tonalElevation = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .testTag("sticky_add_to_cart_bar")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Stepper Container
                Surface(
                    color = HexCladTheme.colors.charcoalCard,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.height(50.dp)
                    ) {
                        IconButton(
                            onClick = { if (quantity > 1) quantity-- },
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("pdp_qty_decrement")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease Quantity",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                        IconButton(
                            onClick = { quantity++ },
                            modifier = Modifier
                                .size(48.dp)
                                .testTag("pdp_qty_increment")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase Quantity",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }
                    }
                }

                // Prominent Red "ADD TO CART" Button
                Button(
                    onClick = { onAddToCart(quantity) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC02626), // HexClad signature red CTA
                        contentColor = HexCladTheme.colors.pureWhite
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .testTag("sticky_add_to_cart_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "ADD TO CART • $${totalAmount.toInt()}",
                        style = HexCladTheme.typography.labelLarge.copy(
                            letterSpacing = 1.2.sp,
                            fontSize = 13.sp
                        ),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}
