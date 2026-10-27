package com.example.navigation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.data.MockDataProvider
import com.example.model.AddOn
import com.example.model.CartItem
import com.example.model.PolicyType
import com.example.model.Product
import com.example.ui.screens.checkout.ExpressCheckout
import com.example.ui.screens.checkout.OrderConfirmationView
import com.example.ui.screens.checkout.PaymentFormData
import com.example.ui.screens.checkout.PaymentMethodSelector
import com.example.ui.screens.checkout.ShippingAddressForm
import com.example.ui.screens.checkout.ShippingFormData
import com.example.ui.theme.HexCladTheme
import com.example.viewmodel.HexCladViewModel

import com.example.ui.components.FooterSection
import com.example.ui.screens.auth.AuthScreen
import com.example.ui.screens.auth.ProfileScreen
import com.example.ui.screens.checkout.OrderSummarySidebar
import com.example.ui.screens.content.AboutScreen
import com.example.ui.screens.content.RecipeScreen
import com.example.ui.screens.content.ScienceScreen
import com.example.ui.screens.partners.CommunityPartnersScreen
import com.example.ui.screens.partners.CulinaryCouncilScreen
import com.example.ui.screens.partners.YankeesScreen

/**
 * Route declarations for HexClad Navigation.
 */
object Routes {
    const val HOME = "home"
    const val CATALOG = "catalog"
    const val PRODUCT_DETAIL = "product_detail/{id}"
    const val CART = "cart"
    const val CHECKOUT = "checkout"
    const val SUPPORT = "support"
    const val ABOUT = "about"
    const val SCIENCE = "science"
    const val RECIPES = "recipes"
    const val CULINARY_COUNCIL = "culinary_council"
    const val YANKEES = "yankees"
    const val COMMUNITY_PARTNERS = "community_partners"
    const val AUTH = "auth"
    const val PROFILE = "profile"

    fun productDetail(productId: String): String = "product_detail/$productId"
}

/**
 * Navigation Host connecting all required routes:
 * Home, Catalog, ProductDetail/{id}, Cart, Checkout, Support,
 * About, Science, Recipes, Culinary Council, and Yankees.
 */
