package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.rememberNavController
import com.example.navigation.HexCladNavGraph
import com.example.navigation.Routes
import com.example.ui.components.AnnouncementBar
import com.example.ui.components.CategorySubBar
import com.example.ui.components.GoldenTicketModal
import com.example.ui.components.HeaderBar
import com.example.ui.components.SearchOverlay
import com.example.ui.components.TermsAndPoliciesModal
import com.example.ui.theme.HexCladTheme
import com.example.viewmodel.HexCladViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: HexCladViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      HexCladTheme(darkTheme = true) {
        MainRootScaffold(viewModel = viewModel)
      }
    }
  }
}

/**
 * Root Main App Scaffold containing:
 * - Sticky AnnouncementBar (emerald green live countdown timer)
 * - Sticky HeaderBar (centered HEXCLAD logo, Golden Ticket trigger, search, account, cart badge)
 * - Sticky CategorySubBar (horizontal scrolling across luxury categories)
 * - Central NavHost content area
 * - Global Search Overlay (Prompt 46)
 * - Golden Ticket Sweepstakes Modal (Prompt 47)
 * - Modal dialogs for Policy texts
 */
@Composable
fun MainRootScaffold(viewModel: HexCladViewModel) {
  val context = LocalContext.current
  val navController = rememberNavController()
  val uiState by viewModel.uiState.collectAsState()

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("hexclad_root_scaffold"),
    containerColor = HexCladTheme.colors.deepCharcoal,
    topBar = {
      Column(modifier = Modifier.fillMaxWidth()) {
        // 1. Sticky Promotional Bar with Live Countdown (Prompt 10)
        AnnouncementBar(
          onShopNowClick = {
            navController.navigate(Routes.CATALOG)
          }
        )

        // 2. Sticky Header Bar (Prompt 11)
        HeaderBar(
          cartCount = uiState.totalCartCount,
          goldenTicketEntries = uiState.totalGoldenTicketEntries,
          isGoldenTicketOpen = uiState.isGoldenTicketDropdownOpen,
          onToggleGoldenTicket = { viewModel.openGoldenTicketModal() },
          onSearchClick = { viewModel.openSearchOverlay() },
          onAccountClick = {
            if (uiState.isLoggedIn) {
              navController.navigate(Routes.PROFILE)
            } else {
              navController.navigate(Routes.AUTH)
            }
          },
          onCartClick = {
            navController.navigate(Routes.CART)
          },
          onLogoClick = {
            navController.navigate(Routes.HOME) {
              popUpTo(Routes.HOME) { inclusive = true }
            }
          }
        )

        // 3. Sticky Horizontal Category Sub-Bar (Prompt 12)
        CategorySubBar(
          selectedCategory = uiState.selectedCategory,
          onCategorySelected = { category ->
            viewModel.selectCategory(category)
            navController.navigate(Routes.CATALOG)
          }
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(HexCladTheme.colors.deepCharcoal)
    ) {
      // 4. Navigation Host
      HexCladNavGraph(
        navController = navController,
        viewModel = viewModel
      )
    }

    // Modal Policy Viewer (Prompt 39)
    uiState.selectedPolicy?.let { policy ->
      TermsAndPoliciesModal(
        initialPolicyType = policy.type,
        onDismissRequest = { viewModel.dismissPolicyModal() }
      )
    }

    // Global Search Overlay (Prompt 46)
    SearchOverlay(
      isOpen = uiState.isSearchOverlayOpen,
      recentSearches = uiState.recentSearches,
      onDismissRequest = { viewModel.closeSearchOverlay() },
      onNavigateToProduct = { id ->
        navController.navigate(Routes.productDetail(id))
      },
      onAddRecentSearch = { query ->
        viewModel.addRecentSearch(query)
      },
      onClearRecentSearches = {
        viewModel.clearRecentSearches()
      }
    )

    // Golden Ticket Sweepstakes Modal (Prompt 47)
    GoldenTicketModal(
      isOpen = uiState.isGoldenTicketModalOpen,
      currentEntries = uiState.userTotalEntriesWithCart,
      onDismissRequest = { viewModel.closeGoldenTicketModal() },
      onShopCookware = {
        navController.navigate(Routes.CATALOG)
      }
    )
  }
}
