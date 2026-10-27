package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.HexCladTheme

/**
 * Top Navigation Header Bar displaying:
 * - Centered HEXCLAD logo mark
 * - Gordon's Golden Ticket dropdown trigger
 * - Global search icon
 * - User account icon
 * - Shopping cart badge button with dynamic count
 */
@Composable
fun HeaderBar(
    cartCount: Int,
    goldenTicketEntries: Int,
    isGoldenTicketOpen: Boolean,
    onToggleGoldenTicket: () -> Unit,
    onSearchClick: () -> Unit,
    onAccountClick: () -> Unit,
    onCartClick: () -> Unit,
    onLogoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        tonalElevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: Golden Ticket Dropdown Trigger
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier
                        .clickable { onToggleGoldenTicket() }
                        .testTag("golden_ticket_trigger")
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CardGiftcard,
                            contentDescription = "Golden Ticket",
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "GOLDEN TICKET",
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Center: Centered HEXCLAD Logo Mark
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onLogoClick() }
                        .testTag("logo_mark")
                ) {
                    Text(
                        text = "HEXCLAD",
                        style = HexCladTheme.typography.headlineMedium.copy(
                            letterSpacing = 4.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        color = HexCladTheme.colors.pureWhite
                    )
                    Text(
                        text = "HYBRID COOKWARE",
                        style = HexCladTheme.typography.labelSmall.copy(
                            fontSize = 8.sp,
                            letterSpacing = 2.sp
                        ),
                        color = HexCladTheme.colors.metallicWarmGold
                    )
                }

                // Right: Search, Account, and Cart Action Icons
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onSearchClick,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("search_icon_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Catalog",
                            tint = HexCladTheme.colors.pureWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = onAccountClick,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("account_icon_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "User Account",
                            tint = HexCladTheme.colors.pureWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    IconButton(
                        onClick = onCartClick,
                        modifier = Modifier
                            .size(38.dp)
                            .testTag("cart_icon_button")
                    ) {
                        BadgedBox(
                            badge = {
                                if (cartCount > 0) {
                                    Badge(
                                        containerColor = HexCladTheme.colors.metallicWarmGold,
                                        contentColor = HexCladTheme.colors.deepCharcoal
                                    ) {
                                        Text(
                                            text = cartCount.toString(),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingBag,
                                contentDescription = "Shopping Cart",
                                tint = HexCladTheme.colors.pureWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
        }
    }

    // Golden Ticket Dropdown Info Dialog
    if (isGoldenTicketOpen) {
        Dialog(onDismissRequest = onToggleGoldenTicket) {
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    HexCladTheme.colors.metallicWarmGold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .testTag("golden_ticket_dialog")
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = "EXCLUSIVE SWEEPSTAKES",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }

                    Text(
                        text = "Gordon's Golden Ticket to Italy",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Win an all-expenses-paid culinary trip to Italy to cook alongside Gordon Ramsay. Every dollar spent on qualifying cookware earns sweepstakes entries.",
                        style = HexCladTheme.typography.bodyMedium,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Surface(
                        color = HexCladTheme.colors.deepCharcoal,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Your Current Entries:",
                                style = HexCladTheme.typography.bodyMedium,
                                color = HexCladTheme.colors.pureWhite
                            )
                            Text(
                                text = "$goldenTicketEntries ENTRIES",
                                style = HexCladTheme.typography.labelLarge,
                                color = HexCladTheme.colors.metallicWarmGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Surface(
                        color = HexCladTheme.colors.pureWhite,
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleGoldenTicket() }
                    ) {
                        Text(
                            text = "CONTINUE SHOPPING",
                            style = HexCladTheme.typography.labelMedium,
                            color = HexCladTheme.colors.deepCharcoal,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