@Composable
fun HexCladNavGraph(
    navController: NavHostController,
    viewModel: HexCladViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier.fillMaxSize()
    ) {
        // 1. Home Destination
        composable(Routes.HOME) {
            HomeScreen(
                onNavigateToCatalog = { navController.navigate(Routes.CATALOG) },
                onNavigateToProduct = { id -> navController.navigate(Routes.productDetail(id)) },
                onAddToCart = { product -> viewModel.addToCart(product) },
                onOpenPolicy = { policyType -> viewModel.selectPolicy(policyType) },
                onNavigateToAbout = { navController.navigate(Routes.ABOUT) },
                onNavigateToScience = { navController.navigate(Routes.SCIENCE) },
                onNavigateToRecipes = { navController.navigate(Routes.RECIPES) },
                onNavigateToCulinaryCouncil = { navController.navigate(Routes.CULINARY_COUNCIL) },
                onNavigateToYankees = { navController.navigate(Routes.YANKEES) },
                onNavigateToCommunityPartners = { navController.navigate(Routes.COMMUNITY_PARTNERS) }
            )
        }

        // 2. Catalog Destination (Prompts 15-21)
        composable(Routes.CATALOG) {
            com.example.ui.screens.ProductCatalogScreen(
                currentCategory = uiState.selectedCategory,
                searchQuery = uiState.searchQuery,
                onNavigateToProduct = { id -> navController.navigate(Routes.productDetail(id)) },
                onQuickAddToCart = { product -> viewModel.addToCart(product) }
            )
        }

        // 3. Product Detail Destination
        composable(
            route = Routes.PRODUCT_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("id") ?: ""
            val product = MockDataProvider.getProductById(productId) ?: MockDataProvider.products.first()

            com.example.ui.screens.pdp.ProductDetailScreen(
                product = product,
                onBackClick = { navController.popBackStack() },
                onAddToCart = { prod, variant, qty ->
                    viewModel.addToCart(prod, variant, qty)
                    navController.navigate(Routes.CART)
                }
            )
        }

        // 4. Cart Destination
        composable(Routes.CART) {
            CartScreen(
                cartItems = uiState.cartItems,
                subtotal = uiState.cartSubtotal,
                goldenTicketEntries = uiState.totalGoldenTicketEntries,
                onUpdateQuantity = { id, variant, qty -> viewModel.updateQuantity(id, variant, qty) },
                onRemoveItem = { id, variant -> viewModel.removeFromCart(id, variant) },
                onContinueShopping = { navController.navigate(Routes.CATALOG) },
                onProceedToCheckout = { navController.navigate(Routes.CHECKOUT) }
            )
        }

        // 5. Checkout Destination
        composable(Routes.CHECKOUT) {
            CheckoutScreen(
                cartItems = uiState.cartItems,
                subtotal = uiState.cartSubtotal,
                goldenTicketEntries = uiState.totalGoldenTicketEntries,
                checkoutDetails = uiState.checkoutDetails,
                isOrderPlaced = uiState.isOrderPlaced,
                orderNumber = uiState.lastOrderNumber,
                onUpdateDetails = { viewModel.updateCheckoutDetails(it) },
                onPlaceOrder = { viewModel.completeOrder() },
                onBackToCart = { navController.popBackStack() },
                onOrderDismissed = {
                    viewModel.resetOrderState()
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onViewSupport = {
                    viewModel.resetOrderState()
                    navController.navigate(Routes.SUPPORT)
                }
            )
        }

        // 6. Support Destination
        composable(Routes.SUPPORT) {
            SupportScreen(
                onOpenPolicy = { policyType -> viewModel.selectPolicy(policyType) },
                onBackClick = { navController.popBackStack() },
                onNavigateToAbout = { navController.navigate(Routes.ABOUT) },
                onNavigateToScience = { navController.navigate(Routes.SCIENCE) },
                onNavigateToRecipes = { navController.navigate(Routes.RECIPES) },
                onNavigateToCulinaryCouncil = { navController.navigate(Routes.CULINARY_COUNCIL) },
                onNavigateToYankees = { navController.navigate(Routes.YANKEES) },
                onNavigateToCommunityPartners = { navController.navigate(Routes.COMMUNITY_PARTNERS) }
            )
        }

        // 7. About Screen (Prompt 40)
        composable(Routes.ABOUT) {
            AboutScreen(
                onBackClick = { navController.popBackStack() },
                onExploreCookware = { navController.navigate(Routes.CATALOG) }
            )
        }

        // 8. Science Screen (Prompt 41)
        composable(Routes.SCIENCE) {
            ScienceScreen(
                onBackClick = { navController.popBackStack() },
                onExploreCookware = { navController.navigate(Routes.CATALOG) }
            )
        }

        // 9. Recipe Hub Screen (Prompt 42)
        composable(Routes.RECIPES) {
            RecipeScreen(
                onBackClick = { navController.popBackStack() },
                onNavigateToProduct = { id -> navController.navigate(Routes.productDetail(id)) }
            )
        }

        // 10. Culinary Council Screen (Prompt 43)
        composable(Routes.CULINARY_COUNCIL) {
            CulinaryCouncilScreen(
                onBackClick = { navController.popBackStack() },
                onExploreCookware = { navController.navigate(Routes.CATALOG) }
            )
        }

        // 11. New York Yankees Screen (Prompt 44)
        composable(Routes.YANKEES) {
            YankeesScreen(
                onBackClick = { navController.popBackStack() },
                onExploreCookware = { navController.navigate(Routes.CATALOG) }
            )
        }

        // 12. Community Partners Screen (Prompt 45)
        composable(Routes.COMMUNITY_PARTNERS) {
            CommunityPartnersScreen(
                onBackClick = { navController.popBackStack() },
                onExploreCookware = { navController.navigate(Routes.CATALOG) }
            )
        }

        // 13. Auth Screen (Prompt 48)
        composable(Routes.AUTH) {
            AuthScreen(
                onBackClick = { navController.popBackStack() },
                onAuthSuccess = { email, name ->
                    viewModel.signIn(email, name)
                    navController.navigate(Routes.PROFILE) {
                        popUpTo(Routes.AUTH) { inclusive = true }
                    }
                },
                onContinueAsGuest = {
                    navController.popBackStack()
                }
            )
        }

        // 14. Profile Screen (Prompt 48)
        composable(Routes.PROFILE) {
            ProfileScreen(
                user = uiState.currentUser,
                pastOrders = uiState.pastOrders,
                onBackClick = { navController.popBackStack() },
                onSignOut = {
                    viewModel.signOut()
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) { inclusive = true }
                    }
                },
                onOpenGoldenTicket = {
                    viewModel.openGoldenTicketModal()
                },
                onNavigateToSupport = {
                    navController.navigate(Routes.SUPPORT)
                }
            )
        }
    }
}

