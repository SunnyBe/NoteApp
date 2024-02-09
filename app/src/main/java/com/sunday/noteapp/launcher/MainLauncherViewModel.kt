package com.sunday.noteapp.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val userIntroManager: UserIntroManager,
//    private val recoveryManager: RecoveryManager,
//    private val authHandler: AppAuthHandler,
//    private val userRepository: UserRepository
) : ViewModel() {

    val appInitState: StateFlow<AppInitState> =
        combine(
            flowOf(true),
            flowOf(false),
            flowOf(true),
        ) { isOnboarded, isRecovering, isAuthed ->
            if (isOnboarded && !isRecovering && isAuthed) {
                AppInitState.CanProceed(user = "user-test")
            } else {
                AppInitState.DoNotProceed(
                    isOnboarded to OnboardingState.Completed,
                    isRecovering to RecoveryState.NoRecoveryState,
                    isAuthed to AuthState.Authenticated("user")
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            AppInitState.DoNotProceed()
        )

}

sealed interface AppInitState {
    data class CanProceed(val user: String) : AppInitState
    data class DoNotProceed(
        val isOnboarded: Pair<Boolean, OnboardingState> = false to OnboardingState.NotStarted,
        val isRecoveryOngoing: Pair<Boolean, RecoveryState> = false to RecoveryState.NoRecoveryState,
        val isAuthenticated: Pair<Boolean, AuthState> = false to AuthState.Unknown
    ): AppInitState
}

sealed interface OnboardingState {
    data object NotStarted : OnboardingState
    data class Ongoing(val isIntroduced: Boolean, val givenConsent: Boolean) : OnboardingState
    data object Completed : OnboardingState
}

sealed interface RecoveryState {
    data object NoRecoveryState : RecoveryState // Also complete recovery
    data class RecoveryBackupState(val backupState: BackupState) : RecoveryState
    data class RecoveryRestoreState(val backupState: RestoreState) : RecoveryState
}

sealed interface BackupState {
    data object NoBackup : BackupState
    data class Ongoing(val level: Long) : BackupState // TODO User [BackupStatus]
    data object Completed : BackupState
}

sealed interface RestoreState {
    data object NoBackup : RestoreState
    data class Ongoing(val level: Long) : RestoreState // TODO User [RecoveryStatus]
    data object Completed : RestoreState
}

sealed interface AuthState {
    data object Unknown : AuthState
    data object NotAuthenticated : AuthState
    data class Authenticated(val user: String) : AuthState // Todo user [User] entity
}

/*
MainActivity States
- OnboardingState
    - introduce to app
    - legal consent
- RestoreState
    - BackupState [not started, ongoing, completed ]
    - RestoreState [not started, ongoing, completed]
- AuthenticationState
    - User fetch
        - user payment plan
        - user configs
Reasons for AppInitState failure(Not progress)
- Onboarding:
    - User Service agreement consent
    - IsOnboarded
    - Configs are set
- Feature Availability
    - Location based/fetch

Services
- Feature Availability
- Location Service
- Sync Service
- Notification Service
- Hint Service
- Backup Service
- Authentication Service
 */
