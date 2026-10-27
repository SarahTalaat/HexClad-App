package com.example.ui.screens.content

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.RestaurantMenu
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.HexCladTheme

/**
 * Recipe Model for the Culinary Hub.
 */
data class HexCladRecipe(
    val id: String,
    val title: String,
    val category: String, // "Dinner", "Soups", "Quick Meals", "Desserts"
    val cookware: String,
    val prepTime: String,
    val cookTime: String,
    val servings: String,
    val difficulty: String,
    val imageUrl: String,
    val description: String,
    val ingredients: List<String>
)

/**
 * Recipe Hub Screen (Prompt 42)
 *
 * Features:
 * - Category filter chips: "All", "Dinner", "Soups", "Quick Meals", "Desserts"
 * - High-end recipe cards with required cookware tags, cook time, and Gordon Ramsay favorites
 * - Modal or expandable detail view with ingredients and cooking tips
 */
@Composable
fun RecipeScreen(
    onBackClick: () -> Unit,
    onNavigateToProduct: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = listOf("All", "Dinner", "Soups", "Quick Meals", "Desserts")
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedRecipeForModal by remember { mutableStateOf<HexCladRecipe?>(null) }

    val recipes = remember {
        listOf(
            HexCladRecipe(
                id = "pan-seared-bavette-steak",
                title = "Pan-Seared Bavette Steak with Garlic Herb Butter",
                category = "Dinner",
                cookware = "12\" Hybrid Stainless Fry Pan",
                prepTime = "15 min",
                cookTime = "8 min",
                servings = "4 Servings",
                difficulty = "Medium",
                imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=1000&q=80",
                description = "Deep golden caramelized crust sealed with high heat, basted with rosemary, crushed garlic cloves, and chilled unsalted French butter.",
                ingredients = listOf(
                    "2 lbs Prime Bavette or Flank Steak",
                    "4 tbsp Unsalted Grass-Fed Butter",
                    "4 Garlic Cloves, crushed",
                    "3 sprigs Fresh Rosemary & Thyme",
                    "Flaky Maldon Sea Salt & Cracked Black Pepper"
                )
            ),
            HexCladRecipe(
                id = "french-onion-soup",
                title = "HexClassic French Onion Soup with Gruyère Gratin",
                category = "Soups",
                cookware = "8QT Hybrid Stainless Dutch Oven",
                prepTime = "20 min",
                cookTime = "60 min",
                servings = "6 Servings",
                difficulty = "Advanced",
                imageUrl = "https://images.unsplash.com/photo-1547592180-85f173990554?auto=format&fit=crop&w=1000&q=80",
                description = "Slowly caramelized yellow onions reduced with dry white wine and rich beef bone stock, broiled under a molten mantle of cave-aged Gruyère.",
                ingredients = listOf(
                    "5 lbs Yellow Onions, thinly sliced",
                    "6 cups Simmered Beef Bone Broth",
                    "1/2 cup Dry White Wine (Sauvignon Blanc)",
                    "1 Baguette, thickly sliced and toasted",
                    "8 oz Cave-Aged Gruyère Cheese, grated"
                )
            ),
            HexCladRecipe(
                id = "crispy-skin-salmon",
                title = "Crispy King Salmon with Meyer Lemon Beurre Blanc",
                category = "Quick Meals",
                cookware = "10\" Hybrid Stainless Fry Pan",
                prepTime = "10 min",
                cookTime = "10 min",
                servings = "2 Servings",
                difficulty = "Easy",
                imageUrl = "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?auto=format&fit=crop&w=1000&q=80",
                description = "Glass-shattering crispy skin created by pressing salmon fillets into our laser-etched matrix with zero oil splattering or sticking.",
                ingredients = listOf(
                    "2 King Salmon fillets (skin-on, scale-free)",
                    "1 tbsp Extra Virgin Olive Oil",
                    "1 Meyer Lemon, juiced and zested",
                    "3 tbsp Cold Butter Cubes",
                    "Fresh Dill for garnish"
                )
            ),
            HexCladRecipe(
                id = "tamagoyaki-rolled-omelet",
                title = "Sweet Dashi Tamagoyaki Rolled Japanese Omelet",
                category = "Quick Meals",
                cookware = "Hybrid Japanese Egg Pan",
                prepTime = "5 min",
                cookTime = "6 min",
                servings = "2 Servings",
                difficulty = "Medium",
                imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?auto=format&fit=crop&w=1000&q=80",
                description = "Silky layered Japanese omelet rolled effortlessly in the rectangular nonstick valleys with sweet mirin and savory dashi.",
                ingredients = listOf(
                    "4 Large Pasture-Raised Eggs",
                    "3 tbsp Dashi Broth",
                    "1 tbsp Mirin",
                    "1 tsp Usukuchi Light Soy Sauce",
                    "1 tsp Pure Cane Sugar"
                )
            ),
            HexCladRecipe(
                id = "skillet-chocolate-cookie",
                title = "Molten Dark Chocolate Skillet Cookie with Sea Salt",
                category = "Desserts",
                cookware = "8\" Hybrid Stainless Fry Pan",
                prepTime = "15 min",
                cookTime = "18 min",
                servings = "4 Servings",
                difficulty = "Easy",
                imageUrl = "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?auto=format&fit=crop&w=1000&q=80",
                description = "Crispy chewy caramelized edges with a gooey molten chocolate fudge center, baked directly in the oven at 375°F.",
                ingredients = listOf(
                    "1 cup All-Purpose Flour",
                    "1/2 cup Dark Brown Sugar",
                    "1/2 cup Melted Butter",
                    "1 cup 70% Dark Chocolate Chunks",
                    "Vanilla Bean Ice Cream for serving"
                )
            )
        )
    }

    val filteredRecipes = if (selectedCategory == "All") {
        recipes
    } else {
        recipes.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("recipe_screen"),
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
                    modifier = Modifier.testTag("recipe_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "CULINARY RECIPE HUB",
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Title and intro
        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = "TESTED IN CHEF GORDON RAMSAY'S KITCHEN",
                        style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Master Your Cookware",
                    style = HexCladTheme.typography.headlineLarge,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Step-by-step masterclass recipes engineered to leverage HexClad's patented searing and nonstick properties.",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
        }

        // Category Filter Chips
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategory == category
                    Surface(
                        modifier = Modifier
                            .clickable { selectedCategory = category }
                            .testTag("recipe_chip_$category"),
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalCard,
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                        )
                    ) {
                        Text(
                            text = category.uppercase(),
                            style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                            color = if (isSelected) HexCladTheme.colors.deepCharcoal else HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // Recipe Cards
        items(filteredRecipes) { recipe ->
            RecipeCard(
                recipe = recipe,
                onViewRecipe = { selectedRecipeForModal = recipe },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }

    // Recipe Detail Modal Overlay
    selectedRecipeForModal?.let { recipe ->
        androidx.compose.ui.window.Dialog(
            onDismissRequest = { selectedRecipeForModal = null }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 620.dp)
                    .padding(vertical = 20.dp)
                    .testTag("recipe_detail_modal"),
                shape = RoundedCornerShape(12.dp),
                color = HexCladTheme.colors.charcoalCard,
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .verticalScroll(androidx.compose.foundation.rememberScrollState())
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = HexCladTheme.colors.charcoalSurfaceVariant,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = recipe.category.uppercase(),
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        IconButton(onClick = { selectedRecipeForModal = null }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = HexCladTheme.colors.pureWhite
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = recipe.title,
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = recipe.description,
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "RECOMMENDED COOKWARE",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Surface(
                        color = HexCladTheme.colors.charcoalSurfaceVariant,
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Kitchen,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = recipe.cookware,
                                style = HexCladTheme.typography.bodyMedium,
                                color = HexCladTheme.colors.pureWhite,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "KEY INGREDIENTS",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    recipe.ingredients.forEach { ingredient ->
                        Row(
                            modifier = Modifier.padding(vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "•",
                                color = HexCladTheme.colors.metallicWarmGold,
                                style = HexCladTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = ingredient,
                                color = HexCladTheme.colors.lightGrey,
                                style = HexCladTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { selectedRecipeForModal = null },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("CLOSE RECIPE", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun RecipeCard(
    recipe: HexCladRecipe,
    onViewRecipe: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onViewRecipe() }
            .testTag("recipe_card_${recipe.id}"),
        colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(HexCladTheme.colors.charcoalSurfaceVariant)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recipe.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.RestaurantMenu,
                        contentDescription = null,
                        tint = HexCladTheme.colors.charcoalBorder.copy(alpha = 0.5f),
                        modifier = Modifier.size(48.dp)
                    )
                }

                Surface(
                    color = HexCladTheme.colors.deepCharcoal.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(bottomEnd = 6.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = recipe.category.uppercase(),
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = recipe.title,
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = recipe.description,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AccessTime,
                                contentDescription = null,
                                tint = HexCladTheme.colors.mutedGrey,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = recipe.cookTime,
                                style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = HexCladTheme.colors.mutedGrey
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.People,
                                contentDescription = null,
                                tint = HexCladTheme.colors.mutedGrey,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = recipe.servings,
                                style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = HexCladTheme.colors.mutedGrey
                            )
                        }
                    }

                    Text(
                        text = "VIEW RECIPE →",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 11.sp),
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
