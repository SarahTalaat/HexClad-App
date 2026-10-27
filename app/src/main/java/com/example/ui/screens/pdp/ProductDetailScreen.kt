package com.example.ui.screens.pdp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MockDataProvider
import com.example.model.Product
import com.example.model.ProductVariant
import com.example.navigation.ReviewItemRow
import com.example.ui.theme.HexCladTheme

/**
 * Complete, High-Conversion Product Detail Page (PDP) combining:
 * - Image Gallery Carousel (HorizontalPager)
 * - Header Info (Rating, Price, Red 4X Golden Ticket Promo Box)
 * - Variant Selector Chips
 * - Popular Add-Ons Carousel ("Frequently Bought Together")
 * - Brand Badges Grid (TerraBond, PFAS-Free, Dishwasher, Metal Utensil, Oven 500°F)
 * - Expandable Feature Accordions
 * - Gordon Ramsay Endorsement Card
 * - UGC Video Reels ("The Secret's Out!")
 * - Verified Reviews
 * - Sticky Bottom Add to Cart Bar
 */
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit,
    onAddToCart: (Product, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedVariant by remember {
        mutableStateOf<ProductVariant?>(product.variants.firstOrNull())
    }

    val currentPrice = selectedVariant?.price ?: product.price

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("full_product_detail_screen"),
        containerColor = HexCladTheme.colors.deepCharcoal,
        topBar = {
            Surface(
                color = HexCladTheme.colors.deepCharcoal,
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.testTag("pdp_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }
                        Text(
                            text = "BACK TO CATALOG",
                            style = HexCladTheme.typography.labelSmall.copy(
                                letterSpacing = 1.2.sp,
                                fontSize = 10.sp
                            ),
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Product",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }
                }
            }
        },
        bottomBar = {
            // Prompt 25: Sticky Bottom Add to Cart Bar
            StickyAddToCartBar(
                unitPrice = currentPrice,
                onAddToCart = { quantity ->
                    val variantName = selectedVariant?.name ?: "Standard"
                    onAddToCart(product, variantName, quantity)
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(HexCladTheme.colors.deepCharcoal),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            // 1. Image Gallery Carousel (Prompt 22)
            item {
                ProductImageGallery(
                    imageUrls = product.imageUrls,
                    productTitle = product.title
                )
            }

            // 2. Product Pricing & Red Golden Ticket Promo Box (Prompt 23)
            item {
                ProductHeaderInfo(product = product)
            }

            // 3. Variant Selector Chips (Prompt 24)
            if (product.variants.isNotEmpty()) {
                item {
                    VariantSelector(
                        variants = product.variants,
                        selectedVariant = selectedVariant,
                        onVariantSelected = { selectedVariant = it }
                    )
                }
            }

            // 4. Brand Feature Badges Grid (Prompt 28)
            item {
                BrandBadgesGrid()
            }

            // 5. Frequently Bought Together / Add-Ons (Prompt 26)
            item {
                PopularAddOnsRow(
                    onAddProductToCart = { addOnProduct ->
                        onAddToCart(addOnProduct, "Standard", 1)
                    }
                )
            }

            // 6. Gordon Ramsay Endorsement Card (Prompt 29)
            item {
                RamsayEndorsementCard()
            }

            // 7. Feature Accordion List (Prompt 27)
            item {
                Spacer(modifier = Modifier.height(10.dp))
                ProductAccordions(product = product)
            }

            // 8. User UGC Video Reels Carousel (Prompt 30)
            item {
                UgcVideoReels()
            }

            // 9. Reviews Summary & Dashboard (Prompt 31)
            item {
                var searchReviewText by remember { mutableStateOf("") }
                var sortOption by remember { mutableStateOf("Most Recent") }

                ReviewsDashboard(
                    overallScore = MockDataProvider.averageReviewRating,
                    totalReviews = MockDataProvider.totalReviewCount,
                    searchQuery = searchReviewText,
                    onSearchChange = { searchReviewText = it },
                    selectedSort = sortOption,
                    onSortChange = { sortOption = it }
                )
            }

            // 10. Individual Review Cards (Prompt 32)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
                ) {
                    MockDataProvider.reviews.take(3).forEach { review ->
                        com.example.ui.components.ReviewCard(review = review)
                    }
                }
            }
        }
    }
}
