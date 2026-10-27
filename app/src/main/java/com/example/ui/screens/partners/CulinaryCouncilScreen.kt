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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Restaurant
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.HexCladTheme

/**
 * Celebrity Chef data model for the HexClad Culinary Council.
 */
data class ChefProfile(
    val id: String,
    val name: String,
    val title: String,
    val accolade: String,
    val restaurants: String,
    val favoriteCookware: String,
    val imageUrl: String,
    val quote: String,
    val bio: String
)

/**
 * HexClad Culinary Council Screen (Prompt 43)
 *
 * Displays celebrity chef profile cards for:
 * - Chef Gordon Ramsay (Lead Partner, 17 Michelin Stars)
 * - Chef Clare Smyth (Core by Clare Smyth, 3 Michelin Stars)
 * - Chef Nancy Silverton (Osteria Mozza, James Beard Outstanding Chef)
 * - Chef Paul Carmichael (Momofuku Seiōbo, Gourmet Traveller Chef of the Year)
 * - Chef Massimo Bottura (Osteria Francescana, 3 Michelin Stars, World's 50 Best #1)
 *
 * Features biography modals detailing Michelin stars and favorite cookware.
 */
@Composable
fun CulinaryCouncilScreen(
    onBackClick: () -> Unit,
    onExploreCookware: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedChefForModal by remember { mutableStateOf<ChefProfile?>(null) }

    val chefs = remember {
        listOf(
            ChefProfile(
                id = "gordon-ramsay",
                name = "Gordon Ramsay",
                title = "Global Culinary Director & Lead Partner",
                accolade = "17 Michelin Stars Worldwide",
                restaurants = "Restaurant Gordon Ramsay (London), Hell's Kitchen, Lucky Cat",
                favoriteCookware = "12\" Hybrid Stainless Fry Pan & Hybrid Wok",
                imageUrl = "https://images.unsplash.com/photo-1577219491135-ce391730fb2c?auto=format&fit=crop&w=1000&q=80",
                quote = "HexClad is the Rolls Royce of pans. I use them in my home and in my restaurants across the world.",
                bio = "Internationally renowned multi-Michelin-starred chef, restaurateur, and television personality. Gordon Ramsay joined HexClad as a lead partner after discovering the pans in his personal California home kitchen. Today he leads HexClad's culinary product development and testing."
            ),
            ChefProfile(
                id = "clare-smyth",
                name = "Clare Smyth, MBE",
                title = "HexClad Culinary Ambassador",
                accolade = "3 Michelin Stars (Core by Clare Smyth)",
                restaurants = "Core by Clare Smyth (London), Oncore (Sydney)",
                favoriteCookware = "Hybrid Japanese Egg Pan & 8QT Dutch Oven",
                imageUrl = "https://images.unsplash.com/photo-1583394838336-acd977736f90?auto=format&fit=crop&w=1000&q=80",
                quote = "Precision is everything in haute cuisine. HexClad gives me total thermal control without delicate proteins catching or burning.",
                bio = "Clare Smyth is the first and only British female chef to hold three Michelin stars in the UK. Renowned for celebrating sustainable British agricultural produce, Chef Smyth insists on cookware that withstands high-volume commercial service with effortless cleanup."
            ),
            ChefProfile(
                id = "nancy-silverton",
                name = "Nancy Silverton",
                title = "Culinary Council Member & Baking Legend",
                accolade = "James Beard Outstanding Chef & Julia Child Award",
                restaurants = "Osteria Mozza, Pizzeria Mozza, chi SPACCA (Los Angeles)",
                favoriteCookware = "Commercial Pizza Steel & Double Burner Griddle",
                imageUrl = "https://images.unsplash.com/photo-1556911220-e15b29be8c8f?auto=format&fit=crop&w=1000&q=80",
                quote = "Baking artisan crusts demands rapid heat transference. HexClad's heavy aluminum tri-ply core delivers flawless crust blisters every time.",
                bio = "Legendary baker, restaurateur, and author who revolutionized artisan sourdough bread and wood-fired pizza in the United States. Winner of the James Beard Outstanding Chef award and star of Netflix's Chef's Table."
            ),
            ChefProfile(
                id = "paul-carmichael",
                name = "Paul Carmichael",
                title = "Caribbean Gastronomy Pioneer",
                accolade = "Gourmet Traveller Chef of the Year",
                restaurants = "Momofuku Seiōbo (Sydney)",
                favoriteCookware = "Hybrid Deep Sauté Pan with Lid",
                imageUrl = "https://images.unsplash.com/photo-1560250097-0b93528c311a?auto=format&fit=crop&w=1000&q=80",
                quote = "Caribbean braises and curries need deep caramelization and steady gentle simmering. HexClad transitions seamlessly between both worlds.",
                bio = "A visionary chef celebrated for elevating Caribbean cuisine to global fine-dining prominence. Formerly executive chef at Momofuku Má Pêche in NYC and Momofuku Seiōbo in Australia."
            ),
            ChefProfile(
                id = "massimo-bottura",
                name = "Massimo Bottura",
                title = "Culinary Icon & Creative Visionary",
                accolade = "3 Michelin Stars & World's 50 Best #1",
                restaurants = "Osteria Francescana (Modena, Italy), Torno Subito",
                favoriteCookware = "Hybrid Roasting Pan & Damascus Steel Knives",
                imageUrl = "https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&w=1000&q=80",
                quote = "Cooking is an act of love and scientific alchemy. HexClad honors traditional Italian culinary heritage through modern laser engineering.",
                bio = "Culinary maestro whose Modena restaurant Osteria Francescana was twice named the best restaurant in the world. Massimo combines contemporary modern art with deeply rooted Italian culinary traditions."
            )
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("culinary_council_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
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
                    modifier = Modifier.testTag("culinary_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "THE CULINARY COUNCIL",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Hero Section
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
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "WORLD-CLASS MASTER CHEFS",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Text(
                        text = "Tested by the World's\nMost Discerning Palates",
                        style = HexCladTheme.typography.headlineLarge,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "From 3-star Michelin temples in London and Modena to legendary Los Angeles trattorias, the world's greatest culinary masters cook with HexClad.",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
            }
        }

        // List of Chef Cards
        items(chefs) { chef ->
            ChefCard(
                chef = chef,
                onClick = { selectedChefForModal = chef },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

    // Biography & Details Modal
    selectedChefForModal?.let { chef ->
        Dialog(onDismissRequest = { selectedChefForModal = null }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 620.dp)
                    .padding(vertical = 16.dp)
                    .testTag("chef_biography_modal"),
                shape = RoundedCornerShape(12.dp),
                color = HexCladTheme.colors.charcoalCard,
                border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.6f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = HexCladTheme.colors.metallicWarmGoldContainer,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = chef.accolade.uppercase(),
                                style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = HexCladTheme.colors.metallicWarmGoldLight,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        IconButton(
                            onClick = { selectedChefForModal = null },
                            modifier = Modifier.testTag("btn_close_chef_modal")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = chef.name,
                        style = HexCladTheme.typography.headlineMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = chef.title,
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.metallicWarmGold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Quote Card
                    Surface(
                        color = HexCladTheme.colors.charcoalSurfaceVariant,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "\"${chef.quote}\"",
                                style = HexCladTheme.typography.bodyMedium,
                                color = HexCladTheme.colors.pureWhite,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                lineHeight = 22.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "BIOGRAPHY & PHILOSOPHY",
                        style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = chef.bio,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "FLAGSHIP RESTAURANTS",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.mutedGrey,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = chef.restaurants,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.pureWhite
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "FAVORITE HEXCLAD GEAR",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.mutedGrey,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = chef.favoriteCookware,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.announcementGreenText,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { selectedChefForModal = null },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("CLOSE PROFILE", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun ChefCard(
    chef: ChefProfile,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("chef_card_${chef.id}"),
        colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Chef Initials Avatar Circle
            Surface(
                modifier = Modifier.size(56.dp),
                shape = CircleShape,
                color = HexCladTheme.colors.charcoalSurfaceVariant,
                border = BorderStroke(1.5.dp, HexCladTheme.colors.metallicWarmGold)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    val initials = chef.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.joinToString("")
                    Text(
                        text = initials,
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = chef.accolade,
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = chef.name,
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = chef.title,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Favorite: ${chef.favoriteCookware}",
                    style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = HexCladTheme.colors.announcementGreenText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Text(
                text = "BIO →",
                style = HexCladTheme.typography.labelSmall,
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
