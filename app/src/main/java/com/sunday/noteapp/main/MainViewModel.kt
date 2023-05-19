package com.sunday.noteapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.cache.datastore.AggregateDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val aggregateDataStore: AggregateDataStore
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> =
        combine(aggregateDataStore.appStartCount()) { (appStartCount) ->
            Timber.e(javaClass.name + ":::: App Start count: " + appStartCount)
            MainActivityUiState(
                shouldOnboard = appStartCount < 2
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            MainActivityUiState()
        )


}

data class MainActivityUiState(
    val shouldOnboard: Boolean = false
)