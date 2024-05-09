package id.rashio.android.data.local.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.rashio.android.data.model.BookmarkableArticle
import kotlinx.parcelize.Parcelize

@Entity(tableName = "bookmark_article")
@Parcelize
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val articleId: Int,
    val title: String,
    val imageUrl: String,
    val author: String,
    val referenceUrl: String,
    val category: String,
    val isBookmarked: Boolean
) : Parcelable

fun ArticleEntity.toBookmarkableArticle(): BookmarkableArticle {
    return BookmarkableArticle(
        articleId = articleId,
        title = title,
        imageUrl = imageUrl,
        author = author,
        referenceUrl = referenceUrl,
        category = category,
        isBookmarked = isBookmarked
    )
}

fun BookmarkableArticle.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        articleId = articleId,
        title = title,
        imageUrl = imageUrl,
        author = author,
        referenceUrl = referenceUrl,
        category = category,
        isBookmarked = isBookmarked
    )
}