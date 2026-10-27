package com.example.ui.screens.content

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.ui.theme.HexCladTheme

/**
 * About Us & Origin Screen (Prompt 40)
 *
 * Features:
 * - Hero banner imagery celebrating culinary craftsmanship
 * - Gordon Ramsay prominent quote section & endorsement
 * - HexClad brand origin story: Replacing disposable nonstick pans with patented hybrid technology that lasts a lifetime
 * - Engineering pillars: Tri-ply stainless steel, aluminum core, laser-etched hexagonal lattice
 */
@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    onExploreCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("about_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Navigation Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("about_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "ABOUT HEXCLAD",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 1. Hero Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                HexCladTheme.colors.charcoalSurfaceVariant,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://images.unsplash.com/photo-1556911220-e15b29be8c8f?auto=format&fit=crop&w=1000&q=80")
                        .crossfade(true)
                        .build(),
                    contentDescription = "HexClad Cookware Craftsmanship",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark gradient overlay for readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    HexCladTheme.colors.deepCharcoal.copy(alpha = 0.5f),
                                    HexCladTheme.colors.deepCharcoal.copy(alpha = 0.95f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f))
                    ) {
                        Text(
                            text = "BORN TO DISRUPT COOKWARE",
                            style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "The Cookware Revolution\nBuilt to Last a Lifetime",
                        style = HexCladTheme.typography.headlineLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 2. Gordon Ramsay Signature Quote Section
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag("gordon_ramsay_quote_card"),
                colors = CardDefaults.cardColors(
                    containerColor = HexCladTheme.colors.charcoalCard
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = HexCladTheme.colors.metallicWarmGoldContainer,
                            shape = CircleShape,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.FormatQuote,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.metallicWarmGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.starRatingGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "\"HexClad is the Rolls Royce of pans. The way they sear steak while cleaning up like nonstick in seconds is unmatched anywhere in professional gastronomy.\"",
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.pureWhite,
                        lineHeight = 26.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(44.dp),
                            shape = CircleShape,
                            color = HexCladTheme.colors.charcoalSurfaceVariant,
                            border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "GR",
                                    style = HexCladTheme.typography.titleMedium,
                                    color = HexCladTheme.colors.metallicWarmGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Chef Gordon Ramsay",
                                style = HexCladTheme.typography.titleSmall,
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Lead Partner & Global Culinary Director",
                                style = HexCladTheme.typography.bodySmall,
                                color = HexCladTheme.colors.metallicWarmGoldLight
                            )
                        }
                    }
                }
            }
        }

        // 3. HexClad Brand Origin Story
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                Text(
                    text = "THE PROBLEM WE SOLVED",
                    style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Why Cookware Was Broken",
                    style = HexCladTheme.typography.headlineMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "For decades, home chefs and restaurant kitchens faced an impossible tradeoff. Traditional nonstick was convenient to clean, but scratched easily, couldn't handle metal utensils, and had to be replaced every 12 to 24 months—filling landfills with toxic disposable pans.\n\nStainless steel and cast iron delivered deep golden searing and longevity, but required heavy grease, meticulous heat regulation, and painstaking scrubbing.",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.lightGrey,
                    lineHeight = 24.sp
                )
            }
        }

        // 4. The HexClad Hybrid Invention
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "OUR PATENTED BREAKTHROUGH",
                        style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                        color = HexCladTheme.colors.announcementGreenText,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "The Laser-Etched Matrix",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "HexClad patented an ingenious laser-etching process that forms a matrix of raised stainless steel hexagonal peaks and recessed nonstick valleys.\n\n• Raised Stainless Steel Peaks: Deliver restaurant-grade golden caramelization and protect the pan from forks, metal whisks, and spatulas.\n• Recessed Japanese Nonstick Valleys: Prevent delicate proteins like eggs, salmon, and scallops from sticking, requiring little to no butter or oil.\n• Tri-Ply Aluminum Core: Delivers instant, corner-to-corner heat conductivity with zero cold spots.",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        // 5. Lifetime Warranty & Sustainability Promise
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalSurfaceVariant),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = CircleShape,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Guaranteed for Life",
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "HexClad cookware is engineered to outlast a lifetime. Every piece carries our complete manufacturer warranty.",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }
                }
            }
        }

        // 6. Shop CTA Button
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = onExploreCookware,
                colors = ButtonDefaults.buttonColors(
                    containerColor = HexCladTheme.colors.metallicWarmGold,
                    contentColor = HexCladTheme.colors.deepCharcoal
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(horizontal = 16.dp)
                    .testTag("about_shop_now_btn")
            ) {
                Text(
                    text = "EXPERIENCE HEXCLAD COOKWARE",
                    style = HexCladTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
