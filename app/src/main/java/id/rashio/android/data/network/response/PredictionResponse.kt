package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class Data(

    @field:SerializedName("result")
    val result: String? = null,

    @field:SerializedName("percentage")
    val percentage: Int? = null
)
