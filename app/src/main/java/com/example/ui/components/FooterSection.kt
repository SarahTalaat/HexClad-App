package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PolicyType
import com.example.ui.theme.HexCladTheme

/**
 * Full Website Footer Section (Prompt 49)
 *
 * Contains:
 * - Newsletter subscription box with validation & success confirmation state
 * - Social media links (Instagram, Facebook, YouTube, TikTok)
 * - 3-column link structure (Shop, About, Support)
 * - Payment icons (Visa, Mastercard, AMEX, Apple Pay, PayPal, Shop Pay)
 * - Copyright notice and policy shortcuts
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
    onNavigateToCatalog: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToScience: () -> Unit,
    onNavigateToRecipes: () -> Unit,
    onNavigateToCulinaryCouncil: () -> Unit,
    onNavigateToYankees: () -> Unit,
    onNavigateToCommunityPartners: () -> Unit,
    onOpenPolicy: (PolicyType) -> Unit,
    modifier: Modifier = Modifier
) {
    var newsletterEmail by remember { mutableStateOf("") }
    var isSubscribed by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("footer_section"),
        color = HexCladTheme.colors.charcoalCard,
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 28.dp)
        ) {
            // 1. Newsletter Signup Box
            Surface(
                color = HexCladTheme.colors.deepCharcoal,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGold.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "JOIN THE HEXCLAD REVOLUTION",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        letterSpacing = 1.3.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Unlock 10% Off Your First Order",
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Plus receive Gordon Ramsay's weekly skillet recipes, VIP giveaway alerts, and early access to drops.",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    if (isSubscribed) {
                        Surface(
                            color = HexCladTheme.colors.announcementGreen,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.pureWhite,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Thank you! Code WELCOME10 sent to your email.",
                                    style = HexCladTheme.typography.labelMedium,
                                    color = HexCladTheme.colors.pureWhite,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        OutlinedTextField(
                            value = newsletterEmail,
                            onValueChange = {
                                newsletterEmail = it
                                emailError = false
                            },
                            placeholder = { Text("Enter your email address...") },
                            isError = emailError,
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.mutedGrey
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = HexCladTheme.colors.pureWhite,
                                unfocusedTextColor = HexCladTheme.colors.pureWhite,
                                focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                                unfocusedBorderColor = HexCladTheme.colors.charcoalBorder,
                                focusedContainerColor = HexCladTheme.colors.charcoalCard,
                                unfocusedContainerColor = HexCladTheme.colors.charcoalCard
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("newsletter_email_input")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = {
                                if (newsletterEmail.isBlank() || !newsletterEmail.contains("@")) {
                                    emailError = true
                                } else {
                                    isSubscribed = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HexCladTheme.colors.metallicWarmGold,
                                contentColor = HexCladTheme.colors.deepCharcoal
                            ),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("newsletter_subscribe_btn")
                        ) {
                            Text(
                                text = "SUBSCRIBE & SAVE 10%",
                                style = HexCladTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. Social Media Channels
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "FOLLOW @HEXCLAD",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SocialChip(platform = "Instagram")
                    SocialChip(platform = "TikTok")
                    SocialChip(platform = "YouTube")
                    SocialChip(platform = "Facebook")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(20.dp))

            // 3. Three Link Columns: Shop, About, Support
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Column 1: SHOP
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "SHOP",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FooterLink(text = "Hybrid Cookware", onClick = onNavigateToCatalog)
                    FooterLink(text = "Cookware Sets", onClick = onNavigateToCatalog)
                    FooterLink(text = "Japanese Cutlery", onClick = onNavigateToCatalog)
                    FooterLink(text = "Pizza Steels", onClick = onNavigateToCatalog)
                    FooterLink(text = "Roasting Pans", onClick = onNavigateToCatalog)
                    FooterLink(text = "Care & Wax", onClick = onNavigateToCatalog)
                }

                // Column 2: ABOUT
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "DISCOVER",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FooterLink(text = "Our Story", onClick = onNavigateToAbout)
                    FooterLink(text = "The Science", onClick = onNavigateToScience)
                    FooterLink(text = "Recipe Hub", onClick = onNavigateToRecipes)
                    FooterLink(text = "Culinary Council", onClick = onNavigateToCulinaryCouncil)
                    FooterLink(text = "NY Yankees™", onClick = onNavigateToYankees)
                    FooterLink(text = "Food For Soul & LKA", onClick = onNavigateToCommunityPartners)
                }

                // Column 3: SUPPORT & LEGAL
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "POLICIES",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    FooterLink(text = "Refund Policy", onClick = { onOpenPolicy(PolicyType.REFUND) })
                    FooterLink(text = "Shipping Policy", onClick = { onOpenPolicy(PolicyType.SHIPPING) })
                    FooterLink(text = "Privacy Policy", onClick = { onOpenPolicy(PolicyType.PRIVACY) })
                    FooterLink(text = "Terms of Sale", onClick = { onOpenPolicy(PolicyType.TERMS) })
                    FooterLink(text = "Lifetime Guarantee", onClick = onNavigateToAbout)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
            Spacer(modifier = Modifier.height(16.dp))

            // 4. Payment Icons Row
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "GUARANTEED SAFE & SECURE CHECKOUT",
                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = HexCladTheme.colors.mutedGrey,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    PaymentBadge(name = "VISA")
                    PaymentBadge(name = "MC")
                    PaymentBadge(name = "AMEX")
                    PaymentBadge(name = "APPLE PAY")
                    PaymentBadge(name = "PAYPAL")
                    PaymentBadge(name = "SHOP PAY")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 5. Copyright
            Text(
                text = "© 2026 HexClad Hybrid Cookware. All Rights Reserved. Engineered for life with patented laser-etched technology.",
                style = HexCladTheme.typography.bodySmall.copy(fontSize = 10.sp),
                color = HexCladTheme.colors.darkGrey,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun FooterLink(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = HexCladTheme.typography.bodySmall,
        color = HexCladTheme.colors.lightGrey,
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    )
}

@Composable
fun SocialChip(platform: String) {
    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Text(
            text = platform,
            style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
            color = HexCladTheme.colors.lightGrey,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun PaymentBadge(name: String) {
    Surface(
        color = HexCladTheme.colors.deepCharcoal,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Text(
            text = name,
            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
            color = HexCladTheme.colors.mutedGrey,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
        )
    }
}
