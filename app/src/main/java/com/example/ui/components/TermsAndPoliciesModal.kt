package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.MockDataProvider
import com.example.model.PolicyType
import com.example.ui.theme.HexCladTheme

/**
 * Terms & Policies Modal Overlay (Prompt 39)
 *
 * Provides a dialog with tab/chip switching between:
 * - Refund Policy (Lifetime Warranty & 30-Day Returns)
 * - Privacy Policy (CCPA Rights & Data Protection)
 * - Terms of Service (Sale conditions & Sweepstakes rules)
 * - Shipping Policy (Fulfillment & Contiguous U.S. delivery)
 *
 * Features rich text rendering and accessible close triggers.
 */
@Composable
fun TermsAndPoliciesModal(
    initialPolicyType: PolicyType = PolicyType.REFUND,
    onDismissRequest: () -> Unit
) {
    var selectedType by remember { mutableStateOf(initialPolicyType) }
    val currentPolicy = MockDataProvider.policies[selectedType]

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .heightIn(max = 680.dp)
                .padding(vertical = 24.dp)
                .testTag("terms_policies_modal"),
            shape = RoundedCornerShape(12.dp),
            color = HexCladTheme.colors.charcoalCard,
            border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header with title and close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "LEGAL & POLICIES",
                            style = HexCladTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp),
                            color = HexCladTheme.colors.metallicWarmGold,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Customer Trust & Commitments",
                            style = HexCladTheme.typography.titleMedium,
                            color = HexCladTheme.colors.pureWhite
                        )
                    }

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.testTag("btn_close_policies_modal")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Modal",
                            tint = HexCladTheme.colors.pureWhite
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tab Selector Row for 4 Policies
                val policyTabs = listOf(
                    PolicyTabItem(PolicyType.REFUND, "Warranty & Returns", Icons.Default.Shield),
                    PolicyTabItem(PolicyType.SHIPPING, "Shipping Policy", Icons.Default.LocalShipping),
                    PolicyTabItem(PolicyType.PRIVACY, "Privacy Notice", Icons.Default.Policy),
                    PolicyTabItem(PolicyType.TERMS, "Terms of Sale", Icons.Default.Gavel)
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 6.dp)
                ) {
                    items(policyTabs) { tab ->
                        val isSelected = selectedType == tab.type
                        Surface(
                            modifier = Modifier
                                .clickable { selectedType = tab.type }
                                .testTag("policy_tab_${tab.type.name}"),
                            color = if (isSelected) HexCladTheme.colors.charcoalSurfaceVariant else HexCladTheme.colors.deepCharcoal,
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(
                                1.dp,
                                if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = null,
                                    tint = if (isSelected) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.mutedGrey,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = tab.title,
                                    style = HexCladTheme.typography.labelSmall,
                                    color = if (isSelected) HexCladTheme.colors.pureWhite else HexCladTheme.colors.lightGrey,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(12.dp))

                // Policy Body Content with Rich Text Rendering & Scroll State
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .testTag("policy_content_body")
                ) {
                    Text(
                        text = currentPolicy?.title ?: "Policy Document",
                        style = HexCladTheme.typography.titleLarge,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val rawContent = currentPolicy?.fullContentHtml ?: "Policy information currently unavailable."
                    
                    // Render formatted sections
                    val sections = parsePolicySections(rawContent)
                    sections.forEach { section ->
                        if (section.heading.isNotBlank()) {
                            Text(
                                text = section.heading,
                                style = HexCladTheme.typography.titleMedium,
                                color = HexCladTheme.colors.metallicWarmGoldLight,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        Text(
                            text = section.body,
                            style = HexCladTheme.typography.bodyMedium,
                            color = HexCladTheme.colors.lightGrey,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = HexCladTheme.colors.charcoalBorder)
                Spacer(modifier = Modifier.height(14.dp))

                // Bottom Action Button
                Button(
                    onClick = onDismissRequest,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HexCladTheme.colors.metallicWarmGold,
                        contentColor = HexCladTheme.colors.deepCharcoal
                    ),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("btn_policy_understand")
                ) {
                    Text(
                        text = "I UNDERSTAND & ACCEPT",
                        style = HexCladTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

private data class PolicyTabItem(
    val type: PolicyType,
    val title: String,
    val icon: ImageVector
)

private data class PolicySection(
    val heading: String,
    val body: String
)

/**
 * Converts raw HTML string into structured heading and body paragraphs.
 */
private fun parsePolicySections(html: String): List<PolicySection> {
    val clean = html
        .replace("<ul>", "\n")
        .replace("</ul>", "\n")
        .replace("<li>", "• ")
        .replace("</li>", "\n")
        .replace("<p>", "")
        .replace("</p>", "\n\n")

    val parts = clean.split("<h3>")
    val sections = mutableListOf<PolicySection>()

    parts.forEach { part ->
        if (part.isBlank()) return@forEach
        if (part.contains("</h3>")) {
            val hParts = part.split("</h3>")
            val heading = hParts[0].trim()
            val body = if (hParts.size > 1) {
                hParts[1].replace(Regex("<[^>]*>"), "").trim()
            } else ""
            sections.add(PolicySection(heading, body))
        } else {
            val body = part.replace(Regex("<[^>]*>"), "").trim()
            if (body.isNotBlank()) {
                sections.add(PolicySection("", body))
            }
        }
    }

    if (sections.isEmpty()) {
        sections.add(PolicySection("", html.replace(Regex("<[^>]*>"), "").trim()))
    }

    return sections
}
