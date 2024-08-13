package com.gragica.gitinsight.ui.screens.repo

import com.gragica.gitinsight.domain.FullRepoDetails

sealed class RepoUiState {

    data object Loading : RepoUiState()

    data class Success(
        val repoDetails: FullRepoDetails
    ) : RepoUiState()

    data object Error : RepoUiState()
}
