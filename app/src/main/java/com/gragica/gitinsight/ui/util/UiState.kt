package com.gragica.gitinsight.ui.util

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<out T>(val value: T) : UiState<T>()
    data class Error(val exception: Throwable) : UiState<Nothing>()
}
