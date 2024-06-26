package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: LoginData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class LoginData(

    @field:SerializedName("accessToken")
    val accessToken: String,

    @field:SerializedName("refreshToken")
    val refreshToken: String
)
