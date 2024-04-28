package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: RegisterData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class RegisterData(

    @field:SerializedName("userId")
    val userId: String
)
