package id.rashio.android.data.model

data class BookmarkableArticle(
    val articleId: Int,
    val title: String,
    val imageUrl: String,
    val author: String,
    val referenceUrl: String,
    val category: String,
    val body: List<String> = emptyList(),
    val isBookmarked: Boolean
)

