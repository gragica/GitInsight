package com.gragica.gitinsight.ui.screens.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gragica.gitinsight.domain.GetRepoDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val useCase: GetRepoDetailsUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<RepoUiState> = MutableStateFlow(RepoUiState.Loading)
    val uiState: StateFlow<RepoUiState> = _uiState

    fun getRepoData(userId: String, repoId: String) {
        viewModelScope.launch {
            useCase.invoke(userId, repoId).collect { result ->
                result.onSuccess { repoDetails ->
                    _uiState.update {
                        RepoUiState.Success(repoDetails)
                    }
                }
            }
        }
    }
}
