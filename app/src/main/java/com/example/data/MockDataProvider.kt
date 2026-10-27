package com.example.data

import com.example.model.AddOn
import com.example.model.PolicyText
import com.example.model.PolicyType
import com.example.model.Product
import com.example.model.ProductVariant
import com.example.model.Review

/**
 * Provides comprehensive, realistic mock data for HexClad cookware products, collections,
 * customer reviews, and policies matching https://hexclad.com/.
 */
object MockDataProvider {

    // ==============================================================================
    // Cookware Accessories & Add-Ons
    // ==============================================================================
    val defaultAddOns: List<AddOn> = listOf(
        AddOn(
            id = "addon_seasoning_wax",
            name = "HexClad Cookware Care & Seasoning Wax",
            price = 24.00,
            originalPrice = 30.00,
            imageUrl = "https://hexclad.com/cdn/shop/files/CareWax_1.jpg",
            description = "Natural beeswax and mineral oil blend for perpetual seasoning and mirror gloss."
        ),
        AddOn(
            id = "addon_silicone_spatula",
            name = "Pro-Grade Silicone Edge Stainless Turner",
            price = 29.00,
            originalPrice = 39.00,
            imageUrl = "https://hexclad.com/cdn/shop/files/Spatula_1.jpg",
            description = "Flexible high-heat silicone edge rated to 550°F with sturdy steel core."
        ),
        AddOn(
            id = "addon_lid_cleaner",
            name = "Tempered Glass Lid Cleaner & Stainless Polish",
            price = 19.00,
            originalPrice = 25.00,
            imageUrl = "https://hexclad.com/cdn/shop/files/Polish_1.jpg",
            description = "Specialized streak-free formula for laser-etched stainless steel."
        )
    )

    // ==============================================================================
    // Realistic Products matching HexClad Catalog
    // ==============================================================================
    val products: List<Product> = listOf(
        // 1. Hybrid Japanese Egg Pan (Tamagoyaki) ($139, 556 entries)
        Product(
            id = "hybrid-japanese-egg-pan",
            title = "Hybrid Japanese Egg Pan (Tamagoyaki)",
            category = "Specialty Cookware",
            price = 139.00,
            originalPrice = 179.00,
            rating = 4.9,
            reviewCount = 1840,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/JapaneseEggPan_Hero.jpg",
                "https://hexclad.com/cdn/shop/files/JapaneseEggPan_InUse.jpg",
                "https://hexclad.com/cdn/shop/files/JapaneseEggPan_Detail.jpg"
            ),
            entryCount = 556,
            variants = listOf(
                ProductVariant(
                    id = "egg-pan-standard",
                    name = "Rectangular 7\" x 6\"",
                    sku = "HEX-EGG-07",
                    price = 139.00,
                    originalPrice = 179.00,
                    inStock = true,
                    optionValue = "7x6"
                )
            ),
            addOns = defaultAddOns
        ),

