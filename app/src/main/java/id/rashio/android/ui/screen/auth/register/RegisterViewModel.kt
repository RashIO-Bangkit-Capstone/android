package id.rashio.android.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegisterUiState() {
    data object Loading : RegisterUiState()
    data class Success(val authenticated: Boolean = false) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
}

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    fun register(
        name: String,
        email: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String
    ) = viewModelScope.launch {
        _uiState.value = RegisterUiState.Loading
        authenticationRepository.register(name, email, phoneNumber, password, confirmPassword)
            .onSuccess {
                _uiState.value = RegisterUiState.Success(authenticated = true)
            }
            .onFailure { e ->
                _uiState.value = RegisterUiState.Error(e.message ?: "An error occurred")
            }
    }

    val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Success())
    val uiState: StateFlow<RegisterUiState> = _uiState

}