// ==============================================================================
// 1. HOME SCREEN
// ==============================================================================
@Composable
fun HomeScreen(
    onNavigateToCatalog: () -> Unit,
    onNavigateToProduct: (String) -> Unit,
    onAddToCart: (Product) -> Unit,
    onOpenPolicy: (PolicyType) -> Unit,
    onNavigateToAbout: () -> Unit = {},
    onNavigateToScience: () -> Unit = {},
    onNavigateToRecipes: () -> Unit = {},
    onNavigateToCulinaryCouncil: () -> Unit = {},
    onNavigateToYankees: () -> Unit = {},
    onNavigateToCommunityPartners: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Hero Section
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                HexCladTheme.colors.deepCharcoal,
                                HexCladTheme.colors.charcoalSurfaceVariant,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = "GORDON RAMSAY APPROVED",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        )
                    }

                    Text(
                        text = "The Rolls Royce\nof Pans",
                        style = HexCladTheme.typography.displayMedium,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Patented laser-etched hexagon matrix with tri-ply stainless steel & non-stick valleys. Built to sear like steel, clean like non-stick.",
                        style = HexCladTheme.typography.bodyLarge,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = onNavigateToCatalog,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("explore_catalog_btn")
                    ) {
                        Text(
                            text = "SHOP ALL COOKWARE",
                            style = HexCladTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Bestseller Product Showcase
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                Text(
                    text = "Most Wanted Cookware",
                    style = HexCladTheme.typography.headlineMedium,
                    color = HexCladTheme.colors.pureWhite
                )
                Text(
                    text = "Includes Gordon's Golden Ticket giveaway entries on all sets",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
        }

        items(MockDataProvider.products.take(3)) { product ->
            CatalogProductCard(
                product = product,
                onCardClick = { onNavigateToProduct(product.id) },
                onAddToCart = { onAddToCart(product) }
            )
        }

        // Explore HexClad Brand & Innovation Section (Prompts 40-44)
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "EXPLORE HEXCLAD INNOVATION",
                    style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Culinary masters, patented science, and iconic partnerships",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
                Spacer(modifier = Modifier.height(12.dp))

                HomeBrandLinkCard(
                    title = "The Science of HexClad",
                    subtitle = "Patented laser-etched steel peaks and nonstick valleys",
                    tag = "ENGINEERING",
                    onClick = onNavigateToScience,
                    testTag = "home_nav_science"
                )
                Spacer(modifier = Modifier.height(8.dp))

                HomeBrandLinkCard(
                    title = "The Culinary Council",
                    subtitle = "Chef Gordon Ramsay, Clare Smyth, Nancy Silverton & masters",
                    tag = "CHEF NETWORK",
                    onClick = onNavigateToCulinaryCouncil,
                    testTag = "home_nav_culinary_council"
                )
                Spacer(modifier = Modifier.height(8.dp))

                HomeBrandLinkCard(
                    title = "Masterclass Recipe Hub",
                    subtitle = "Tested recipes for steaks, soups, seafood & skillet desserts",
                    tag = "RECIPES",
                    onClick = onNavigateToRecipes,
                    testTag = "home_nav_recipes"
                )
                Spacer(modifier = Modifier.height(8.dp))

                HomeBrandLinkCard(
                    title = "New York Yankees™ Official Cookware",
                    subtitle = "Powering Yankee Stadium's Champions Suite culinary brigade",
                    tag = "PARTNERSHIP",
                    onClick = onNavigateToYankees,
                    testTag = "home_nav_yankees"
                )
                Spacer(modifier = Modifier.height(8.dp))

                HomeBrandLinkCard(
                    title = "Food For Soul & Little Kitchen Academy",
                    subtitle = "Social impact: combating food waste & culinary youth empowerment",
                    tag = "COMMUNITY",
                    onClick = onNavigateToCommunityPartners,
                    testTag = "home_nav_community_partners"
                )
                Spacer(modifier = Modifier.height(8.dp))

                HomeBrandLinkCard(
                    title = "Our Origin Story & Lifetime Promise",
                    subtitle = "Why we set out to permanently replace disposable nonstick",
                    tag = "ABOUT US",
                    onClick = onNavigateToAbout,
                    testTag = "home_nav_about"
                )
            }
        }

        // Reviews Section (4.81 star overall)
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "VERIFIED REVIEWS",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                letterSpacing = 1.5.sp
                            )
                            Text(
                                text = "${MockDataProvider.averageReviewRating} / 5.0 Star Average",
                                style = HexCladTheme.typography.titleLarge,
                                color = HexCladTheme.colors.pureWhite
                            )
                        }
                        Text(
                            text = "${MockDataProvider.totalReviewCount}+ Reviews",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    MockDataProvider.reviews.take(2).forEach { review ->
                        ReviewItemRow(review)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }

        // Full Website Footer Section (Prompt 49)
        item {
            Spacer(modifier = Modifier.height(24.dp))
            FooterSection(
                onNavigateToCatalog = onNavigateToCatalog,
                onNavigateToAbout = onNavigateToAbout,
                onNavigateToScience = onNavigateToScience,
                onNavigateToRecipes = onNavigateToRecipes,
                onNavigateToCulinaryCouncil = onNavigateToCulinaryCouncil,
                onNavigateToYankees = onNavigateToYankees,
                onNavigateToCommunityPartners = onNavigateToCommunityPartners,
                onOpenPolicy = onOpenPolicy
            )
        }
    }
}

