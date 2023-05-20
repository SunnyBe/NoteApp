package com.sunday.noteapp.landing

import android.content.Context
import android.content.Intent
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
import com.sunday.noteapp.ui.theme.NoteAppTheme

class LandingPageActivity : ComponentActivity() {

    private val viewModel by viewModels<LandingPageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                val uiState by viewModel.uiState.collectAsState()
                MainScreen(uiState = uiState)
            }
        }

    }

    companion object {
        fun startActivityIntent(
            context: Context
        ) {
            val intent = Intent(context, LandingPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}

@Composable
fun MainScreen(uiState: LandingPageUiState) {
    if (uiState.isUserVerified) {
        Column {
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
fun GreetingPreview() {
    NoteAppTheme {
        Greeting("Android")
    }
}