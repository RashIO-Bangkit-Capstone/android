package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: HistoryData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class HistoryData(

    @field:SerializedName("predictionLogs")
    val predictionLogs: List<PredictionLogsItem>
)

data class PredictionLogsItem(

    @field:SerializedName("result")
    val result: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("percentage")
    val percentage: Float
)
