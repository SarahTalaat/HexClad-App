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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme

/**
 * User Authentication Screen (Prompt 48)
 *
 * Provides:
 * - Tab switching between "Sign In" and "Create Account"
 * - Email, Password, Name form inputs with validation
 * - Quick Guest & Social sign-in options
 * - HexClad VIP benefits showcase
 */
@Composable
fun AuthScreen(
    onBackClick: () -> Unit,
    onAuthSuccess: (email: String, name: String) -> Unit,
    onContinueAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isRegisterMode by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(HexCladTheme.colors.deepCharcoal)
            .testTag("auth_screen"),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // Top Nav Back
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.testTag("auth_back_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = HexCladTheme.colors.pureWhite
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "BACK TO STORE",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.mutedGrey,
                    letterSpacing = 1.3.sp
                )
            }
        }

        // Header Branding
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
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = HexCladTheme.colors.metallicWarmGoldContainer,
                        shape = RoundedCornerShape(50),
                        border = BorderStroke(1.dp, HexCladTheme.colors.metallicWarmGoldLight.copy(alpha = 0.4f)),
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        Text(
                            text = "HEXCLAD CULINARY CLUB",
                            style = HexCladTheme.typography.labelSmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = if (isRegisterMode) "Create Your Account" else "Welcome Back",
                        style = HexCladTheme.typography.headlineMedium,
                        color = HexCladTheme.colors.pureWhite,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isRegisterMode)
                            "Unlock member-exclusive discounts, order tracking, and earn 4x Golden Ticket sweepstakes entries."
                        else
                            "Sign in to track orders, manage your lifetime warranty, and view your Golden Ticket balance.",
                        style = HexCladTheme.typography.bodySmall,
                        color = HexCladTheme.colors.mutedGrey,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Mode Switch Tabs
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(HexCladTheme.colors.charcoalCard)
                            .padding(4.dp)
                    ) {
                        Surface(
                            color = if (!isRegisterMode) HexCladTheme.colors.metallicWarmGold else Color.Transparent,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    isRegisterMode = false
                                    errorMessage = null
                                }
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "SIGN IN",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = if (!isRegisterMode) HexCladTheme.colors.deepCharcoal else HexCladTheme.colors.lightGrey,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Surface(
                            color = if (isRegisterMode) HexCladTheme.colors.metallicWarmGold else Color.Transparent,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    isRegisterMode = true
                                    errorMessage = null
                                }
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "CREATE ACCOUNT",
                                    style = HexCladTheme.typography.labelSmall,
                                    color = if (isRegisterMode) HexCladTheme.colors.deepCharcoal else HexCladTheme.colors.lightGrey,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Form Fields
        item {
            Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                // Name (Register only)
                if (isRegisterMode) {
                    Text(
                        text = "FULL NAME",
                        style = HexCladTheme.typography.labelSmall,
                        color = HexCladTheme.colors.metallicWarmGold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        placeholder = { Text("e.g. Gordon Ramsay") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = HexCladTheme.colors.mutedGrey
                            )
                        },
                        singleLine = true,
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
                            .testTag("auth_name_input")
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Email
                Text(
                    text = "EMAIL ADDRESS",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("chef@example.com") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = HexCladTheme.colors.mutedGrey
                        )
                    },
                    singleLine = true,
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
                        .testTag("auth_email_input")
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Password
                Text(
                    text = "PASSWORD",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.metallicWarmGold
                )
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("••••••••") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = HexCladTheme.colors.mutedGrey
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = HexCladTheme.colors.mutedGrey
                            )
                        }
                    },
                    singleLine = true,
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
                        .testTag("auth_password_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Remember Me & Forgot Password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = HexCladTheme.colors.metallicWarmGold,
                                checkmarkColor = HexCladTheme.colors.deepCharcoal
                            )
                        )
                        Text(
                            text = "Remember me",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.lightGrey
                        )
                    }

                    if (!isRegisterMode) {
                        Text(
                            text = "Forgot password?",
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.metallicWarmGold,
                            modifier = Modifier.clickable { }
                        )
                    }
                }

                // Error Message Display
                errorMessage?.let { msg ->
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = HexCladTheme.colors.luxurySaleBadge.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(6.dp),
                        border = BorderStroke(1.dp, HexCladTheme.colors.luxurySaleBadge)
                    ) {
                        Text(
                            text = msg,
                            style = HexCladTheme.typography.bodySmall,
                            color = HexCladTheme.colors.pureWhite,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Primary Submit Button
                Button(
                    onClick = {
                        if (email.isBlank() || !email.contains("@")) {
                            errorMessage = "Please enter a valid email address."
                            return@Button
                        }
                        if (password.length < 6) {
                            errorMessage = "Password must be at least 6 characters."
                            return@Button
                        }
                        if (isRegisterMode && name.isBlank()) {
                            errorMessage = "Please enter your name."
                            return@Button
                        }
                        val resolvedName = if (isRegisterMode) name else "Gordon Vance"
                        onAuthSuccess(email, resolvedName)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.metallicWarmGold,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("auth_submit_btn")
                ) {
                    Text(
                        text = if (isRegisterMode) "CREATE ACCOUNT" else "SIGN IN",
                        style = HexCladTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Guest option
                OutlinedButton(
                    onClick = onContinueAsGuest,
                    border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = HexCladTheme.colors.lightGrey),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("auth_guest_btn")
                ) {
                    Text(
                        text = "CONTINUE AS GUEST",
                        style = HexCladTheme.typography.labelMedium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Divider with OR
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = HexCladTheme.colors.charcoalBorder
                    )
                    Text(
                        text = "  OR CONNECT WITH  ",
                        style = HexCladTheme.typography.labelSmall.copy(fontSize = 10.sp),
                        color = HexCladTheme.colors.mutedGrey
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = HexCladTheme.colors.charcoalBorder
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Fast Social Auth Simulation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    SocialAuthButton(
                        label = "Google",
                        onClick = { onAuthSuccess("chef.google@example.com", "Executive Member") },
                        modifier = Modifier.weight(1f)
                    )
                    SocialAuthButton(
                        label = "Apple",
                        onClick = { onAuthSuccess("chef.apple@example.com", "Executive Member") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun SocialAuthButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = HexCladTheme.colors.charcoalCard,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder),
        modifier = modifier
            .height(44.dp)
            .clickable { onClick() }
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = HexCladTheme.typography.labelMedium,
                color = HexCladTheme.colors.pureWhite,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