        // 2. Hybrid Fry Pan Set with Lids 6pc ($399, 299 entries)
        Product(
            id = "hybrid-fry-pan-set-6pc",
            title = "Hybrid Fry Pan Set with Lids (6-Piece)",
            category = "Hybrid Cookware",
            price = 399.00,
            originalPrice = 599.00,
            rating = 4.9,
            reviewCount = 28940,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/6pc_FryPanSet_Hero.jpg",
                "https://hexclad.com/cdn/shop/files/6pc_FryPanSet_Grid.jpg",
                "https://hexclad.com/cdn/shop/files/6pc_FryPanSet_Gordon.jpg"
            ),
            entryCount = 299,
            variants = listOf(
                ProductVariant(
                    id = "fry-set-standard",
                    name = "8\", 10\", 12\" Pans + Matching Lids",
                    sku = "HEX-FPS-06",
                    price = 399.00,
                    originalPrice = 599.00,
                    inStock = true,
                    optionValue = "6pc-standard"
                )
            ),
            addOns = defaultAddOns
        ),

        // 3. Hybrid Rice & Multi-Cook Pot
        Product(
            id = "hybrid-rice-pot",
            title = "Hybrid Rice & Multi-Cook Pot (3.5 Qt)",
            category = "Specialty Cookware",
            price = 189.00,
            originalPrice = 249.00,
            rating = 4.8,
            reviewCount = 3420,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/RicePot_Hero.jpg",
                "https://hexclad.com/cdn/shop/files/RicePot_Interior.jpg"
            ),
            entryCount = 750,
            variants = listOf(
                ProductVariant(
                    id = "rice-pot-35qt",
                    name = "3.5 Quart with Precision Steam Lid",
                    sku = "HEX-RPC-35",
                    price = 189.00,
                    originalPrice = 249.00,
                    inStock = true,
                    optionValue = "3.5qt"
                ),
                ProductVariant(
                    id = "rice-pot-50qt",
                    name = "5.0 Quart Family Capacity",
                    sku = "HEX-RPC-50",
                    price = 229.00,
                    originalPrice = 289.00,
                    inStock = true,
                    optionValue = "5.0qt"
                )
            ),
            addOns = defaultAddOns
        ),

        // 4. Hybrid Double Burner Griddle
        Product(
            id = "hybrid-double-burner-griddle",
            title = "Hybrid Double Burner Griddle & Reversible Grill",
            category = "Specialty Cookware",
            price = 219.00,
            originalPrice = 279.00,
            rating = 4.9,
            reviewCount = 5810,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/DoubleGriddle_Hero.jpg",
                "https://hexclad.com/cdn/shop/files/DoubleGriddle_Action.jpg"
            ),
            entryCount = 880,
            variants = listOf(
                ProductVariant(
                    id = "griddle-double-burn",
                    name = "20\" x 11\" Heavyweight Dual Burner",
                    sku = "HEX-DBG-20",
                    price = 219.00,
                    originalPrice = 279.00,
                    inStock = true,
                    optionValue = "20x11"
                )
            ),
            addOns = defaultAddOns
        ),

        // 5. 13-Piece Complete Hybrid Cookware Set with Lids
        Product(
            id = "hybrid-cookware-set-13pc",
            title = "13-Piece Complete Hybrid Cookware Set with Lids",
            category = "Top Picks",
            price = 699.00,
            originalPrice = 999.00,
            rating = 4.9,
            reviewCount = 31200,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/13pc_Set_Hero.jpg",
                "https://hexclad.com/cdn/shop/files/13pc_Set_Cooktop.jpg"
            ),
            entryCount = 1400,
            variants = listOf(
                ProductVariant(
                    id = "set-13pc-stainless",
                    name = "13-Piece Set with Stainless Steel Handles",
                    sku = "HEX-SET-13",
                    price = 699.00,
                    originalPrice = 999.00,
                    inStock = true,
                    optionValue = "13pc"
                )
            ),
            addOns = defaultAddOns
        ),

        // 6. Japanese Damascus Steel Chef's Knife (8-Inch)
        Product(
            id = "damascus-chef-knife-8in",
            title = "Japanese Damascus Steel 8\" Chef's Knife",
            category = "Knives/Cutting Boards",
            price = 149.00,
            originalPrice = 189.00,
            rating = 4.9,
            reviewCount = 8120,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/Damascus_Knife_Hero.jpg"
            ),
            entryCount = 450,
            variants = listOf(
                ProductVariant(
                    id = "knife-8in-pakkawood",
                    name = "8\" Damascus Blade with Green Pakkawood Handle",
                    sku = "HEX-KNF-08",
                    price = 149.00,
                    originalPrice = 189.00,
                    inStock = true,
                    optionValue = "8in"
                )
            ),
            addOns = defaultAddOns
        ),

        // 7. HexClad Hybrid Roasting Pan with Rack
        Product(
            id = "hybrid-roasting-pan",
            title = "Hybrid Roasting Pan with Removable Nonstick Rack",
            category = "Bakeware",
            price = 179.00,
            originalPrice = 229.00,
            rating = 4.8,
            reviewCount = 2940,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/Roaster_Hero.jpg"
            ),
            entryCount = 620,
            variants = listOf(
                ProductVariant(
                    id = "roaster-standard",
                    name = "16\" x 13\" Heavy Duty Pan",
                    sku = "HEX-RST-16",
                    price = 179.00,
                    originalPrice = 229.00,
                    inStock = true,
                    optionValue = "16x13"
                )
            ),
            addOns = defaultAddOns
        ),

        // 8. HexMill Salt & Pepper Grinder Duo
        Product(
            id = "hexmill-grinder-duo",
            title = "HexMill Salt & Pepper Engineered Grinder Duo",
            category = "HexMills",
            price = 199.00,
            originalPrice = 250.00,
            rating = 4.9,
            reviewCount = 6120,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/HexMills_Hero.jpg"
            ),
            entryCount = 500,
            variants = listOf(
                ProductVariant(
                    id = "hexmills-matte-black",
                    name = "Heavyweight Aluminum in Matte Gunmetal",
                    sku = "HEX-MIL-02",
                    price = 199.00,
                    originalPrice = 250.00,
                    inStock = true,
                    optionValue = "duo"
                )
            ),
            addOns = defaultAddOns
        ),

        // 9. HexClad Hybrid Pizza Steel
        Product(
            id = "hybrid-pizza-steel",
            title = "HexClad Heavyweight Hybrid Pizza Steel (14-Inch)",
            category = "PIZZA STEEL",
            price = 149.00,
            originalPrice = 189.00,
            rating = 4.9,
            reviewCount = 2150,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/PizzaSteel_Hero.jpg"
            ),
            entryCount = 450,
            variants = listOf(
                ProductVariant(
                    id = "pizza-steel-14in",
                    name = "14\" Heavyweight Conductive Steel",
                    sku = "HEX-PZ-14",
                    price = 149.00,
                    originalPrice = 189.00,
                    inStock = true,
                    optionValue = "14in"
                )
            ),
            addOns = defaultAddOns
        ),

        // 10. HexClad Hybrid BBQ Grill Pan
        Product(
            id = "hybrid-bbq-grill-pan",
            title = "HexClad Hybrid Outdoor BBQ & Smoker Pan",
            category = "BBQ",
            price = 169.00,
            originalPrice = 219.00,
            rating = 4.8,
            reviewCount = 3780,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/BBQPan_Hero.jpg"
            ),
            entryCount = 520,
            variants = listOf(
                ProductVariant(
                    id = "bbq-pan-standard",
                    name = "Perforated High-Heat BBQ Grill Pan",
                    sku = "HEX-BBQ-12",
                    price = 169.00,
                    originalPrice = 219.00,
                    inStock = true,
                    optionValue = "standard"
                )
            ),
            addOns = defaultAddOns
        ),

        // 11. 12" Hybrid Stainless Steel Wok
        Product(
            id = "hybrid-wok-12in",
            title = "12\" Hybrid Stainless Steel Wok with Lid",
            category = "Specialty Cookware",
            price = 179.00,
            originalPrice = 229.00,
            rating = 4.9,
            reviewCount = 11420,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/Wok_Hero.jpg"
            ),
            entryCount = 530,
            variants = listOf(
                ProductVariant(
                    id = "wok-12in-standard",
                    name = "12-Inch Round Bottom Hybrid Wok",
                    sku = "HEX-WOK-12",
                    price = 179.00,
                    originalPrice = 229.00,
                    inStock = true,
                    optionValue = "12in"
                )
            ),
            addOns = defaultAddOns
        ),

        // 12. 5-Quart Hybrid Dutch Oven
        Product(
            id = "hybrid-dutch-oven-5qt",
            title = "5-Quart Hybrid Dutch Oven with Deep Dome Lid",
            category = "Pots & Dutch Ovens",
            price = 229.00,
            originalPrice = 289.00,
            rating = 4.9,
            reviewCount = 8920,
            imageUrls = listOf(
                "https://hexclad.com/cdn/shop/files/DutchOven_Hero.jpg"
            ),
            entryCount = 680,
            variants = listOf(
                ProductVariant(
                    id = "dutch-oven-5qt",
                    name = "5-Quart Commercial Cast Stainless",
                    sku = "HEX-DCH-05",
                    price = 229.00,
                    originalPrice = 289.00,
                    inStock = true,
                    optionValue = "5qt"
                )
            ),
            addOns = defaultAddOns
        )
    )

    // ==============================================================================
    // Realistic User Reviews (4.81 Star Overall Average, Verified Buyer Tags)
    // ==============================================================================
    val reviews: List<Review> = listOf(
        Review(
            id = "rev-001",
            author = "Chef Gordon Ramsay",
            rating = 5.0,
            date = "October 18, 2026",
            headline = "The Rolls Royce of nonstick cookware.",
            content = "I was skeptical of hybrid cookware until I tested HexClad under real restaurant rush conditions. The sear on scallops and dry-aged ribeye is phenomenal, yet eggs glide right off. Absolutely revolutionary.",
            verifiedBuyer = true
        ),
        Review(
            id = "rev-002",
            author = "Marcus V., Austin TX",
            rating = 5.0,
            date = "October 12, 2026",
            headline = "Truly indestructible pans. Worth every single penny.",
            content = "I’ve thrown away dozen of Teflon pans over the past decade. These look as pristine today as when unboxed. Clean up takes literally 10 seconds with warm soapy water and a sponge.",
            verifiedBuyer = true
        ),
        Review(
            id = "rev-003",
            author = "Elena S., Seattle WA",
            rating = 4.8,
            date = "October 05, 2026",
            headline = "Incredible heat distribution on induction.",
            content = "Heats completely evenly without any hot spots. The Japanese egg pan makes the fluffiest tamagoyaki I’ve ever achieved at home. Love that you can use metal tongs without stressing.",
            verifiedBuyer = true
        ),
        Review(
            id = "rev-004",
            author = "Dr. David L., Chicago IL",
            rating = 5.0,
            date = "September 28, 2026",
            headline = "PFOA-free piece of mind and commercial durability.",
            content = "The laser-etched stainless hexagonal grid protects the non-stick valleys underneath. You get the crispy golden browning of cast iron with the effortless release of ceramic.",
            verifiedBuyer = true
        ),
        Review(
            id = "rev-005",
            author = "Claire R., San Francisco CA",
            rating = 4.7,
            date = "September 19, 2026",
            headline = "Beautiful modern look on the stovetop.",
            content = "The dark charcoal and laser etched finish looks stunning hanging from our rack. Also the Golden Ticket sweepstakes entries are a super fun bonus!",
            verifiedBuyer = true
        )
    )

    val averageReviewRating: Double = 4.81
    val totalReviewCount: Int = 89450

    // ==============================================================================
    // Complete Store Policies (Refund Policy, Privacy Policy, Terms of Sale)
    // ==============================================================================
    val policies: Map<PolicyType, PolicyText> = mapOf(
        PolicyType.REFUND to PolicyText(
            type = PolicyType.REFUND,
            title = "Refund Policy & Lifetime Warranty",
            fullContentHtml = """
                <h3>HexClad Lifetime Warranty Guarantee</h3>
                <p>HexClad Cookware is engineered to withstand a lifetime of culinary use. All cookware vessels, handles, and rivets are covered against manufacturer defects in materials and craftsmanship for the lifetime of the original purchaser.</p>
                
                <h3>30-Day Hassle-Free Trial & Returns</h3>
                <p>We invite you to cook with your new HexClad in your own kitchen for 30 days. If you are not completely delighted with your culinary results, return it within 30 days of delivery for a full refund of your purchase price.</p>
                <ul>
                    <li>Free return shipping labels provided for all contiguous U.S. orders.</li>
                    <li>Items must be returned with all original lids and components.</li>
                    <li>Refunds are credited to the original payment method within 3-5 business days upon warehouse inspection.</li>
                </ul>
            """.trimIndent()
        ),
        PolicyType.PRIVACY to PolicyText(
            type = PolicyType.PRIVACY,
            title = "Privacy Policy & Data Security",
            fullContentHtml = """
                <h3>Your Privacy at HexClad</h3>
                <p>HexClad respects your privacy. We collect only information required to fulfill your orders, provide warranty support, and notify you regarding promotional sweepstakes such as Gordon's Golden Ticket giveaway.</p>
                
                <h3>Information We Protect</h3>
                <p>All checkout transactions and customer records are protected with 256-bit TLS encryption. We do not sell your personal data to third parties. Under the California Consumer Privacy Act (CCPA), you retain full rights to request access to or deletion of your stored records.</p>
            """.trimIndent()
        ),
        PolicyType.TERMS to PolicyText(
            type = PolicyType.TERMS,
            title = "Terms of Sale & Sweepstakes Rules",
            fullContentHtml = """
                <h3>Terms of Sale</h3>
                <p>By placing an order on HexClad, you agree to these Terms of Sale. All promotions, discounts, and coupon codes must be applied prior to order submission. Product availability is subject to change.</p>
                
                <h3>Gordon's Golden Ticket to Italy Official Rules</h3>
                <p>NO PURCHASE NECESSARY. Each eligible dollar spent on qualifying cookware sets earns entry points as listed during promotional hours. Open to legal residents of the 50 United States and D.C., 18 years or older. Void where prohibited by law.</p>
            """.trimIndent()
        ),
        PolicyType.SHIPPING to PolicyText(
            type = PolicyType.SHIPPING,
            title = "Shipping & Delivery Policy",
            fullContentHtml = """
                <h3>Fast, Free Shipping</h3>
                <p>HexClad proudly offers free standard ground shipping on all orders over $99 within the contiguous United States. Orders are dispatched from our fulfillment centers within 1-2 business days.</p>
                <ul>
                    <li>Standard Delivery: 3 to 5 business days.</li>
                    <li>Express 2-Day Air: Available at checkout.</li>
                    <li>Real-time tracking notifications sent via email and SMS.</li>
                </ul>
            """.trimIndent()
        )
    )

    // ==============================================================================
    // Navigation Categories (Sticky Horizontal Sub-Bar categories from Prompt 12)
    // ==============================================================================
    val categories: List<String> = listOf(
        "Top Picks",
        "Bakeware",
        "Hybrid Cookware",
        "Specialty Cookware",
        "Knives/Cutting Boards",
        "HexMills",
        "Kitchen Tools",
        "HexWear",
        "Cocktail Shaker"
    )

    fun getAllProducts(): List<Product> = products

    fun getProductById(id: String): Product? = products.find { it.id == id }

    fun getProductsByCategory(category: String): List<Product> {
        val cat = category.trim().uppercase()
        return when {
            cat == "TOP PICKS" || cat == "ALL" -> products
            cat == "SETS" -> products.filter { it.title.contains("Set", ignoreCase = true) }
            cat == "BBQ" -> products.filter { it.title.contains("BBQ", ignoreCase = true) || it.category.contains("BBQ", ignoreCase = true) }
            cat == "PIZZA STEEL" -> products.filter { it.title.contains("Pizza", ignoreCase = true) || it.category.contains("Pizza", ignoreCase = true) }
            cat == "ROASTING PANS" -> products.filter { it.title.contains("Roasting", ignoreCase = true) || it.title.contains("Roaster", ignoreCase = true) }
            cat == "DOUBLE BURNER GRIDDLE" -> products.filter { it.title.contains("Griddle", ignoreCase = true) }
            cat == "JAPANESE-STYLE COOKWARE" -> products.filter { 
                it.title.contains("Japanese", ignoreCase = true) || 
                it.title.contains("Egg Pan", ignoreCase = true) ||
                it.title.contains("Damascus", ignoreCase = true)
            }
            else -> {
                val matches = products.filter { it.category.equals(category, ignoreCase = true) }
                if (matches.isNotEmpty()) matches else products.filter { it.title.contains(category, ignoreCase = true) }
            }
        }
    }
    // ==============================================================================
    // User Profile & Order History Mock Data (Prompt 48)
    // ==============================================================================
    val defaultUser = com.example.model.UserProfile(
        id = "usr_hex_789",
        name = "Gordon Vance",
        email = "gordon.vance@example.com",
        membershipTier = "Executive Chef Circle",
        goldenTicketBalance = 2480,
        memberSince = "November 2023"
    )

    val mockOrders: List<com.example.model.PastOrder> = listOf(
        com.example.model.PastOrder(
            id = "ord_101",
            orderNumber = "HEX-849201",
            date = "Oct 12, 2024",
            itemsSummary = "12\" Hybrid Stainless Steel Pan + Cookware Care Wax",
            totalAmount = 203.00,
            status = "Delivered",
            goldenTicketEntriesEarned = 716
        ),
        com.example.model.PastOrder(
            id = "ord_102",
            orderNumber = "HEX-739184",
            date = "Aug 29, 2024",
            itemsSummary = "6-Piece Hybrid Cookware Set with Lids",
            totalAmount = 499.00,
            status = "Delivered",
            goldenTicketEntriesEarned = 1996
        ),
        com.example.model.PastOrder(
            id = "ord_103",
            orderNumber = "HEX-920481",
            date = "Nov 02, 2024",
            itemsSummary = "Hybrid Japanese Egg Pan (Tamagoyaki)",
            totalAmount = 139.00,
            status = "In Transit",
            goldenTicketEntriesEarned = 556
        )
    )
}
