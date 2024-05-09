package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: List<ArticlesDataItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class ArticlesDataItem(

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("referenceUrl")
    val referenceUrl: String
)

