package com.sunday.noteapp.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = combine(flowOf(1)) { (num) ->
        MainActivityUiState(
            isUserVerified = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainActivityUiState()
    )
}

data class MainActivityUiState(
    val isUserVerified: Boolean = false
)
