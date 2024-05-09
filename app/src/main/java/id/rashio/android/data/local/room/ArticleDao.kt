package id.rashio.android.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookmarkArticleEntity: ArticleEntity)

    @Query("DELETE FROM bookmark_article WHERE articleId = :articleId")
    suspend fun delete(articleId: Int)

    @Query("SELECT * from bookmark_article ORDER BY articleId DESC")
    fun getAllArticleBookmark(): Flow<List<ArticleEntity>>

    @Query("SELECT EXISTS (SELECT * FROM bookmark_article WHERE articleId = :articleId)")
    suspend fun exists(articleId: Int): Boolean
}