package com.example.ui.screens.pdp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.HexCladTheme

/**
 * Gordon Ramsay Endorsement Card displaying:
 * - Chef portrait thumbnail
 * - Authoritative endorsement quote
 * - Digital signature graphic & Michelin star verification
 */
@Composable
fun RamsayEndorsementCard(
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.6f)),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .testTag("ramsay_endorsement_card")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            HexCladTheme.colors.metallicWarmGoldContainer.copy(alpha = 0.35f),
                            HexCladTheme.colors.charcoalCard
                        )
                    )
                )
                .padding(18.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Tag & Stars
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Chef Avatar
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(HexCladTheme.colors.charcoalSurfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://hexclad.com/cdn/shop/files/Gordon_Ramsay_Portrait.jpg")
                                .crossfade(true)
                                .build(),
                            contentDescription = "Gordon Ramsay",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(54.dp)
                        )
                        // Fallback initials if network image is loading
                        Text(
                            text = "GR",
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "GORDON RAMSAY",
                                style = HexCladTheme.typography.titleMedium,
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Michelin Star",
                                tint = HexCladTheme.colors.starRatingGold,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Text(
                            text = "Multi-Michelin Star Chef & HexClad Partner",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = HexCladTheme.colors.metallicWarmGoldLight
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Quote Body with Icon
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = Icons.Default.FormatQuote,
                        contentDescription = null,
                        tint = HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.7f),
                        modifier = Modifier.size(26.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "\"Home cooks, you deserve better pans. HexClad's hybrid technology sears like cast iron and cleans like non-stick. I use them in my restaurants, my academies, and in my own kitchen every day.\"",
                        style = HexCladTheme.typography.bodyMedium.copy(
                            fontStyle = FontStyle.Italic,
                            lineHeight = 22.sp
                        ),
                        color = HexCladTheme.colors.lightGrey
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Signature Graphic Box
                Surface(
                    color = HexCladTheme.colors.deepCharcoal,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Gordon Ramsay",
                        style = HexCladTheme.typography.headlineSmall.copy(
                            letterSpacing = 2.sp,
                            fontSize = 16.sp
                        ),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
