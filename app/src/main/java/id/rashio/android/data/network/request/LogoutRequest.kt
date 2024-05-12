package id.rashio.android.data.network.request

import com.google.gson.annotations.SerializedName

data class LogoutRequest(

    @field:SerializedName("refreshToken")
    val refreshToken: String
)
