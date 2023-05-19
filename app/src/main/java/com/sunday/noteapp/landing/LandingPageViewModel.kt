package com.sunday.noteapp.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class LandingPageViewModel : ViewModel() {

    val uiState: StateFlow<LandingPageUiState> = combine(flowOf(1)) { (num) ->
        LandingPageUiState()
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LandingPageUiState()
        )
}

data class LandingPageUiState(
    val isUserVerified: Boolean = false
)