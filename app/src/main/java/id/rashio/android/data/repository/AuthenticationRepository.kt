package id.rashio.android.data.repository

import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.request.LoginRequest
import id.rashio.android.data.network.request.RegisterRequest
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
    ) {
        val registerRequest = RegisterRequest(name, email, phoneNumber, password, confirmPassword)
        apiService.register(registerRequest)
    }

    suspend fun login(email: String, password: String): String {
        val loginRequest = LoginRequest(email, password)
        val loginResponse = apiService.login(loginRequest)
        return loginResponse.data.accessToken
    }
}