package id.rashio.android.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.Base64
import javax.inject.Inject


sealed class LoginUiState {
    data object Loading : LoginUiState()
    data object Idle : LoginUiState()
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
                val token = response.data.accessToken
                val refreshToken = response.data.refreshToken
                val body = token.split(".").get(1)
                val decoded = Base64.getDecoder().decode(body)
                val jsonObject = JSONObject(String(decoded))
                val name = jsonObject.getString("name")
                val phoneNumber = jsonObject.getString("phoneNumber")
                val id = jsonObject.getString("id")
                tokenPreference.saveUserData(
                    name = name,
                    email = email,
                    phoneNumber = phoneNumber,
                    token = token,
                    id = id,
                    refreshToken = refreshToken
                )
            }
            .onFailure { e ->
                _uiState.value = LoginUiState.Error(e.message ?: "An error occurred")
                delay(5000)
                _uiState.value = LoginUiState.Idle
            }
    }

    val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState
}