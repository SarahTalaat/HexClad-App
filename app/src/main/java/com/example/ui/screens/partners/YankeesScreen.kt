package com.example.ui.screens.partners

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
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
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
import androidx.compose.ui.graphics.Color
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
 * New York Yankees Official Partnership Screen (Prompt 44)
 *
 * Details:
 * - "OFFICIAL COOKWARE SPONSOR OF THE NEW YORK YANKEES™"
 * - Stadium hospitality program at Yankee Stadium (Champions Suite & Legends Club)
 * - Quotes from Executive Chefs and Yankees stadium culinary leadership
 * - High-heat durability story under high-volume stadium foodservice
 */
@Composable
fun YankeesScreen(
    onBackClick: () -> Unit,
    onExploreCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navyBlue = Color(0xFF0C2340) // Iconic Yankees Navy

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("yankees_screen"),
        contentPadding = PaddingValues(bottom = 36.dp)
    ) {
        // Nav Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("yankees_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "HEXCLAD x NEW YORK YANKEES™",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Hero Banner with Yankees Navy & Gold accents
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                navyBlue,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://images.unsplash.com/photo-1508098682722-e99c43a406b2?auto=format&fit=crop&w=1000&q=80")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Yankee Stadium Hospitality",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    navyBlue.copy(alpha = 0.6f),
                                    HexCladTheme.colors.deepCharcoal.copy(alpha = 0.96f)
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
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.SportsBaseball,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "OFFICIAL PARTNER",
                                style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "OFFICIAL COOKWARE SPONSOR\nOF THE NEW YORK YANKEES™",
                        style = HexCladTheme.typography.headlineLarge.copy(fontSize = 24.sp),
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 30.sp
                    )
                }
            }
        }

        // Executive Chef Endorsement Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "YANKEE STADIUM EXECUTIVE KITCHENS",
                            style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "\"When you are catering prime steaks and artisanal dishes for thousands of guests in the Champions Suites during a playoff game, your pans cannot fail. HexClad gives our culinary brigades unmatched searing heat, zero sticking, and instant turnaround between courses.\"",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "— Senior Executive Chef, Yankee Stadium Premium Hospitality",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey
                    )
                }
            }
        }

        // Stadium Hospitality Highlights
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                Text(
                    text = "THE CHAMPIONS SUITE EXPERIENCE",
                    style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Elevating Baseball Hospitality",
                    style = HexCladTheme.typography.headlineSmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "HexClad cookware powers the high-heat cooking stations across Yankee Stadium's finest dining destinations:\n\n• Legends Suite Club: Hand-carved prime ribeye and Wagyu steaks seared live in HexClad 14\" Commercial Skillets.\n• Champions Suite Lounges: Flash-sautéed seafood and delicate pastas prepared on induction ranges.\n• Monument Park VIP Events: Private dining experiences curated by celebrity guest chefs.",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.lightGrey,
                    lineHeight = 22.sp
                )
            }
        }

        // Durability Pillar Card
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = HexCladTheme.colors.charcoalSurfaceVariant,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier.size(46.dp),
                        shape = CircleShape,
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Kitchen,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = "Commercial League Durability",
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "If HexClad can handle the speed, fire, and volume of Yankee Stadium, it will master anything in your home kitchen.",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }
                }
            }
        }

        // Shop Cookware CTA Button
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
                    .testTag("yankees_shop_now_btn")
            ) {
                Text(
                    text = "GET THE YANKEES' COOKWARE",
                    style = HexCladTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
