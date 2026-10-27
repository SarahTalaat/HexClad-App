package com.example.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * Express Checkout Buttons displaying:
 * - Shop Pay (purple #5A31F4)
 * - PayPal (yellow #FFC439)
 * - Google Pay (black #000000)
 * - "OR" divider line for manual billing form transition
 */
@Composable
fun ExpressCheckout(
    onExpressPaySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("express_checkout_section")
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EXPRESS CHECKOUT",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.5.sp,
                    fontSize = 11.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 1. Shop Pay Button (Purple)
            Button(
                onClick = { onExpressPaySelected("Shop Pay") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5A31F4),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_shoppay")
            ) {
                Text(
                    text = "Shop Pay",
                    style = HexCladTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 2. PayPal Button (Yellow)
            Button(
                onClick = { onExpressPaySelected("PayPal") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC439),
                    contentColor = Color(0xFF003087)
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_paypal")
            ) {
                Text(
                    text = "PayPal",
                    style = HexCladTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.SansSerif
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Google Pay Button (Black)
            Button(
                onClick = { onExpressPaySelected("Google Pay") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000000),
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color(0xFF333333)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("btn_gpay")
            ) {
                Text(
                    text = "G Pay",
                    style = HexCladTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    color = HexCladTheme.colors.charcoalBorder,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "OR CONTINUE BELOW",
                    style = HexCladTheme.typography.labelSmall.copy(
                        letterSpacing = 1.2.sp,
                        fontSize = 10.sp
                    ),
                    color = HexCladTheme.colors.mutedGrey,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                HorizontalDivider(
                    color = HexCladTheme.colors.charcoalBorder,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
