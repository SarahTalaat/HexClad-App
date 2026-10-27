package com.example.ui.screens.checkout

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

data class ShippingFormData(
    val email: String = "",
    val country: String = "United States",
    val firstName: String = "",
    val lastName: String = "",
    val address: String = "",
    val apartment: String = "",
    val city: String = "",
    val state: String = "CA",
    val zipCode: String = "",
    val phone: String = ""
)

/**
 * Checkout Contact & Shipping Form Component with outlined fields for:
 * - Email
 * - Country/Region dropdown
 * - First Name & Last Name
 * - Street Address & Apartment/Suite
 * - City, State dropdown & ZIP Code
 * - Phone Number
 */
@Composable
fun ShippingAddressForm(
    formData: ShippingFormData,
    onFormDataChange: (ShippingFormData) -> Unit,
    modifier: Modifier = Modifier
) {
    var isCountryExpanded by remember { mutableStateOf(false) }
    var isStateExpanded by remember { mutableStateOf(false) }

    val countries = listOf("United States", "Canada", "United Kingdom", "Australia")
    val usStates = listOf("CA", "TX", "NY", "FL", "IL", "WA", "CO", "OH", "NC", "GA")

    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .fillMaxWidth()
            .testTag("shipping_address_form")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "CONTACT & SHIPPING DETAILS",
                style = HexCladTheme.typography.labelSmall.copy(
                    letterSpacing = 1.5.sp,
                    fontSize = 11.sp
                ),
                color = HexCladTheme.colors.metallicWarmGold,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 1. Email Field
            OutlinedTextField(
                value = formData.email,
                onValueChange = { onFormDataChange(formData.copy(email = it)) },
                label = { Text("Email Address for Order Confirmation") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_email"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HexCladTheme.colors.pureWhite,
                    unfocusedTextColor = HexCladTheme.colors.pureWhite,
                    focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                    unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 2. Country / Region Dropdown
            Box {
                OutlinedTextField(
                    value = formData.country,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Country / Region") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Select Country",
                            tint = HexCladTheme.colors.metallicWarmGold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isCountryExpanded = true }
                        .testTag("dropdown_country"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    )
                )

                DropdownMenu(
                    expanded = isCountryExpanded,
                    onDismissRequest = { isCountryExpanded = false },
                    modifier = Modifier.background(HexCladTheme.colors.charcoalCard)
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = { Text(country, color = HexCladTheme.colors.pureWhite) },
                            onClick = {
                                onFormDataChange(formData.copy(country = country))
                                isCountryExpanded = false
                            },
                            colors = MenuDefaults.itemColors(textColor = HexCladTheme.colors.pureWhite)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3. First Name & Last Name Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = formData.firstName,
                    onValueChange = { onFormDataChange(formData.copy(firstName = it)) },
                    label = { Text("First Name") },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("input_first_name"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    )
                )

                OutlinedTextField(
                    value = formData.lastName,
                    onValueChange = { onFormDataChange(formData.copy(lastName = it)) },
                    label = { Text("Last Name") },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("input_last_name"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 4. Street Address
            OutlinedTextField(
                value = formData.address,
                onValueChange = { onFormDataChange(formData.copy(address = it)) },
                label = { Text("Street Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_address"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HexCladTheme.colors.pureWhite,
                    unfocusedTextColor = HexCladTheme.colors.pureWhite,
                    focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                    unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 5. Apartment / Suite / Unit
            OutlinedTextField(
                value = formData.apartment,
                onValueChange = { onFormDataChange(formData.copy(apartment = it)) },
                label = { Text("Apartment, suite, unit (optional)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_apartment"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HexCladTheme.colors.pureWhite,
                    unfocusedTextColor = HexCladTheme.colors.pureWhite,
                    focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                    unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 6. City, State Dropdown & ZIP Code Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = formData.city,
                    onValueChange = { onFormDataChange(formData.copy(city = it)) },
                    label = { Text("City") },
                    modifier = Modifier
                        .weight(1.4f)
                        .testTag("input_city"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    )
                )

                // State Dropdown
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = formData.state,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("State") },
                        trailingIcon = {
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = HexCladTheme.colors.metallicWarmGold
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isStateExpanded = true }
                            .testTag("dropdown_state"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = HexCladTheme.colors.pureWhite,
                            unfocusedTextColor = HexCladTheme.colors.pureWhite,
                            focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                            unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                        )
                    )

                    DropdownMenu(
                        expanded = isStateExpanded,
                        onDismissRequest = { isStateExpanded = false },
                        modifier = Modifier.background(HexCladTheme.colors.charcoalCard)
                    ) {
                        usStates.forEach { st ->
                            DropdownMenuItem(
                                text = { Text(st, color = HexCladTheme.colors.pureWhite) },
                                onClick = {
                                    onFormDataChange(formData.copy(state = st))
                                    isStateExpanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = formData.zipCode,
                    onValueChange = { onFormDataChange(formData.copy(zipCode = it)) },
                    label = { Text("ZIP") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("input_zip"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = HexCladTheme.colors.pureWhite,
                        unfocusedTextColor = HexCladTheme.colors.pureWhite,
                        focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                        unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 7. Phone Number
            OutlinedTextField(
                value = formData.phone,
                onValueChange = { onFormDataChange(formData.copy(phone = it)) },
                label = { Text("Phone Number for Delivery Updates") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("input_phone"),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = HexCladTheme.colors.pureWhite,
                    unfocusedTextColor = HexCladTheme.colors.pureWhite,
                    focusedBorderColor = HexCladTheme.colors.metallicWarmGold,
                    unfocusedBorderColor = HexCladTheme.colors.charcoalBorder
                )
            )
        }
    }
}
