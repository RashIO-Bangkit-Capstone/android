package id.rashio.android.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class LoginUiState() {
    data object Loading : LoginUiState()
    data class Success(val authenticated: Boolean = false) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenPreference: TokenPreference
) : ViewModel() {
    fun login(email: String, password: String) = viewModelScope.launch {
        _uiState.value = LoginUiState.Loading
        authenticationRepository.login(email, password)
            .onSuccess { response ->
                tokenPreference.saveToken(response.data.accessToken)
                _uiState.value = LoginUiState.Success(authenticated = true)
            }
            .onFailure { e ->
                _uiState.value = LoginUiState.Error(e.message ?: "An error occurred")
            }
    }

    val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Success())
    val uiState: StateFlow<LoginUiState> = _uiState
}