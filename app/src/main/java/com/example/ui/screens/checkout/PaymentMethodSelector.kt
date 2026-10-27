package com.example.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

data class PaymentFormData(
    val selectedMethod: String = "Credit Card",
    val cardNumber: String = "",
    val cardExpiry: String = "",
    val cardCvv: String = "",
    val cardHolderName: String = "",
    val sameAsShipping: Boolean = true
)

/**
 * Payment method selection component:
 * - Credit Card with card input details
 * - PayPal
 * - Klarna 4 Interest-Free Installments calculation
 * - 256-bit SSL secure checkout guarantee
 */
@Composable
fun PaymentMethodSelector(
    paymentData: PaymentFormData,
    onPaymentDataChange: (PaymentFormData) -> Unit,
    subtotal: Double,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("payment_method_selector")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PAYMENT METHOD",
                    style = HexCladTheme.typography.labelSmall.copy(
                        letterSpacing = 1.5.sp,
                        fontSize = 11.sp
                    ),
                    color = HexCladTheme.colors.metallicWarmGold,
                    fontWeight = FontWeight.Bold
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Encrypted",
                        tint = HexCladTheme.colors.announcementGreen,
                        modifier = Modifier.size(13.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "256-BIT ENCRYPTION",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = HexCladTheme.colors.announcementGreen,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Option 1: Credit Card
            PaymentOptionRow(
                title = "Credit Card",
                subtitle = "Visa, MasterCard, Amex, Discover",
                isSelected = paymentData.selectedMethod == "Credit Card",
                onClick = { onPaymentDataChange(paymentData.copy(selectedMethod = "Credit Card")) }
            )

            if (paymentData.selectedMethod == "Credit Card") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 6.dp)
                ) {
                    OutlinedTextField(
                        value = paymentData.cardNumber,
                        onValueChange = { onPaymentDataChange(paymentData.copy(cardNumber = it.take(19))) },
                        label = { Text("Card Number (•••• •••• •••• ••••)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_card_number"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = HexCladTheme.colors.pureWhite,
                            unfocusedTextColor = HexCladTheme.colors.pureWhite,
                            focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                            unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = paymentData.cardExpiry,
                            onValueChange = { onPaymentDataChange(paymentData.copy(cardExpiry = it.take(5))) },
                            label = { Text("MM / YY") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("input_card_expiry"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = HexCladTheme.colors.pureWhite,
                                unfocusedTextColor = HexCladTheme.colors.pureWhite,
                                focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                                unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                            )
                        )

                        OutlinedTextField(
                            value = paymentData.cardCvv,
                            onValueChange = { onPaymentDataChange(paymentData.copy(cardCvv = it.take(4))) },
                            label = { Text("CVV (3 or 4 digits)") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .weight(1f)
                                .testTag("input_card_cvv"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = HexCladTheme.colors.pureWhite,
                                unfocusedTextColor = HexCladTheme.colors.pureWhite,
                                focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                                unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = paymentData.cardHolderName,
                        onValueChange = { onPaymentDataChange(paymentData.copy(cardHolderName = it)) },
                        label = { Text("Name on Card") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_card_name"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = HexCladTheme.colors.pureWhite,
                            unfocusedTextColor = HexCladTheme.colors.pureWhite,
                            focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                            unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Option 2: Klarna 4 Installments
            val installmentAmount = (subtotal / 4.0).let { "%.2f".format(it) }
            PaymentOptionRow(
                title = "Klarna / Afterpay",
                subtitle = "4 interest-free payments of $$installmentAmount every 2 weeks",
                isSelected = paymentData.selectedMethod == "Klarna",
                onClick = { onPaymentDataChange(paymentData.copy(selectedMethod = "Klarna")) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Option 3: PayPal
            PaymentOptionRow(
                title = "PayPal",
                subtitle = "You will be redirected to PayPal to complete your purchase securely",
                isSelected = paymentData.selectedMethod == "PayPal",
                onClick = { onPaymentDataChange(paymentData.copy(selectedMethod = "PayPal")) }
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Billing address checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onPaymentDataChange(paymentData.copy(sameAsShipping = !paymentData.sameAsShipping))
                    }
                    .testTag("checkbox_same_billing_address")
            ) {
                Checkbox(
                    checked = paymentData.sameAsShipping,
                    onCheckedChange = { checked ->
                        onPaymentDataChange(paymentData.copy(sameAsShipping = checked))
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = HexCladTheme.colors.metallicWarmGold,
                        uncheckedColor = HexCladTheme.colors.charcoalBorder,
                        checkmarkColor = HexCladTheme.colors.deepCharcoal
                    )
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Billing address is the same as shipping address",
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.pureWhite
                )
            }
        }
    }
}

@Composable
private fun PaymentOptionRow(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (isSelected) HexCladTheme.colors.charcoalSurfaceVariant else HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(
            1.dp,
            if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = HexCladTheme.colors.metallicWarmGold,
                    unselectedColor = HexCladTheme.colors.charcoalBorder
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = title,
                    style = HexCladTheme.typography.titleSmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey,
                    fontSize = 11.sp
                )
            }
        }
    }
}
