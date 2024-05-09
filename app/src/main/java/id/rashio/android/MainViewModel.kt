package id.rashio.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenPreference: TokenPreference
) : ViewModel() {

    val authState = tokenPreference.userData.map { userData ->
        if (userData.token.isEmpty()) {
            AuthenticationState.Unauthenticated
        } else {
            AuthenticationState.Authenticated
        }
    }.sample(2000L).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = AuthenticationState.Unknown
    )

}

sealed class AuthenticationState {
    data object Unauthenticated : AuthenticationState()
    data object Authenticated : AuthenticationState()
    data object Unknown : AuthenticationState()
}