package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MockDataProvider
import com.example.model.CartItem
import com.example.model.CheckoutDetails
import com.example.model.PastOrder
import com.example.model.PolicyText
import com.example.model.PolicyType
import com.example.model.Product
import com.example.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * UI State for the HexClad application.
 */
data class HexCladUiState(
    val cartItems: List<CartItem> = listOf(
        // Seed initial cart item for immediate interactivity
        CartItem(
            product = MockDataProvider.products.first(),
            selectedVariant = "Rectangular 7\" x 6\"",
            quantity = 1
        )
    ),
    val selectedCategory: String = "Top Picks",
    val searchQuery: String = "",
    val selectedPolicy: PolicyText? = null,
    val isGoldenTicketDropdownOpen: Boolean = false,
    val isGoldenTicketModalOpen: Boolean = false,
    val isSearchOverlayOpen: Boolean = false,
    val recentSearches: List<String> = listOf("12\" Hybrid Pan", "Hybrid Wok", "Japanese Damascus Knives", "Roasting Pan"),
    val isLoggedIn: Boolean = true,
    val currentUser: UserProfile = MockDataProvider.defaultUser,
    val pastOrders: List<PastOrder> = MockDataProvider.mockOrders,
    val isNewsletterSubscribed: Boolean = false,
    val checkoutDetails: CheckoutDetails = CheckoutDetails(),
    val isOrderPlaced: Boolean = false,
    val lastOrderNumber: String = ""
) {
    val totalCartCount: Int
        get() = cartItems.sumOf { it.quantity }

    val cartSubtotal: Double
        get() = cartItems.sumOf { it.totalPrice }

    val totalGoldenTicketEntries: Int
        get() = cartItems.sumOf { it.totalEntries }

    val userTotalEntriesWithCart: Int
        get() = (currentUser.goldenTicketBalance) + totalGoldenTicketEntries

    val filteredProducts: List<Product>
        get() {
            val baseList = MockDataProvider.getProductsByCategory(selectedCategory)
            return if (searchQuery.isBlank()) {
                baseList
            } else {
                baseList.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                    it.category.contains(searchQuery, ignoreCase = true)
                }
            }
        }
}

/**
 * Central ViewModel managing cart operations, category filtering,
 * sweepstakes calculations, search, authentication, checkout flow, and legal policy modals.
 */
class HexCladViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HexCladUiState())
    val uiState: StateFlow<HexCladUiState> = _uiState.asStateFlow()

    fun addToCart(product: Product, variantName: String = "Standard", quantity: Int = 1) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val existingIndex = currentState.cartItems.indexOfFirst {
                    it.product.id == product.id && it.selectedVariant == variantName
                }

                val updatedList = if (existingIndex != -1) {
                    currentState.cartItems.toMutableList().apply {
                        val current = this[existingIndex]
                        this[existingIndex] = current.copy(quantity = current.quantity + quantity)
                    }
                } else {
                    currentState.cartItems + CartItem(
                        product = product,
                        selectedVariant = variantName,
                        quantity = quantity
                    )
                }
                currentState.copy(cartItems = updatedList)
            }
        }
    }

    fun removeFromCart(productId: String, variantName: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                val updatedList = currentState.cartItems.filterNot {
                    it.product.id == productId && it.selectedVariant == variantName
                }
                currentState.copy(cartItems = updatedList)
            }
        }
    }

    fun updateQuantity(productId: String, variantName: String, newQuantity: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                if (newQuantity <= 0) {
                    val updatedList = currentState.cartItems.filterNot {
                        it.product.id == productId && it.selectedVariant == variantName
                    }
                    currentState.copy(cartItems = updatedList)
                } else {
                    val updatedList = currentState.cartItems.map { item ->
                        if (item.product.id == productId && item.selectedVariant == variantName) {
                            item.copy(quantity = newQuantity)
                        } else {
                            item
                        }
                    }
                    currentState.copy(cartItems = updatedList)
                }
            }
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(cartItems = emptyList()) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun openSearchOverlay() {
        _uiState.update { it.copy(isSearchOverlayOpen = true) }
    }

    fun closeSearchOverlay() {
        _uiState.update { it.copy(isSearchOverlayOpen = false) }
    }

    fun addRecentSearch(query: String) {
        if (query.isBlank()) return
        _uiState.update { state ->
            val updated = (listOf(query.trim()) + state.recentSearches.filterNot { it.equals(query.trim(), ignoreCase = true) }).take(6)
            state.copy(recentSearches = updated, searchQuery = query.trim())
        }
    }

    fun clearRecentSearches() {
        _uiState.update { it.copy(recentSearches = emptyList()) }
    }

    fun toggleGoldenTicketDropdown(open: Boolean? = null) {
        _uiState.update {
            it.copy(isGoldenTicketDropdownOpen = open ?: !it.isGoldenTicketDropdownOpen)
        }
    }

    fun openGoldenTicketModal() {
        _uiState.update { it.copy(isGoldenTicketModalOpen = true, isGoldenTicketDropdownOpen = false) }
    }

    fun closeGoldenTicketModal() {
        _uiState.update { it.copy(isGoldenTicketModalOpen = false) }
    }

    fun selectPolicy(type: PolicyType) {
        val policy = MockDataProvider.policies[type]
        _uiState.update { it.copy(selectedPolicy = policy) }
    }

    fun dismissPolicyModal() {
        _uiState.update { it.copy(selectedPolicy = null) }
    }

    fun updateCheckoutDetails(details: CheckoutDetails) {
        _uiState.update { it.copy(checkoutDetails = details) }
    }

    fun completeOrder(): Boolean {
        val state = _uiState.value
        if (state.cartItems.isEmpty() || !state.checkoutDetails.isValid) {
            return false
        }
        val orderNum = "HEX-${(100000..999999).random()}"
        val earnedEntries = state.totalGoldenTicketEntries
        val newOrder = PastOrder(
            id = "ord_${System.currentTimeMillis()}",
            orderNumber = orderNum,
            date = "Today",
            itemsSummary = state.cartItems.joinToString(", ") { "${it.product.title} (${it.selectedVariant})" },
            totalAmount = state.cartSubtotal * 1.0825,
            status = "Processing",
            goldenTicketEntriesEarned = earnedEntries
        )

        _uiState.update {
            it.copy(
                isOrderPlaced = true,
                lastOrderNumber = orderNum,
                cartItems = emptyList(),
                currentUser = it.currentUser.copy(
                    goldenTicketBalance = it.currentUser.goldenTicketBalance + earnedEntries
                ),
                pastOrders = listOf(newOrder) + it.pastOrders
            )
        }
        return true
    }

    fun resetOrderState() {
        _uiState.update { it.copy(isOrderPlaced = false, lastOrderNumber = "") }
    }

    // User Authentication Methods (Prompt 48)
    fun signIn(email: String, name: String = "Gordon Vance") {
        _uiState.update {
            it.copy(
                isLoggedIn = true,
                currentUser = it.currentUser.copy(email = email, name = name)
            )
        }
    }

    fun signOut() {
        _uiState.update {
            it.copy(isLoggedIn = false)
        }
    }

    fun subscribeNewsletter(email: String) {
        _uiState.update { it.copy(isNewsletterSubscribed = true) }
    }
}
