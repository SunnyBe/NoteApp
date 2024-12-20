package com.sunday.noteapp.landing

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunday.library.service.deeplink.BaseScreen
import com.sunday.library.ui.designsystem.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val baseSelection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(EXTRA_BASE_SELECTION, BaseScreen::class.java)
            } else {
                intent.getSerializableExtra(EXTRA_BASE_SELECTION)
            }
        setContent {
            NoteAppTheme {
                val uiState by viewModel.uiState.collectAsState()
                MainScreen(uiState = uiState)
            }
        }
    }

    companion object {
        fun getIntent(context: Context, baseSelection: BaseScreen = BaseScreen.Home): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(EXTRA_BASE_SELECTION, baseSelection)
            return intent
        }

        const val EXTRA_BASE_SELECTION = "extra-base-selection"
    }
}

@Composable
fun MainScreen(uiState: MainActivityUiState, modifier: Modifier = Modifier) {
    if (!uiState.isUserVerified) {
        Column(modifier) {
            Snackbar {
                Text(text = "You should verify this user!")
            }
            Text(text = "You should verify this user!")
        }
    } else {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    NoteAppTheme {
        Greeting("Android")
    }
}
