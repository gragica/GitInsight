package com.gragica.gitinsight.ui.screens.user

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.gragica.gitinsight.R
import com.gragica.gitinsight.ui.reusableElements.RepoItem
import com.gragica.gitinsight.ui.reusableElements.StandardErrorScreen
import com.gragica.gitinsight.ui.reusableElements.StandardLoadingScreen
import com.gragica.gitinsight.ui.reusableElements.StandardScreen
import com.gragica.gitinsight.ui.util.UiState

@Composable
fun UserScreen(
    userId: String,
    onRepoSelected: (String, String) -> Unit = { _, _ -> },
    viewModel: UserViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    LaunchedEffect(userId) {
        viewModel.fetchUser(userId)
        viewModel.fetchRepos(userId)
    }

    when {
        state.value.name is UiState.Success && state.value.repos is UiState.Success -> {
            val name = (state.value.name as UiState.Success).value
            val repos = (state.value.repos as UiState.Success).value

            StandardScreen(title = stringResource(id = R.string.title_user, name)) {
                LazyColumn {
                    items(repos.size) { item ->
                        RepoItem(text = repos[item].name, number = repos[item].openIssues) {
                            onRepoSelected(userId, it)
                        }
                    }
                }
            }
        }

        state.value.name is UiState.Error || state.value.repos is UiState.Error -> {
            StandardErrorScreen(onReload = { viewModel.fetchUser(userId) })
        }

        else -> {
            StandardLoadingScreen()
        }
    }
}
