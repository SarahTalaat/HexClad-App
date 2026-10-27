package com.example.model

/**
 * Represents a luxury HexClad cookware product.
 *
 * @param id Unique product identifier (e.g. "hexclad-13pc-set")
 * @param title Full display title (e.g. "13-Piece Hybrid Cookware Set with Lids")
 * @param category Product category (e.g. "Hybrid Cookware", "Pots & Pans", "Cutlery")
 * @param price Current active purchase price (e.g. 699.00)
 * @param originalPrice Original MSRP before discount (e.g. 999.00)
 * @param rating Average customer rating out of 5.0 (e.g. 4.9)
 * @param reviewCount Total number of verified customer reviews
 * @param imageUrls High-resolution product showcase and gallery image URLs
 * @param entryCount Promotional giveaway entries included with purchase (e.g. 100 entries)
 * @param variants Available sizes, finishes, or configuration options
 * @param addOns Recommended complementary add-on accessories (e.g. seasoning wax, silicone spatulas)
 */
data class Product(
    val id: String,
    val title: String,
    val category: String,
    val price: Double,
    val originalPrice: Double? = null,
    val rating: Double = 5.0,
    val reviewCount: Int = 0,
    val imageUrls: List<String> = emptyList(),
    val entryCount: Int = 0,
    val variants: List<ProductVariant> = emptyList(),
    val addOns: List<AddOn> = emptyList(),
) {
    val hasDiscount: Boolean
        get() = originalPrice != null && originalPrice > price

    val savingsAmount: Double
        get() = if (hasDiscount && originalPrice != null) originalPrice - price else 0.0

    val discountPercentage: Int
        get() = if (hasDiscount && originalPrice != null && originalPrice > 0) {
            (((originalPrice - price) / originalPrice) * 100).toInt()
        } else {
            0
        }
}

/**
 * Represents a variant of a cookware product (e.g. 8", 10", or 12" pan size, or handle finish).
 *
 * @param id Unique variant identifier (e.g. "var-12-inch")
 * @param name Display label (e.g. "12-Inch Pan")
 * @param sku Stock Keeping Unit code
 * @param price Variant-specific price if different from parent product
 * @param originalPrice Variant-specific original price
 * @param inStock Availability status
 * @param optionValue Key value for variant selection (e.g. "12in")
 */
data class ProductVariant(
    val id: String,
    val name: String,
    val sku: String? = null,
    val price: Double? = null,
    val originalPrice: Double? = null,
    val inStock: Boolean = true,
    val optionValue: String = name,
)

/**
 * Represents an optional accessory, cookware care item, or add-on product.
 *
 * @param id Unique add-on identifier (e.g. "addon-seasoning-wax")
 * @param name Display name of the accessory
 * @param price Add-on price
 * @param originalPrice Optional pre-discount price
 * @param imageUrl Optional accessory image URL
 * @param description Short benefit or specification copy
 * @param isSelected Whether the add-on is currently checked in the cart flow
 */
data class AddOn(
    val id: String,
    val name: String,
    val price: Double,
    val originalPrice: Double? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val isSelected: Boolean = false,
)
