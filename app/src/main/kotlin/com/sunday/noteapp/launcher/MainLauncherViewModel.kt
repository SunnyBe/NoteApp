package com.sunday.noteapp.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val userIntroManager: UserIntroManager,
//    private val authHandler: AppAuthHandler,
//    private val userRepository: UserRepository
) : ViewModel() {
    val appInitState: StateFlow<AppInitState> =
        combine(
            onboardingState(),
            authState()
        ) { onboardingState, authState ->
            val isOnboarded = onboardingState is OnboardingState.Completed
            val isAuthed = authState is AuthState.Authenticated
            if (isOnboarded && isAuthed) {
                AppInitState.CanProceed(user = (authState as AuthState.Authenticated).user)
            } else {
                AppInitState.DoNotProceed(onboardingState, authState)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AppInitState.DoNotProceed()
        )
}

fun onboardingState(): Flow<OnboardingState> = flowOf(OnboardingState.Completed)
fun authState(): Flow<AuthState> = flowOf(AuthState.Authenticated("test-user-01"))

sealed interface AppInitState {
    data class CanProceed(val user: String) : AppInitState
    data class DoNotProceed(
        val onboardingState: OnboardingState = OnboardingState.NotStarted,
        val authState: AuthState = AuthState.Unknown
    ) : AppInitState
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
