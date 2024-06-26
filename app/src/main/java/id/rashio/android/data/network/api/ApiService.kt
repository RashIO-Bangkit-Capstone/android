package id.rashio.android.data.network.api

import id.rashio.android.data.network.request.LoginRequest
import id.rashio.android.data.network.request.LogoutRequest
import id.rashio.android.data.network.request.RegisterRequest
import id.rashio.android.data.network.response.ArticleResponse
import id.rashio.android.data.network.response.ArticlesResponse
import id.rashio.android.data.network.response.DiseaseResponse
import id.rashio.android.data.network.response.HistoryResponse
import id.rashio.android.data.network.response.LoginResponse
import id.rashio.android.data.network.response.LogoutResponse
import id.rashio.android.data.network.response.PredictionResponse
import id.rashio.android.data.network.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @POST("/v1/authentications")
    suspend fun login(
        @Body loginRequest: LoginRequest,
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<LoginResponse>

    @POST("/v1/users")
    suspend fun register(
        @Body registerRequest: RegisterRequest,
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<RegisterResponse>

    @GET("/v1/articles")
    suspend fun getArticles(
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<ArticlesResponse>

    @GET("/v1/articles/{id}")
    suspend fun getArticleDetail(
        @Path("id") id: String,
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<ArticleResponse>

    @HTTP(method = "DELETE", path = "/v1/authentications", hasBody = true)
    suspend fun logout(
        @Body logoutRequest: LogoutRequest,
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<LogoutResponse>

    @Multipart
    @POST("/v1/predictions")
    suspend fun predict(
        @Header("Authorization") authorization: String,
        @Part image: MultipartBody.Part
    ): Result<PredictionResponse>

    @GET("/v1/predictions/{userId}")
    suspend fun historyDetection(
        @Header("authorization") authorization: String,
        @Path("userId") id: String
    ): Result<HistoryResponse>

    @GET("/v1/diseases/{name}")
    suspend fun getDisease(
        @Path("name") name: String,
        @Header("Content-Type") contentType: String = "application/json"
    ): Result<DiseaseResponse>
}