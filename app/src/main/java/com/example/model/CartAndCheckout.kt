package com.example.model

/**
 * Represents an individual item within the luxury cookware shopping cart.
 *
 * @param product The referenced [Product]
 * @param selectedVariant The chosen configuration/size (e.g. "12-Inch", "Standard")
 * @param quantity The number of units selected
 */
data class CartItem(
    val product: Product,
    val selectedVariant: String = "Standard",
    val quantity: Int = 1,
) {
    val totalPrice: Double
        get() = product.price * quantity

    val totalEntries: Int
        get() = product.entryCount * quantity
}

/**
 * User contact, shipping, and payment information gathered during simulated checkout.
 */
data class CheckoutDetails(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val paymentMethod: String = "Credit Card",
) {
    val isValid: Boolean
        get() = email.isNotBlank() &&
                firstName.isNotBlank() &&
                lastName.isNotBlank() &&
                address.isNotBlank() &&
                city.isNotBlank() &&
                state.isNotBlank() &&
                zipCode.isNotBlank()
}

/**
 * Promotional sweepstakes and giveaway details attached to high-end cookware purchases.
 *
 * @param totalEntries Cumulative entries earned towards the current HexClad kitchen giveaway
 */
data class SweepstakesInfo(
    val totalEntries: Int = 0,
)
