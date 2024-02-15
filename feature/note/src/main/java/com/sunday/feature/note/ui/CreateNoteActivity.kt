package com.sunday.feature.note.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class CreateNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun getIntent(context: Context, content: String?): Intent {
            return Intent(context, CreateNoteActivity::class.java)
        }
    }
}