package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Category Banner Header displaying:
 * - High-contrast luxury dark gradient
 * - Category headline / tagline ("FRY OFF THE HANDLE")
 * - Brand warranty callouts: Lifetime Warranty, Free Shipping, 30-Day Return Guarantee
 */
@Composable
fun CategoryHeaderBanner(
    categoryName: String = "Specialty Cookware",
    tagline: String = "FRY OFF THE HANDLE",
    subtitle: String = "Engineered with patented laser-etched hexagon technology. Searing power of stainless steel meets effortless nonstick release.",
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("category_header_banner")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            HexCladTheme.colors.charcoalSurfaceVariant,
                            HexCladTheme.colors.charcoalCard,
                            HexCladTheme.colors.deepCharcoal
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gold Tagline Header
                Surface(
                    color = HexCladTheme.colors.metallicWarmGoldContainer,
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(
                        1.dp,
                        HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)
                    )
                ) {
                    Text(
                        text = tagline,
                        style = HexCladTheme.typography.labelSmall.copy(
                            letterSpacing = 2.sp,
                            fontSize = 10.sp
                        ),
                        color = HexCladTheme.colors.metallicWarmGoldLight,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Title
                Text(
                    text = categoryName.uppercase(),
                    style = HexCladTheme.typography.headlineLarge,
                    color = HexCladTheme.colors.pureWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Subtitle
                Text(
                    text = subtitle,
                    style = HexCladTheme.typography.bodyMedium,
                    color = HexCladTheme.colors.mutedGrey,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Brand Warranty Callouts Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WarrantyCalloutItem(
                        icon = Icons.Default.Security,
                        label = "Lifetime\nWarranty"
                    )

                    Surface(
                        color = HexCladTheme.colors.charcoalBorder,
                        modifier = Modifier
                            .height(28.dp)
                            .width(1.dp)
                    ) {}

                    WarrantyCalloutItem(
                        icon = Icons.Default.LocalShipping,
                        label = "Free Contiguous\nUS Shipping"
                    )

                    Surface(
                        color = HexCladTheme.colors.charcoalBorder,
                        modifier = Modifier
                            .height(28.dp)
                            .width(1.dp)
                    ) {}

                    WarrantyCalloutItem(
                        icon = Icons.Default.Autorenew,
                        label = "30-Day Return\nGuarantee"
                    )
                }
            }
        }
    }
}

@Composable
private fun WarrantyCalloutItem(
    icon: ImageVector,
    label: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = HexCladTheme.colors.metallicWarmGold,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = label,
            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp, lineHeight = 12.sp),
            color = HexCladTheme.colors.pureWhite,
            fontWeight = FontWeight.SemiBold
        )
    }
}
