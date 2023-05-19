package com.sunday.noteapp.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sunday.cache.datastore.AggregateDataStore
import com.sunday.noteapp.landing.LandingPageActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var aggregateDataStore: AggregateDataStore
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            mainViewModel.uiState.collectLatest { uiState ->
                // Dispatch to expected screen based on ui state
                if (uiState.shouldOnboard) {
                    // TODO: Navigate to Onboarding screen
                    Toast.makeText(this@MainActivity, "Should be onboarding", Toast.LENGTH_LONG)
                        .show()
                } else {
                    // Navigate to Landing page
                    LandingPageActivity.dispatchLandingActivityIntent(this@MainActivity)
                }
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Timber.e(javaClass.name + ":::: App Start count: IsIncrementing?")
                aggregateDataStore.incrementAppStartCount()
            }
        }
    }
}