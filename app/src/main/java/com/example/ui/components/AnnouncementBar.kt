package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.HexCladTheme
import kotlinx.coroutines.delay

/**
 * Top Promotional Bar with emerald green background, sweepstakes headline,
 * and a live ticking countdown timer to create urgency.
 */
@Composable
fun AnnouncementBar(
    modifier: Modifier = Modifier,
    onShopNowClick: () -> Unit = {},
) {
    var isVisible by remember { mutableStateOf(true) }

    // Simulated live countdown starting at 00 Days 07 Hrs 22 Min 36 Sec
    var totalSeconds by remember { mutableIntStateOf(7 * 3600 + 22 * 60 + 36) }

    LaunchedEffect(Unit) {
        while (totalSeconds > 0) {
            delay(1000L)
            totalSeconds--
        }
    }

    val days = totalSeconds / 86400
    val hours = (totalSeconds % 86400) / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    val countdownText = String.format(
        "%02d DAYS %02d HRS %02d MIN %02d SEC",
        days, hours, minutes, seconds
    )

    AnimatedVisibility(
        visible = isVisible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(HexCladTheme.colors.announcementGreen)
                .clickable { onShopNowClick() }
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .testTag("announcement_bar"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Promotional Message
                Text(
                    text = "GORDON'S GOLDEN TICKET TO ITALY GIVEAWAY | UP TO 30% OFF | SHOP NOW",
                    style = HexCladTheme.typography.labelSmall,
                    color = HexCladTheme.colors.pureWhite,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.8.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Live Countdown Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Countdown Timer",
                        tint = HexCladTheme.colors.announcementGreenText,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Surface(
                        color = HexCladTheme.colors.announcementGreenContainer,
                        shape = RoundedCornerShape(2.dp)
                    ) {
                        Text(
                            text = countdownText,
                            style = HexCladTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            color = HexCladTheme.colors.announcementGreenText,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                    }
                }
            }

            IconButton(
                onClick = { isVisible = false },
                modifier = Modifier
                    .size(24.dp)
                    .testTag("close_announcement_button")
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dismiss Announcement",
                    tint = HexCladTheme.colors.pureWhite.copy(alpha = 0.7f),
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}
