package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class DiseaseResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: DataDisease,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataDisease(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("descriptions")
    val descriptions: List<String>,

    @field:SerializedName("treatments")
    val treatments: List<String>
)
