package id.rashio.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenPreference: TokenPreference
) : ViewModel() {

    val authState = tokenPreference.token.map { token ->
        if (token.isEmpty()) {
            AuthenticationState.Unauthenticated
        } else {
            AuthenticationState.Authenticated
        }
    }.sample(2000L).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = AuthenticationState.Unknown
    )

    fun register(
        name: String,
        email: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String
    ) = viewModelScope.launch {
        authenticationRepository.register(name, email, phoneNumber, password, confirmPassword)
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        val token = authenticationRepository.login(email, password)
        tokenPreference.saveToken(token)
    }

}

sealed class AuthenticationState {
    data object Unauthenticated : AuthenticationState()
    data object Authenticated : AuthenticationState()
    data object Unknown : AuthenticationState()
}