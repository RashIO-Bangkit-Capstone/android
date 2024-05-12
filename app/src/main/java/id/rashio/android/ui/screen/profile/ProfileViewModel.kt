package id.rashio.android.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.local.preferences.TokenPreference
import id.rashio.android.data.local.preferences.UserData
import id.rashio.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenPreference: TokenPreference,
) : ViewModel() {

    val userData = tokenPreference.userData.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        initialValue = UserData(
            name = "",
            email = "",
            phoneNumber = "",
            token = "",
            id = "",
            refreshToken = ""
        )
    )

    fun logout() {
        viewModelScope.launch {
            authenticationRepository.logout(userData.value.refreshToken)
            tokenPreference.clearUserData()
        }
    }
}