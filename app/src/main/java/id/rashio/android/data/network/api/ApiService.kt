package id.rashio.android.data.network.api

import id.rashio.android.data.network.request.LoginRequest
import id.rashio.android.data.network.request.RegisterRequest
import id.rashio.android.data.network.response.LoginResponse
import id.rashio.android.data.network.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/v1/authentications")
    suspend fun login(
        @Body loginRequest: LoginRequest,
        @Header("Content-Type") contentType: String = "application/json"
    ): LoginResponse

    @POST("/v1/users")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
        @Header("Content-Type") contentType: String = "application/json"
    ): RegisterResponse

}