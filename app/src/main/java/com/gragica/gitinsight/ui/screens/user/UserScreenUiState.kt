package com.gragica.gitinsight.ui.screens.user

import com.gragica.gitinsight.core.model.Repo
import com.gragica.gitinsight.ui.util.UiState

data class UserScreenUiState(
    val name: UiState<String> = UiState.Loading,
    val repos: UiState<List<Repo>> = UiState.Loading
)
