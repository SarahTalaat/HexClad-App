package com.example.model

/**
 * Categorization for luxury store terms, legal pages, and operational policies.
 */
enum class PolicyType {
    REFUND,
    PRIVACY,
    TERMS,
    SHIPPING
}

/**
 * Represents a customer review for a HexClad cookware item.
 *
 * @param id Unique review identifier
 * @param author Customer name or pseudonym (e.g. "Chef Marcus B.")
 * @param rating Review rating score from 1.0 to 5.0
 * @param date Formatted review date (e.g. "October 14, 2026")
 * @param headline Bold summary of the review (e.g. "The only pans I cook with now")
 * @param content Detailed review comments describing heat distribution, cleanup, durability
 * @param verifiedBuyer Indicates whether the reviewer verified their HexClad purchase
 */
data class Review(
    val id: String,
    val author: String,
    val rating: Double,
    val date: String,
    val headline: String,
    val content: String,
    val verifiedBuyer: Boolean = true,
)

/**
 * Represents policy documentation with rich or formatted content.
 *
 * @param type The policy category ([PolicyType.REFUND], [PolicyType.PRIVACY], etc.)
 * @param title Policy header title (e.g. "Lifetime Warranty & Refund Policy")
 * @param fullContentHtml Full policy body formatted in HTML or structured text
 */
data class PolicyText(
    val type: PolicyType,
    val title: String,
    val fullContentHtml: String,
)
