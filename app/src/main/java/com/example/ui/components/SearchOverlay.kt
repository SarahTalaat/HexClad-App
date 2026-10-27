package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.MockDataProvider
import com.example.model.Product
import com.example.ui.theme.HexCladTheme

/**
 * Global Search Overlay Component (Prompt 46)
 *
 * Features:
 * - Search input: "Shop HexClad's Collection..." with auto-focus and instant filter
 * - Recent searches history with chip selectors and "Clear All"
 * - "BEST SELLERS" quick link section: 12" Hybrid Pan, 14" Hybrid Pan, 6PC Set
 * - Real-time filtered product cards with thumbnail, pricing, and entries
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchOverlay(
    isOpen: Boolean,
    recentSearches: List<String>,
    onDismissRequest: () -> Unit,
    onNavigateToProduct: (String) -> Unit,
    onAddRecentSearch: (String) -> Unit,
    onClearRecentSearches: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isOpen) return

    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    val allProducts = remember { MockDataProvider.products }
    val searchResults = remember(query) {
        if (query.isBlank()) {
            emptyList()
        } else {
            allProducts.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.category.contains(query, ignoreCase = true)
            }
        }
    }

    LaunchedEffect(isOpen) {
        if (isOpen) {
            focusRequester.requestFocus()
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .testTag("search_overlay_dialog"),
            color = HexCladTheme.colors.deepCharcoal
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Top Search Input Bar
                Surface(
                    color = HexCladTheme.colors.charcoalCard,
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = {
                                query = it
                            },
                            placeholder = {
                                Text(
                                    text = "Shop HexClad's Collection...",
                                    style = HexCladTheme.typography.bodyMedium,
                                    color = HexCladTheme.colors.mutedGrey
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon",
                                    tint = HexCladTheme.colors.metallicWarmGold
                                )
                            },
                            trailingIcon = {
                                if (query.isNotBlank()) {
                                    IconButton(onClick = { query = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Clear",
                                            tint = HexCladTheme.colors.mutedGrey
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = HexCladTheme.colors.pureWhite,
                                unfocusedTextColor = HexCladTheme.colors.pureWhite,
                                focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                                unfocusedBorderColor = HexCladTheme.colors.charcoalBorder,
                                focusedContainerColor = HexCladTheme.colors.deepCharcoal,
                                unfocusedContainerColor = HexCladTheme.colors.deepCharcoal
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester)
                                .testTag("search_input_field")
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = onDismissRequest,
                            modifier = Modifier.testTag("search_close_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Search",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }
                    }
                }

                // Main Content Body: Suggestions vs Results
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    if (query.isBlank()) {
                        // Section 1: Recent Searches
                        if (recentSearches.isNotEmpty()) {
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.History,
                                            contentDescription = null,
                                            tint = HexCladTheme.colors.metallicWarmGold,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "RECENT SEARCHES",
                                            style = HexCladTheme.typography.labelSmall,
                                            color = HexCladTheme.colors.metallicWarmGold,
                                            letterSpacing = 1.3.sp
                                        )
                                    }
                                    Text(
                                        text = "CLEAR ALL",
                                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 11.sp),
                                        color = HexCladTheme.colors.mutedGrey,
                                        modifier = Modifier
                                            .clickable { onClearRecentSearches() }
                                            .testTag("clear_recent_searches_btn")
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    recentSearches.forEach { searchItem ->
                                        Surface(
                                            color = HexCladTheme.colors.charcoalCard,
                                            shape = RoundedCornerShape(20.dp),
                                            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                                            modifier = Modifier.clickable {
                                                query = searchItem
                                            }
                                        ) {
                                            Text(
                                                text = searchItem,
                                                style = HexCladTheme.typography.bodySmall,
                                                color = HexCladTheme.colors.lightGrey,
                                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(28.dp))
                            }
                        }

                        // Section 2: Best Sellers Quick Links
                        item {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.TrendingUp,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.metallicWarmGold,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "BEST SELLERS",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = HexCladTheme.colors.metallicWarmGold,
                                    letterSpacing = 1.3.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Most demanded hybrid pieces by home cooks and Michelin chefs",
                                style = HexCladTheme.typography.bodySmall,
                                color = HexCladTheme.colors.mutedGrey
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        // 3 Bestseller Quick Link Cards (12" Pan, 14" Pan, 6PC Set)
                        val bestSellers = listOf(
                            Triple("12\" Hybrid Stainless Steel Pan", "$179", "hybrid-12-inch-pan"),
                            Triple("14\" Hybrid Pan with Helper Handle & Lid", "$219", "hybrid-14-inch-pan"),
                            Triple("6-Piece Hybrid Cookware Set", "$499", "6-piece-hybrid-cookware-set")
                        )

                        items(bestSellers) { (title, price, productId) ->
                            BestSellerQuickCard(
                                title = title,
                                price = price,
                                onClick = {
                                    onAddRecentSearch(title)
                                    onDismissRequest()
                                    onNavigateToProduct(productId)
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    } else {
                        // Section: Search Results
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "RESULTS FOR \"$query\"",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = HexCladTheme.colors.metallicWarmGold,
                                    letterSpacing = 1.2.sp
                                )
                                Text(
                                    text = "${searchResults.size} item${if (searchResults.size == 1) "" else "s"}",
                                    style = HexCladTheme.typography.bodySmall,
                                    color = HexCladTheme.colors.mutedGrey
                                )
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        if (searchResults.isEmpty()) {
                            item {
                                Surface(
                                    color = HexCladTheme.colors.charcoalCard,
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 24.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(24.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "No Cookware Found",
                                            style = HexCladTheme.typography.titleMedium,
                                            color = HexCladTheme.colors.pureWhite
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "Check spelling or search for Sets, Woks, Griddles, or Japanese Knives.",
                                            style = HexCladTheme.typography.bodySmall,
                                            color = HexCladTheme.colors.mutedGrey,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        } else {
                            items(searchResults) { product ->
                                SearchResultCard(
                                    product = product,
                                    onClick = {
                                        onAddRecentSearch(query)
                                        onDismissRequest()
                                        onNavigateToProduct(product.id)
                                    }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BestSellerQuickCard(
    title: String,
    price: String,
    onClick: () -> Unit
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = "TOP RATED",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = title,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "From $price • Lifetime Warranty",
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                }
            }

            Text(
                text = "VIEW →",
                style = HexCladTheme.typography.labelSmall,
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SearchResultCard(
    product: Product,
    onClick: () -> Unit
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Product Thumbnail
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageUrls.firstOrNull())
                    .crossfade(true)
                    .build(),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(HexCladTheme.colors.deepCharcoal)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.category.uppercase(),
                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = HexCladTheme.colors.mutedGrey
                )
                Text(
                    text = product.title,
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price.toInt()}",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    if (product.hasDiscount && product.originalPrice != null) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "$${product.originalPrice.toInt()}",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(3.dp)
                    ) {
                        Text(
                            text = "+${product.entryCount} Entries",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = HexCladTheme.colors.metallicWarmGoldLight,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Text(
                text = "→",
                style = HexCladTheme.typography.titleMedium,
                color = HexCladTheme.colors.metallicWarmGold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
