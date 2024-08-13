package com.gragica.gitinsight.ui.reusableElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun RepoStat(
    @DrawableRes iconRes: Int,
    metric: Int
) {
    Row {
        Image(
            painter = painterResource(id = iconRes),
            colorFilter = ColorFilter.tint(Color.Gray),
            contentDescription = "Stat icon"
        )
        Text(
            text = metric.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