@Composable
fun HomeBrandLinkCard(
    title: String,
    subtitle: String,
    tag: String,
    onClick: () -> Unit,
    testTag: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag(testTag),
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Surface(
                    color = HexCladTheme.colors.charcoalSurfaceVariant,
                    shape = RoundedCornerShape(3.dp)
                ) {
                    Text(
                        text = tag,
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = HexCladTheme.typography.titleSmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = HexCladTheme.colors.mutedGrey
                )
            }

            Text(
                text = "EXPLORE →",
                style = HexCladTheme.typography.labelSmall,
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ==============================================================================
// 2. CATALOG SCREEN
// ==============================================================================
@Composable
fun CatalogScreen(
    selectedCategory: String,
    searchQuery: String,
    products: List<Product>,
    onNavigateToProduct: (String) -> Unit,
    onAddToCart: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("catalog_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column {
                Text(
                    text = selectedCategory.uppercase(),
                    style = HexCladTheme.typography.headlineMedium,
                    color = HexCladTheme.colors.pureWhite
                )
                Text(
                    text = if (searchQuery.isNotBlank()) "Showing results for \"$searchQuery\""
                    else "Engineered hybrid culinary essentials with lifetime warranty.",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
        }

        if (products.isEmpty()) {
            item {
                Surface(
                    color = HexCladTheme.colors.charcoalCard,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Cookware Found",
                            style = HexCladTheme.typography.titleLarge,
                            color = HexCladTheme.colors.pureWhite
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Try another category from the top bar or clear your search query.",
                            style = HexCladTheme.typography.bodyMedium,
                            color = HexCladTheme.colors.mutedGrey,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(products) { product ->
                CatalogProductCard(
                    product = product,
                    onCardClick = { onNavigateToProduct(product.id) },
                    onAddToCart = { onAddToCart(product) }
                )
            }
        }
    }
}

// ==============================================================================
// 3. PRODUCT DETAIL SCREEN
// ==============================================================================
@Composable
fun ProductDetailScreen(
    product: Product,
    onBackClick: () -> Unit,
    onAddToCart: (Product, String, Int) -> Unit
) {
    var selectedVariant by remember {
        mutableStateOf(product.variants.firstOrNull()?.name ?: "Standard")
    }
    var quantity by remember { mutableIntStateOf(1) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("product_detail_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            // Top Nav
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Text(
                    text = "BACK TO CATALOG",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                // Category & Golden Ticket Tag
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.category.uppercase(),
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.mutedGrey
                    )
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "+${product.entryCount} GOLDEN TICKET ENTRIES",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGoldLight,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = product.title,
                    style = HexCladTheme.typography.headlineLarge,
                    color = HexCladTheme.colors.pureWhite
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Reviews Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = HexCladTheme.colors.starRatingGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${product.rating} (${product.reviewCount} Reviews)",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.lightGrey
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Price Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price.toInt()}",
                        style = HexCladTheme.typography.displaySmall,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    if (product.hasDiscount && product.originalPrice != null) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "$${product.originalPrice.toInt()}",
                            style = HexCladTheme.typography.headlineSmall,
                            color = HexCladTheme.colors.mutedGrey,
                            textDecoration = TextDecoration.LineThrough
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Surface(
                            color = HexCladTheme.colors.luxurySaleBadge,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "SAVE $${product.savingsAmount.toInt()}",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.pureWhite,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Variant Selector
                if (product.variants.isNotEmpty()) {
                    Text(
                        text = "SELECT CONFIGURATION",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    product.variants.forEach { variant ->
                        val isSel = variant.name == selectedVariant
                        Surface(
                            color = if (isSel) HexCladTheme.colors.charcoalSurfaceVariant else HexCladTheme.colors.charcoalCard,
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(
                                1.dp,
                                if (isSel) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { selectedVariant = variant.name }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = variant.name,
                                    style = HexCladTheme.typography.bodyMedium,
                                    color = if (isSel) HexCladTheme.colors.pureWhite else HexCladTheme.colors.lightGrey
                                )
                                Text(
                                    text = if (variant.inStock) "In Stock" else "Pre-Order",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = HexCladTheme.colors.announcementGreenText
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Quantity and Add to Cart
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Stepper
                    Surface(
                        color = HexCladTheme.colors.charcoalCard,
                        shape = RoundedCornerShape(4.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier.size(38.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = "Decrease",
                                    tint = HexCladTheme.colors.pureWhite
                                )
                            }
                            Text(
                                text = quantity.toString(),
                                style = HexCladTheme.typography.titleMedium,
                                color = HexCladTheme.colors.pureWhite,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            IconButton(
                                onClick = { quantity++ },
                                modifier = Modifier.size(38.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Increase",
                                    tint = HexCladTheme.colors.pureWhite
                                )
                            }
                        }
                    }

                    Button(
                        onClick = { onAddToCart(product, selectedVariant, quantity) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.pureWhite,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        ),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("detail_add_to_cart_btn")
                    ) {
                        Text(
                            text = "ADD TO CART • $${(product.price * quantity).toInt()}",
                            style = HexCladTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// ==============================================================================
// 4. CART SCREEN
// ==============================================================================
// ==============================================================================
// 4. CART SCREEN (Prompts 33, 34, 35)
// ==============================================================================
@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    subtotal: Double,
    goldenTicketEntries: Int,
    onUpdateQuantity: (String, String, Int) -> Unit,
    onRemoveItem: (String, String) -> Unit,
    onContinueShopping: () -> Unit,
    onProceedToCheckout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("cart_screen")
    ) {
        // Prompt 33: Cart Drawer Header with Free Shipping Progress Bar
        com.example.ui.components.cart.CartDrawerHeader(
            itemCount = cartItems.sumOf { it.quantity },
            subtotal = subtotal,
            onCloseClick = onContinueShopping
        )

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Your Cart is Empty",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = onContinueShopping,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HexCladTheme.colors.metallicWarmGold,
                            contentColor = HexCladTheme.colors.deepCharcoal
                        )
                    ) {
                        Text("EXPLORE COOKWARE")
                    }
                }
            }
        } else {
            // Prompt 34: Cart Item Rows
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(cartItems, key = { "${it.product.id}_${it.selectedVariant}" }) { item ->
                    com.example.ui.components.cart.CartItemRow(
                        cartItem = item,
                        onQuantityChange = { newQty ->
                            onUpdateQuantity(item.product.id, item.selectedVariant, newQty)
                        },
                        onDelete = {
                            onRemoveItem(item.product.id, item.selectedVariant)
                        }
                    )
                }
            }

            // Prompt 35: Cart Summary & Checkout Footer
            com.example.ui.components.cart.CartFooter(
                subtotal = subtotal,
                totalEntries = goldenTicketEntries,
                onCheckoutClick = onProceedToCheckout
            )
        }
    }
}

// ==============================================================================
// 5. CHECKOUT SCREEN
// ==============================================================================
@Composable
fun CheckoutScreen(
    cartItems: List<CartItem>,
    subtotal: Double,
    goldenTicketEntries: Int,
    checkoutDetails: com.example.model.CheckoutDetails,
    isOrderPlaced: Boolean,
    orderNumber: String,
    onUpdateDetails: (com.example.model.CheckoutDetails) -> Unit,
    onPlaceOrder: () -> Boolean,
    onBackToCart: () -> Unit,
    onOrderDismissed: () -> Unit,
    onViewSupport: () -> Unit = {}
) {
    if (isOrderPlaced) {
        OrderConfirmationView(
            orderNumber = orderNumber,
            goldenTicketEntries = goldenTicketEntries,
            subtotal = subtotal,
            cartItems = cartItems,
            checkoutDetails = checkoutDetails,
            onContinueShopping = onOrderDismissed,
            onViewSupport = onViewSupport
        )
    } else {
        var shippingData by remember(checkoutDetails) {
            mutableStateOf(
                ShippingFormData(
                    email = checkoutDetails.email,
                    firstName = checkoutDetails.firstName,
                    lastName = checkoutDetails.lastName,
                    address = checkoutDetails.address,
                    city = checkoutDetails.city,
                    state = if (checkoutDetails.state.isNotBlank()) checkoutDetails.state else "CA",
                    zipCode = checkoutDetails.zipCode
                )
            )
        }

        var paymentData by remember {
            mutableStateOf(PaymentFormData(selectedMethod = checkoutDetails.paymentMethod))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("checkout_form_screen"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Bar with Back navigation & Steps indicator
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackToCart,
                        modifier = Modifier.testTag("btn_back_to_cart")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Cart",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Column {
                        Text(
                            text = "SECURE LUXURY CHECKOUT",
                            style = HexCladTheme.typography.titleLarge,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Step 1 of 2: Shipping & Payment",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.metallicWarmGold
                        )
                    }
                }
            }

            // 1. Express Checkout Buttons (Prompt 36)
            item {
                ExpressCheckout(
                    onExpressPaySelected = { method ->
                        paymentData = paymentData.copy(selectedMethod = method)
                        onUpdateDetails(checkoutDetails.copy(paymentMethod = method))
                    }
                )
            }

            // 2. Shipping Address & Contact Form (Prompt 37)
            item {
                ShippingAddressForm(
                    formData = shippingData,
                    onFormDataChange = { updated ->
                        shippingData = updated
                        onUpdateDetails(
                            checkoutDetails.copy(
                                email = updated.email,
                                firstName = updated.firstName,
                                lastName = updated.lastName,
                                address = if (updated.apartment.isNotBlank()) "${updated.address}, ${updated.apartment}" else updated.address,
                                city = updated.city,
                                state = updated.state,
                                zipCode = updated.zipCode
                            )
                        )
                    }
                )
            }

            // 3. Payment Method Selector (Prompt 38)
            item {
                PaymentMethodSelector(
                    paymentData = paymentData,
                    onPaymentDataChange = { updated ->
                        paymentData = updated
                        onUpdateDetails(checkoutDetails.copy(paymentMethod = updated.selectedMethod))
                    },
                    subtotal = subtotal
                )
            }

            // 4. Order Summary Sidebar with Thumbnails, Discounts, Taxes & CTAs (Prompt 38)
            item {
                OrderSummarySidebar(
                    cartItems = cartItems,
                    subtotal = subtotal,
                    goldenTicketEntries = goldenTicketEntries,
                    onDiscountApplied = { /* handled in sidebar */ }
                )
            }

            // 5. Submit Order CTA Button
            item {
                Button(
                    onClick = { onPlaceOrder() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.metallicWarmGold,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("place_order_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SUBMIT SECURE ORDER",
                        style = HexCladTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "By placing your order, you agree to HexClad's Terms of Sale and Sweepstakes Rules.",
                    style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = HexCladTheme.colors.mutedGrey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// ==============================================================================
// 6. SUPPORT & POLICIES SCREEN
// ==============================================================================
@Composable
fun SupportScreen(
    onOpenPolicy: (PolicyType) -> Unit,
    onBackClick: () -> Unit,
    onNavigateToAbout: () -> Unit = {},
    onNavigateToScience: () -> Unit = {},
    onNavigateToRecipes: () -> Unit = {},
    onNavigateToCulinaryCouncil: () -> Unit = {},
    onNavigateToYankees: () -> Unit = {},
    onNavigateToCommunityPartners: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("support_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Text(
                    text = "CUSTOMER CARE & POLICIES",
                    style = HexCladTheme.typography.titleLarge,
                    color = HexCladTheme.colors.pureWhite
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PolicyCard(
                    title = "Lifetime Warranty & 30-Day Returns",
                    subtitle = "Details on our warranty coverage and return shipping.",
                    onClick = { onOpenPolicy(PolicyType.REFUND) }
                )
                PolicyCard(
                    title = "Shipping & Contiguous U.S. Delivery",
                    subtitle = "Delivery timelines, warehouse fulfillment, and tracking.",
                    onClick = { onOpenPolicy(PolicyType.SHIPPING) }
                )
                PolicyCard(
                    title = "Privacy Policy & CCPA Rights",
                    subtitle = "How we protect customer information and transaction data.",
                    onClick = { onOpenPolicy(PolicyType.PRIVACY) }
                )
                PolicyCard(
                    title = "Terms of Sale & Sweepstakes Rules",
                    subtitle = "Official terms and Gordon's Golden Ticket regulations.",
                    onClick = { onOpenPolicy(PolicyType.TERMS) }
                )
            }
        }

        // HexClad Brand & Partner Navigation Hub
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "BRAND DIRECTORY",
                style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.2.sp),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PolicyCard(
                    title = "The Science of HexClad",
                    subtitle = "Laser-etched matrix, tri-ply aluminum, nonstick valleys.",
                    onClick = onNavigateToScience
                )
                PolicyCard(
                    title = "Culinary Council & Master Chefs",
                    subtitle = "Gordon Ramsay, Clare Smyth, Nancy Silverton, Massimo Bottura.",
                    onClick = onNavigateToCulinaryCouncil
                )
                PolicyCard(
                    title = "Food For Soul & Little Kitchen Academy",
                    subtitle = "Combating hunger surplus and youth culinary empowerment.",
                    onClick = onNavigateToCommunityPartners
                )
                PolicyCard(
                    title = "Recipe Hub",
                    subtitle = "Chef-tested culinary creations with required cookware.",
                    onClick = onNavigateToRecipes
                )
                PolicyCard(
                    title = "New York Yankees™ Official Partnership",
                    subtitle = "Champions Suite hospitality and Yankee Stadium kitchens.",
                    onClick = onNavigateToYankees
                )
                PolicyCard(
                    title = "About Us & Origin Story",
                    subtitle = "How HexClad disrupted disposable nonstick cookware.",
                    onClick = onNavigateToAbout
                )
            }
        }
    }
}

@Composable
fun PolicyCard(
    title: String,
    subtitle: String,
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
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = HexCladTheme.typography.titleMedium,
                    color = HexCladTheme.colors.pureWhite
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = HexCladTheme.colors.metallicWarmGold
            )
        }
    }
}

// ==============================================================================
// REUSABLE CATALOG PRODUCT CARD
// ==============================================================================
@Composable
fun CatalogProductCard(
    product: Product,
    onCardClick: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .testTag("catalog_card_${product.id}"),
        colors = CardDefaults.cardColors(containerColor = HexCladTheme.colors.charcoalCard),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
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
                        text = "+${product.entryCount} GOLDEN TICKET ENTRIES",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = HexCladTheme.colors.starRatingGold,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${product.rating} (${product.reviewCount})",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.lightGrey
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = product.title,
                style = HexCladTheme.typography.titleMedium,
                color = HexCladTheme.colors.pureWhite
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$${product.price.toInt()}",
                        style = HexCladTheme.typography.titleLarge,
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
                }

                Button(
                    onClick = onAddToCart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.pureWhite,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "ADD TO CART",
                        style = HexCladTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewItemRow(review: com.example.model.Review) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = review.author,
                    style = HexCladTheme.typography.titleSmall,
                    color = HexCladTheme.colors.pureWhite
                )
                if (review.verifiedBuyer) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        color = HexCladTheme.colors.announcementGreenContainer,
                        shape = RoundedCornerShape(2.dp)
                    ) {
                        Text(
                            text = "VERIFIED BUYER",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                            color = HexCladTheme.colors.announcementGreenText,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }
            }

            Text(
                text = review.date,
                style = HexCladTheme.typography.bodySmall,
                color = HexCladTheme.colors.mutedGrey
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = review.headline,
            style = HexCladTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            color = HexCladTheme.colors.metallicWarmGoldLight
        )

        Text(
            text = review.content,
            style = HexCladTheme.typography.bodySmall,
            color = HexCladTheme.colors.lightGrey
        )
    }
}
