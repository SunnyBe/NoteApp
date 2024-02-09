package com.sunday.noteapp.launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.app.TaskStackBuilder
import androidx.lifecycle.lifecycleScope
import com.sunday.noteapp.deeplink.AppDeeplinkDispatcher
import com.sunday.noteapp.landing.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainLauncherActivity : ComponentActivity() {

    @Inject
    lateinit var deeplinkDispatcher: AppDeeplinkDispatcher

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            mainViewModel.appInitState.collectLatest { initState ->
                when (initState) {
                    is AppInitState.CanProceed -> routeAppIntent()
                    is AppInitState.DoNotProceed -> processAppInitState(initState)
                }
            }
        }
    }

    private fun processAppInitState(initState: AppInitState.DoNotProceed) {
        when {
            !initState.isOnboarded.first -> onboardNewUser(initState.isOnboarded.second)
            initState.isRecoveryOngoing.first -> continueDataRecovery(
                initState.isRecoveryOngoing.second
            )

            !initState.isAuthenticated.first -> requestUserAuthentication(
                initState.isAuthenticated.second
            )

            else -> {
                Timber.d("Illegal State for AppInitState - Do Not proceed!")
            }
        }
    }

    private fun routeAppIntent() {
        val appIntent = this.intent
        val targetIntents: Array<Intent> =
            createDeeplinkIntentStack(appIntent)
                ?: createMainActivityIntent(appIntent)
        startActivities(targetIntents)
        finish()
    }

    private fun createDeeplinkIntentStack(intent: Intent?): Array<Intent>? {
        if (intent == null) return null
        val dataUri: Uri = intent.data ?: return null
        val deeplinkTaskStack = deeplinkDispatcher.dispatchUri(dataUri) ?: return null
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(
            MainActivity.getIntent(
                this,
                deeplinkTaskStack.baseScreen
            )
        )
        deeplinkTaskStack.intentTask.forEach { target ->
            stackBuilder.addNextIntent(target)
        }
        return stackBuilder.intents
    }

    private fun createMainActivityIntent(intent: Intent?): Array<Intent> {
        val landingPageIntent = MainActivity.getIntent(this)
        return arrayOf(landingPageIntent)
    }

    private fun onboardNewUser(onboardingState: OnboardingState) {
//        val onboardingIntent = OnboardingActivity.getIntent()
//        startActivity(onboardingIntent)
    }

    private fun continueDataRecovery(recoveryState: RecoveryState) {
//        val dataRecoveryIntent = DataRecoveryActivity.getIntent()
//        startActivity(dataRecovery)
    }

    private fun requestUserAuthentication(authState: AuthState) {
//        val authIntent = AuthenticationActivity.getIntent()
//        startActivity(authIntent)
    }
}

