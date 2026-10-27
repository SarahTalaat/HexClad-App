package com.example.ui.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Review
import com.example.ui.theme.HexCladTheme

/**
 * Individual Review Card displaying:
 * - Reviewer name & verified buyer badge
 * - Star rating & date
 * - Review title / headline
 * - Detailed body text
 * - Interactive helpful vote button with live count
 */
@Composable
fun ReviewCard(
    review: Review,
    initialHelpfulCount: Int = 24,
    modifier: Modifier = Modifier
) {
    var helpfulCount by remember { mutableIntStateOf(initialHelpfulCount) }
    var hasVotedHelpful by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("review_card_${review.id}"),
        colors = CardDefaults.cardColors(
            containerColor = HexCladTheme.colors.charcoalCard
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, HexCladTheme.colors.charcoalBorder)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Top Row: Reviewer Name & Date
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = review.author,
                        style = HexCladTheme.typography.titleMedium,
                        color = HexCladTheme.colors.pureWhite,
                        fontWeight = FontWeight.Bold
                    )

                    if (review.verifiedBuyer) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            color = HexCladTheme.colors.announcementGreenContainer,
                            shape = RoundedCornerShape(2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = null,
                                    tint = HexCladTheme.colors.announcementGreenText,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "VERIFIED BUYER",
                                    style = HexCladTheme.typography.labelSmall.copy(fontSize = 8.sp),
                                    color = HexCladTheme.colors.announcementGreenText,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Text(
                    text = review.date,
                    style = HexCladTheme.typography.bodySmall,
                    color = HexCladTheme.colors.mutedGrey
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Star Rating
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(review.rating.toInt()) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = HexCladTheme.colors.starRatingGold,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review Title
            Text(
                text = review.headline,
                style = HexCladTheme.typography.titleMedium,
                color = HexCladTheme.colors.pureWhite,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Review Body Text
            Text(
                text = review.content,
                style = HexCladTheme.typography.bodyMedium,
                color = HexCladTheme.colors.lightGrey,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Helpful Vote Button
            OutlinedButton(
                onClick = {
                    if (!hasVotedHelpful) {
                        helpfulCount++
                        hasVotedHelpful = true
                    }
                },
                border = BorderStroke(
                    1.dp,
                    if (hasVotedHelpful) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.charcoalBorder
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.testTag("helpful_btn_${review.id}")
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "Helpful",
                    tint = if (hasVotedHelpful) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.mutedGrey,
                    modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Helpful ($helpfulCount)",
                    style = HexCladTheme.typography.labelSmall,
                    color = if (hasVotedHelpful) HexCladTheme.colors.metallicWarmGold else HexCladTheme.colors.lightGrey
                )
            }
        }
    }
}
