package com.example.ui.screens.partners

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.VolunteerActivism
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.HexCladTheme

/**
 * Community Partners Screen (Prompt 45)
 *
 * Showcases:
 * 1. Food For Soul (Massimo Bottura's global non-profit combating food waste and social vulnerability)
 * 2. Little Kitchen Academy (Montessori-inspired culinary academy empowering kids ages 3-18+)
 * Includes video banners, mission breakdowns, partnership badges, and impact statistics.
 */
@Composable
fun CommunityPartnersScreen(
    onBackClick: () -> Unit,
    onExploreCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedPartnerTab by remember { mutableStateOf("ALL") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("community_partners_screen"),
        contentPadding = PaddingValues(bottom = 40.dp)
    ) {
        // Top Navigation Bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("community_back_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "COMMUNITY IMPACT",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.mutedGrey,
                    letterSpacing = 1.4.sp
                )
            }
        }

        // Hero Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                HexCladTheme.colors.charcoalSurfaceVariant,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.VolunteerActivism,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "PURPOSE-DRIVEN PARTNERSHIPS",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = "Cooking For Good.\nInspiring The Future.",
                        style = HexCladTheme.typography.displayMedium,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "HexClad believes exceptional cookware should serve more than our dinner tables. Through our community partnerships, we actively rescue food surplus, feed vulnerable families, and educate young minds on nutrition.",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Tab Selector
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HexCladTheme.colors.charcoalCard)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PartnerTabButton(
                            title = "All Partners",
                            isSelected = selectedPartnerTab == "ALL",
                            onClick = { selectedPartnerTab = "ALL" },
                            modifier = Modifier.weight(1f)
                        )
                        PartnerTabButton(
                            title = "Food For Soul",
                            isSelected = selectedPartnerTab == "SOUL",
                            onClick = { selectedPartnerTab = "SOUL" },
                            modifier = Modifier.weight(1f)
                        )
                        PartnerTabButton(
                            title = "Little Kitchen Academy",
                            isSelected = selectedPartnerTab == "LKA",
                            onClick = { selectedPartnerTab = "LKA" },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Section 1: Food For Soul
        if (selectedPartnerTab == "ALL" || selectedPartnerTab == "SOUL") {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                FoodForSoulSection(
                    onLearnMore = onExploreCookware
                )
            }
        }

        // Section 2: Little Kitchen Academy
        if (selectedPartnerTab == "ALL" || selectedPartnerTab == "LKA") {
            item {
                Spacer(modifier = Modifier.height(28.dp))
                LittleKitchenAcademySection(
                    onExploreCookware = onExploreCookware
                )
            }
        }

        // Shared Impact Statement & Cookware CTA
        item {
            Spacer(modifier = Modifier.height(32.dp))
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.WorkspacePremium,
                        contentDescription = null,
                        tint = HexCladTheme.colors.metallicWarmGold,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "EQUIPPING THOSE WHO FEED THE WORLD",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        letterSpacing = 1.3.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Every HexClad Purchase Drives Direct Community Impact",
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "A percentage of proceeds from every hybrid cookware order supports student chef scholarships and equips community Refettorio kitchens with lifetime cookware.",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        onClick = onExploreCookware,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("community_shop_cta")
                    ) {
                        Text(
                            text = "SHOP WITH IMPACT",
                            style = HexCladTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FoodForSoulSection(
    onLearnMore: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Partnership Badge
        Surface(
            color = HexCladTheme.colors.charcoalSurfaceVariant,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Public,
                    contentDescription = null,
                    tint = HexCladTheme.colors.metallicWarmGold,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "OFFICIAL SUSTAINABILITY PARTNER • FOOD FOR SOUL",
                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = HexCladTheme.colors.metallicWarmGoldLight,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Food For Soul & Refettorio",
            style = HexCladTheme.typography.headlineMedium,
            color = HexCladTheme.colors.pureWhite
        )

        Text(
            text = "Founded by Michelin 3-Star Chef Massimo Bottura and Lara Gilmore",
            style = HexCladTheme.typography.bodySmall,
            color = HexCladTheme.colors.mutedGrey
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Simulated Video Banner
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://hexclad.com/cdn/shop/files/FoodForSoul_Refettorio.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Food For Soul Refettorio Kitchen",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark Gradient Scrim
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC121212))
                            )
                        )
                )

                // Center Play Icon
                Surface(
                    color = HexCladTheme.colors.metallicWarmGold,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Watch Mission Video",
                        tint = HexCladTheme.colors.deepCharcoal,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Video Label
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "VIDEO: THE REFETTORIO REVOLUTION",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold
                    )
                    Text(
                        text = "Transforming Surplus Food Into Dignity",
                        style = HexCladTheme.typography.titleSmall,
                        color = HexCladTheme.colors.pureWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Chef Bottura Quote
        Surface(
            color = HexCladTheme.colors.charcoalCard,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = null,
                    tint = HexCladTheme.colors.metallicWarmGold,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "\"Cooking is an act of love. By transforming surplus ingredients with the highest level of culinary technique and indestructible tools, we nourish both bodies and souls.\"",
                    style = HexCladTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic),
                    color = HexCladTheme.colors.pureWhite
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "— Chef Massimo Bottura, Founder of Food For Soul & Osteria Francescana",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Key Impact Metrics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImpactStatCard(
                stat = "1.2M+",
                label = "Meals Served With Dignity",
                modifier = Modifier.weight(1f)
            )
            ImpactStatCard(
                stat = "950 Tons",
                label = "Surplus Food Rescued",
                modifier = Modifier.weight(1f)
            )
            ImpactStatCard(
                stat = "14",
                label = "Global Refettorios",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun LittleKitchenAcademySection(
    onExploreCookware: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // Partnership Badge
        Surface(
            color = HexCladTheme.colors.charcoalSurfaceVariant,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ChildCare,
                    contentDescription = null,
                    tint = HexCladTheme.colors.announcementGreenText,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "FOUNDATIONAL EQUIPMENT PARTNER • LITTLE KITCHEN ACADEMY",
                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = HexCladTheme.colors.announcementGreenText,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Little Kitchen Academy",
            style = HexCladTheme.typography.headlineMedium,
            color = HexCladTheme.colors.pureWhite
        )

        Text(
            text = "Montessori-inspired culinary academy for children and teens ages 3 to 18+",
            style = HexCladTheme.typography.bodySmall,
            color = HexCladTheme.colors.mutedGrey
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Simulated Academy Video Banner
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://hexclad.com/cdn/shop/files/LKA_Classroom_Kids.jpg")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Little Kitchen Academy Students Cooking with HexClad",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Dark Gradient Scrim
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC121212))
                            )
                        )
                )

                // Center Play Icon
                Surface(
                    color = HexCladTheme.colors.pureWhite,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Watch LKA Video",
                        tint = HexCladTheme.colors.deepCharcoal,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                // Video Label
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "FEATURE: INDEPENDENCE THROUGH COOKING",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.announcementGreenText
                    )
                    Text(
                        text = "How Kids Master Healthy Life Skills with HexClad",
                        style = HexCladTheme.typography.titleSmall,
                        color = HexCladTheme.colors.pureWhite
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Academy Philosophy Card
        Surface(
            color = HexCladTheme.colors.charcoalCard,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = null,
                        tint = HexCladTheme.colors.metallicWarmGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "EQUIPPING FUTURE CHEFS",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Every student station at Little Kitchen Academy is equipped with HexClad pans. The hybrid stainless steel and nonstick surface allows children to safely learn real searing and sautéing without damaging the pans or worrying about harsh nonstick flaking.",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.lightGrey
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Key LKA Metrics
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImpactStatCard(
                stat = "50+",
                label = "Academies In North America",
                modifier = Modifier.weight(1f)
            )
            ImpactStatCard(
                stat = "100k+",
                label = "Hours of Student Cooking",
                modifier = Modifier.weight(1f)
            )
            ImpactStatCard(
                stat = "Ages 3-18",
                label = "Empowered Young Minds",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ImpactStatCard(
    stat: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stat,
                style = HexCladTheme.typography.titleMedium,
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = HexCladTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = HexCladTheme.colors.mutedGrey,
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )
        }
    }
}

@Composable
fun PartnerTabButton(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = if (isSelected) HexCladTheme.colors.metallicWarmGold else Color.Transparent,
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .padding(2.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = HexCladTheme.typography.labelSmall,
                color = if (isSelected) HexCladTheme.colors.deepCharcoal else HexCladTheme.colors.mutedGrey,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
