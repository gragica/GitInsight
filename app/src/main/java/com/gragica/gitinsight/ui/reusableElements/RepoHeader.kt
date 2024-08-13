package com.gragica.gitinsight.ui.reusableElements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gragica.gitinsight.R
import com.gragica.gitinsight.domain.FullRepoDetails

@Composable
fun RepoHeader(repoDetails: FullRepoDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = repoDetails.repoName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    model = repoDetails.ownerImageUrl,
                    contentDescription = "Avatar",
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = repoDetails.ownerName,
                    fontSize = 18.sp
                )
            }
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                RepoStat(iconRes = R.drawable.ic_fork, metric = repoDetails.forks)
                Spacer(modifier = Modifier.width(4.dp))
                RepoStat(iconRes = R.drawable.ic_watch, metric = repoDetails.watchers)
            }
        }
    }
}
