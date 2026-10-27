package com.example.ui.screens.pdp

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.MockDataProvider
import com.example.model.Product
import com.example.ui.theme.HexCladTheme

/**
 * Frequently Bought Together / Popular Add-Ons Carousel
 * displaying complementary cookware items with instant "+ Add to Cart" buttons.
 */
@Composable
fun PopularAddOnsRow(
    onAddProductToCart: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    // Recommend popular complementary pieces (Rice Pot, Pizza Steel, Wok)
    val addOnProducts = listOfNotNull(
        MockDataProvider.getProductById("hybrid-rice-pot"),
        MockDataProvider.getProductById("hybrid-pizza-steel"),
        MockDataProvider.getProductById("hybrid-wok-12in")
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .testTag("popular_addons_section")
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "FREQUENTLY BOUGHT TOGETHER",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.5.sp,
                    fontSize = 11.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Pair with these Michelin-chef recommended essentials.",
                style = HexCladTheme.typography.bodySmall,
                color = HexCladTheme.colors.mutedGrey
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(addOnProducts) { item ->
                Card(
                    modifier = Modifier
                        .width(220.dp)
                        .testTag("addon_card_${item.id}"),
                    colors = CardDefaults.cardColors(
                        containerColor = HexCladTheme.colors.charcoalCard
                    ),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        // Image Thumbnail
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                                .background(HexCladTheme.colors.charcoalSurfaceVariant),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Kitchen,
                                contentDescription = null,
                                tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.5f),
                                modifier = Modifier.size(36.dp)
                            )
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(item.imageUrls.firstOrNull())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = item.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth().height(110.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = item.title,
                            style = HexCladTheme.typography.bodyMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.height(40.dp)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$${item.price.toInt()}",
                                style = HexCladTheme.typography.titleMedium,
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.Bold
                            )

                            Button(
                                onClick = { onAddProductToCart(item) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = HexCladTheme.colors.pureWhite,
                                    contentColor = HexCladTheme.colors.deepCharcoal
                                ),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                modifier = Modifier
                                    .height(36.dp)
                                    .testTag("addon_add_btn_${item.id}")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "ADD",
                                    style = HexCladTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
