package id.rashio.android.data.repository

import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.request.LoginRequest
import id.rashio.android.data.network.request.LogoutRequest
import id.rashio.android.data.network.request.RegisterRequest
import id.rashio.android.data.network.response.LoginResponse
import id.rashio.android.data.network.response.LogoutResponse
import id.rashio.android.data.network.response.RegisterResponse
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun register(
        name: String,
        email: String,
        phoneNumber: String,
        password: String,
        confirmPassword: String
    ): Result<RegisterResponse> {
        val registerRequest = RegisterRequest(name, email, phoneNumber, password, confirmPassword)
        return apiService.register(registerRequest)
    }

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return apiService.login(loginRequest)
    }

    suspend fun logout(refreshToken: String): Result<LogoutResponse> {
        val logoutRequest = LogoutRequest(refreshToken)
        return apiService.logout(logoutRequest)
    }
}