package com.gragica.gitinsight.ui.reusableElements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StandardScreen(
    title: String,
    onBack: (() -> Unit)? = null,
    onNext: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            StandardAppBar(title = title, onBackButton = onBack)
            content()
        }
    }
}
