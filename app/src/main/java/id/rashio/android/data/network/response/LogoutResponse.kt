package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)
