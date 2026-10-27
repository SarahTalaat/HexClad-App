package com.example.model

/**
 * Model representing a registered HexClad customer account.
 */
data class UserProfile(
    val id: String = "usr_001",
    val name: String = "Gordon Vance",
    val email: String = "gordon.vance@example.com",
    val membershipTier: String = "Executive Chef Circle",
    val goldenTicketBalance: Int = 1840,
    val memberSince: String = "September 2023"
)

/**
 * Model representing a past order in customer history.
 */
data class PastOrder(
    val id: String,
    val orderNumber: String,
    val date: String,
    val itemsSummary: String,
    val totalAmount: Double,
    val status: String,
    val goldenTicketEntriesEarned: Int
)
