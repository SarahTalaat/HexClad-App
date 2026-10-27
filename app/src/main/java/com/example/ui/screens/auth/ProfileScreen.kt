package com.example.ui.screens.auth

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PastOrder
import com.example.model.UserProfile
import com.example.ui.theme.HexCladTheme

/**
 * User Profile & Order History Screen (Prompt 48)
 *
 * Displays:
 * - User details & Executive Chef Circle VIP badge
 * - Golden Ticket sweepstakes live balance
 * - Complete order history with status, items, and entries earned
 * - Warranty registrations, shipping addresses, and sign out
 */
@Composable
fun ProfileScreen(
    user: UserProfile,
    pastOrders: List<PastOrder>,
    onBackClick: () -> Unit,
    onSignOut: () -> Unit,
    onOpenGoldenTicket: () -> Unit,
    onNavigateToSupport: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("profile_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Top Nav Back
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.testTag("profile_back_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "ACCOUNT DASHBOARD",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.mutedGrey,
                        letterSpacing = 1.3.sp
                    )
                }

                IconButton(
                    onClick = onSignOut,
                    modifier = Modifier.testTag("profile_signout_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = "Sign Out",
                        tint = HexCladTheme.colors.mutedGrey
                    )
                }
            }
        }

        // Member Header Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                HexCladTheme.colors.charcoalSurfaceVariant,
                                HexCladTheme.colors.deepCharcoal
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGold,
                        shape = CircleShape,
                        modifier = Modifier.size(60.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = HexCladTheme.colors.deepCharcoal,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = user.name,
                            style = HexCladTheme.typography.titleLarge,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = user.email,
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.lightGrey
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            color = HexCladTheme.colors.metallicWarmGoldContainer,
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.WorkspacePremium,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.metallicWarmGold,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = user.membershipTier.uppercase(),
                                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                    color = HexCladTheme.colors.metallicWarmGoldLight,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Golden Ticket Balance Card
        item {
            Surface(
                color = HexCladTheme.colors.charcoalCard,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clickable { onOpenGoldenTicket() }
                    .testTag("profile_golden_ticket_card")
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CardGiftcard,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "GORDON'S GOLDEN TICKET BALANCE",
                                style = HexCladTheme.typography.labelSmall,
                                color = HexCladTheme.colors.metallicWarmGold,
                                letterSpacing = 1.1.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${user.goldenTicketBalance} ENTRIES",
                            style = HexCladTheme.typography.headlineMedium,
                            color = HexCladTheme.colors.pureWhite,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Next Drawing: $150k Trip to Italy with Gordon Ramsay",
                            style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }

                    Text(
                        text = "DETAILS →",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Order History Section Header
        item {
            Spacer(modifier = Modifier.height(14.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "ORDER HISTORY",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold,
                    letterSpacing = 1.2.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Track deliveries, download invoices, and view earned entries",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        // Past Orders
        if (pastOrders.isEmpty()) {
            item {
                Surface(
                    color = HexCladTheme.colors.charcoalCard,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Previous Orders",
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Your purchases will appear here with real-time tracking.",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.mutedGrey
                        )
                    }
                }
            }
        } else {
            items(pastOrders) { order ->
                PastOrderItemCard(order = order)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        // Account Quick Actions Section
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "MEMBERSHIP SERVICES",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold,
                    letterSpacing = 1.2.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                AccountActionRow(
                    icon = Icons.Default.Shield,
                    title = "Lifetime Warranty Registrations",
                    subtitle = "All purchases automatically protected against defects"
                )
                Spacer(modifier = Modifier.height(8.dp))

                AccountActionRow(
                    icon = Icons.Default.LocationOn,
                    title = "Saved Addresses & Payment Methods",
                    subtitle = "Manage express delivery and billing credentials"
                )
                Spacer(modifier = Modifier.height(8.dp))

                AccountActionRow(
                    icon = Icons.Default.HelpOutline,
                    title = "Concierge Support & Cookware Care",
                    subtitle = "Seasoning guides, warranty claims, and masterclass access",
                    onClick = onNavigateToSupport
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedButton(
                    onClick = onSignOut,
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = HexCladTheme.colors.lightGrey),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "SIGN OUT OF CULINARY CLUB",
                        style = HexCladTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun PastOrderItemCard(order: PastOrder) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = order.orderNumber,
                        style = HexCladTheme.typography.titleSmall,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = order.date,
                        style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                }

                Surface(
                    color = if (order.status == "Delivered") HexCladTheme.colors.announcementGreen else HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = order.status.uppercase(),
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = if (order.status == "Delivered") HexCladTheme.colors.pureWhite else HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = order.itemsSummary,
                style = HexCladTheme.typography.bodyMedium,
                color = HexCladTheme.colors.lightGrey
            )

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Total: $${order.totalAmount.toInt()}",
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    color = HexCladTheme.colors.charcoalSurfaceVariant,
                    shape = RoundedCornerShape(3.dp)
                ) {
                    Text(
                        text = "+${order.goldenTicketEntriesEarned} Entries Earned",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AccountActionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit = {}
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
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = HexCladTheme.colors.metallicWarmGold,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    style = HexCladTheme.typography.bodySmall.copy(fontSize = 11.sp),
                    color = HexCladTheme.colors.mutedGrey
                )
            }
            Text(
                text = "→",
                style = HexCladTheme.typography.bodyLarge,
                color = HexCladTheme.colors.mutedGrey
            )
        }
    }
}
