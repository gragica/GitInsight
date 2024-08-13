package com.gragica.gitinsight.ui.screens.repo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gragica.gitinsight.R
import com.gragica.gitinsight.ui.reusableElements.RepoHeader
import com.gragica.gitinsight.ui.reusableElements.RepoTag
import com.gragica.gitinsight.ui.reusableElements.StandardErrorScreen
import com.gragica.gitinsight.ui.reusableElements.StandardLoadingScreen
import com.gragica.gitinsight.ui.reusableElements.StandardScreen

@Composable
fun RepoScreen(
    userId: String,
    repoId: String,
    onBack: (() -> Unit)? = null,
    viewModel: RepoViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(repoId) {
        viewModel.getRepoData(userId, repoId)
    }

    when (state.value) {
        is RepoUiState.Success -> {
            val details = (state.value as RepoUiState.Success).repoDetails
            StandardScreen(
                title = stringResource(id = R.string.title_repo, details.repoName),
                onBack = { onBack?.invoke() }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    // First item with different design
                    item {
                        RepoHeader(details)
                    }
                    items(details.tags.size) { item ->
                        RepoTag(details.tags[item])
                    }
                }
            }
        }

        is RepoUiState.Loading -> {
            StandardLoadingScreen()
        }

        is RepoUiState.Error -> {
            StandardErrorScreen()
        }
    }
}
