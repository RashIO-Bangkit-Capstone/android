package id.rashio.android.data.network.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val articleData: ArticleData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class ArticleData(

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("bodies")
    val bodies: List<String>,

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
) {
    companion object {
        fun empty() = ArticleData("", "", emptyList(), "", 0, "", "", "")
    }
}
