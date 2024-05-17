package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Data(

    @field:SerializedName("result")
    val result: String,

    @field:SerializedName("percentage")
    val percentage: Float,

    @field:SerializedName("imageUrl")
    val imageUrl: String
)
