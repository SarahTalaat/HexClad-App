package com.example.ui.screens.pdp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.HexCladTheme
import kotlinx.coroutines.launch

/**
 * Image Gallery Carousel using HorizontalPager with thumbnail row selectors
 * to scroll through high-res product photos (e.g. Hybrid Japanese Egg Pan angle shots).
 */
@Composable
fun ProductImageGallery(
    imageUrls: List<String>,
    productTitle: String,
    modifier: Modifier = Modifier
) {
    // Fallback if empty
    val images = if (imageUrls.isEmpty()) {
        listOf(
            "https://hexclad.com/cdn/shop/files/JapaneseEggPan_Hero.jpg",
            "https://hexclad.com/cdn/shop/files/JapaneseEggPan_InUse.jpg",
            "https://hexclad.com/cdn/shop/files/JapaneseEggPan_Detail.jpg"
        )
    } else {
        imageUrls
    }

    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag("pdp_image_gallery")
    ) {
        // Main Horizontal Pager
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(HexCladTheme.colors.charcoalSurfaceVariant)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // Fallback luxury geometric icon
                    Icon(
                        imageVector = Icons.Default.Kitchen,
                        contentDescription = null,
                        tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.4f),
                        modifier = Modifier.size(80.dp)
                    )

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[page])
                            .crossfade(true)
                            .build(),
                        contentDescription = "$productTitle - Photo ${page + 1}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Image Counter Overlay Pill (e.g., "1 / 3")
            Surface(
                color = HexCladTheme.colors.deepCharcoal.copy(alpha = 0.8f),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(14.dp)
            ) {
                Text(
                    text = "${pagerState.currentPage + 1} / ${images.size}",
                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            // Indicator Dots Overlay
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(images.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) HexCladTheme.colors.metallicWarmGold
                                else HexCladTheme.colors.pureWhite.copy(alpha = 0.4f)
                            )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Thumbnail Row Selectors
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(images) { index, url ->
                val isSelected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(HexCladTheme.colors.charcoalCard)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .testTag("thumbnail_selector_$index")
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Thumbnail ${index + 1}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
