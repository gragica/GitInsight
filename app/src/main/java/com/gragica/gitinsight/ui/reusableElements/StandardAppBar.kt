package com.gragica.gitinsight.ui.reusableElements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gragica.gitinsight.R

@Composable
fun StandardAppBar(
    title: String? = null,
    onBackButton: (() -> Unit)? = null,
    onEndButton: (() -> Unit)? = null,
    @DrawableRes endButtonRes: Int? = null
) {
    Row(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackButton != null)
            Image(
                modifier = Modifier.clickable { onBackButton() }.padding(end = 16.dp),
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(id = R.string.cd_back_button)
            )

        if (title != null) {
            Text(text = title, style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold))
        }

        if (onEndButton != null && endButtonRes != null) {
            Image(
                modifier = Modifier.clickable { onEndButton() },
                painter = painterResource(id = endButtonRes),
                contentDescription = stringResource(id = R.string.cd_back_button),
            )
        }
    }
}
