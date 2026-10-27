package com.example

import com.example.data.MockDataProvider
import com.example.model.CartItem
import com.example.model.CheckoutDetails
import com.example.model.PolicyType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class HexCladAppTest {

    @Test
    fun testMockProductsNotEmpty() {
        val products = MockDataProvider.getAllProducts()
        assertTrue("Products list should not be empty", products.isNotEmpty())
        val product = MockDataProvider.getProductById("hybrid-japanese-egg-pan")
        assertNotNull("Should find Japanese Egg Pan", product)
        assertEquals("Hybrid Japanese Egg Pan (Tamagoyaki)", product?.title)
        assertEquals(556, product?.entryCount)
    }

    @Test
    fun testCategoryFiltering() {
        val specialty = MockDataProvider.getProductsByCategory("Specialty Cookware")
        assertTrue(specialty.any { it.title.contains("Egg Pan", ignoreCase = true) })

        val all = MockDataProvider.getProductsByCategory("Top Picks")
        assertEquals(MockDataProvider.products.size, all.size)
    }

    @Test
    fun testPoliciesAvailable() {
        val refund = MockDataProvider.policies[PolicyType.REFUND]
        val privacy = MockDataProvider.policies[PolicyType.PRIVACY]
        val terms = MockDataProvider.policies[PolicyType.TERMS]
        val shipping = MockDataProvider.policies[PolicyType.SHIPPING]

        assertNotNull("Refund policy should exist", refund)
        assertNotNull("Privacy policy should exist", privacy)
        assertNotNull("Terms policy should exist", terms)
        assertNotNull("Shipping policy should exist", shipping)
    }

    @Test
    fun testCartItemCalculations() {
        val product = MockDataProvider.products.first().copy(price = 100.0, entryCount = 400)
        val cartItem = CartItem(
            product = product,
            selectedVariant = "Standard",
            quantity = 3
        )
        assertEquals(300.0, cartItem.totalPrice, 0.001)
        assertEquals(1200, cartItem.totalEntries)
    }

    @Test
    fun testCheckoutDetailsValidation() {
        val invalidDetails = CheckoutDetails()
        assertFalse(invalidDetails.isValid)

        val validDetails = CheckoutDetails(
            email = "gordon@hexclad.com",
            firstName = "Gordon",
            lastName = "Ramsay",
            address = "123 Michelin Way",
            city = "Los Angeles",
            state = "CA",
            zipCode = "90210",
            paymentMethod = "CREDIT_CARD"
        )
        assertTrue(validDetails.isValid)
    }

    @Test
    fun testDiscountAndTaxCalculations() {
        val subtotal = 399.0
        val discountRate = 0.10 // GOLDEN10
        val discountAmount = subtotal * discountRate
        val discountedSubtotal = subtotal - discountAmount
        val tax = discountedSubtotal * 0.0825
        val grandTotal = discountedSubtotal + tax

        assertEquals(39.9, discountAmount, 0.01)
        assertEquals(359.1, discountedSubtotal, 0.01)
        assertTrue(tax > 29.0 && tax < 30.0)
        assertTrue(grandTotal > 388.0 && grandTotal < 389.0)
    }
}
