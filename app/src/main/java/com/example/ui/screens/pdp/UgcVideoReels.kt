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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.components.VideoPlayerDialog
import com.example.ui.theme.HexCladTheme

data class UgcVideo(
    val id: String,
    val creatorHandle: String,
    val recipeTitle: String,
    val viewCount: String,
    val thumbnail: String
)

/**
 * User UGC Video Reels Carousel with interactive video playback modal.
 */
@Composable
fun UgcVideoReels(
    modifier: Modifier = Modifier
) {
    var activePlayingVideo by remember { mutableStateOf<UgcVideo?>(null) }

    val reels = listOf(
        UgcVideo(
            id = "ugc-1",
            creatorHandle = "@chef_marcus_sears",
            recipeTitle = "Crispy Skin Salmon Test",
            viewCount = "2.4M",
            thumbnail = "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?auto=format&fit=crop&w=1000&q=80"
        ),
        UgcVideo(
            id = "ugc-2",
            creatorHandle = "@homecook_sara",
            recipeTitle = "Zero-Oil Egg Slide Challenge",
            viewCount = "1.8M",
            thumbnail = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=1000&q=80"
        ),
        UgcVideo(
            id = "ugc-3",
            creatorHandle = "@bbq_pitmaster_dan",
            recipeTitle = "Dry-Aged Ribeye Stovetop Sear",
            viewCount = "3.1M",
            thumbnail = "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=1000&q=80"
        ),
        UgcVideo(
            id = "ugc-4",
            creatorHandle = "@pastalover_julia",
            recipeTitle = "Truffle Cacio e Pepe in 12\" Pan",
            viewCount = "980K",
            thumbnail = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=1000&q=80"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
            .testTag("ugc_video_reels_section")
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "THE SECRET'S OUT!",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.5.sp,
                    fontSize = 11.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "(More Than a Million of You Love Us!)",
                style = HexCladTheme.typography.headlineSmall,
                color = HexCladTheme.colors.pureWhite
            )
            Text(
                text = "Watch real kitchen tests from home cooks and Michelin-star chefs.",
                style = HexCladTheme.typography.bodySmall,
                color = HexCladTheme.colors.mutedGrey
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(reels) { reel ->
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .height(240.dp)
                        .clickable { activePlayingVideo = reel }
                        .testTag("reel_${reel.id}"),
                    colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(reel.thumbnail)
                                .crossfade(true)
                                .build(),
                            contentDescription = reel.recipeTitle,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            HexCladTheme.colors.deepCharcoal.copy(alpha = 0.85f)
                                        ),
                                        startY = 100f
                                    )
                                )
                        )

                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(HexCladTheme.colors.pureWhite.copy(alpha = 0.9f))
                                .align(Alignment.Center),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play Reel",
                                tint = HexCladTheme.colors.deepCharcoal,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Surface(
                            color = HexCladTheme.colors.deepCharcoal.copy(alpha = 0.7f),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.pureWhite,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = reel.viewCount,
                                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                                    color = HexCladTheme.colors.pureWhite,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = reel.recipeTitle,
                                style = HexCladTheme.typography.bodySmall.copy(lineHeight = 14.sp),
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = reel.creatorHandle,
                                style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                color = HexCladTheme.colors.metallicWarmGoldLight
                            )
                        }
                    }
                }
            }
        }
    }

    // Interactive Video Player Modal when tapped
    activePlayingVideo?.let { video ->
        VideoPlayerDialog(
            videoTitle = video.recipeTitle,
            creatorOrSubtitle = "Creator: ${video.creatorHandle} • ${video.viewCount} Views",
            thumbnailUrl = video.thumbnail,
            onDismiss = { activePlayingVideo = null }
        )
    }
}
