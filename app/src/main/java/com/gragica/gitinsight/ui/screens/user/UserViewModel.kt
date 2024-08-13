package com.gragica.gitinsight.ui.screens.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gragica.gitinsight.data.repository.user.UserRepository
import com.gragica.gitinsight.ui.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UserScreenUiState> =
        MutableStateFlow(UserScreenUiState())
    val uiState: StateFlow<UserScreenUiState> = _uiState

    fun fetchUser(userId: String) {
        viewModelScope.launch {
            userRepository.getUser(userId).collect { result ->
                result.onSuccess { user ->
                    _uiState.update {
                        it.copy(name = UiState.Success(user.name))
                    }
                }
                result.onFailure { error ->
                    _uiState.update {
                        it.copy(name = UiState.Error(error))
                    }
                }
            }
        }
    }

    fun fetchRepos(userId: String) {
        viewModelScope.launch {
            userRepository.getUserRepos(userId).collect { result ->
                result.onSuccess { repos ->
                    _uiState.update {
                        it.copy(repos = UiState.Success(repos))
                    }
                }
                result.onFailure { error ->
                    _uiState.update {
                        it.copy(repos = UiState.Error(error))
                    }
                }
            }
        }
    }
}
