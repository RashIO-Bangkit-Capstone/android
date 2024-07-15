package id.rashio.android.data.repository

import id.rashio.android.data.local.room.ArticleDao
import id.rashio.android.data.local.room.toArticleEntity
import id.rashio.android.data.model.BookmarkableArticle
import id.rashio.android.data.network.api.ApiService
import id.rashio.android.data.network.response.ArticleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) {

    private val networkArticles = flow {
        val response = apiService.getArticles()
        emit(
            response.fold(
                onSuccess = { articleResponse ->
                    articleResponse.data.sortedBy {
                        it.id
                    }
                        .map {
                            BookmarkableArticle(
                                articleId = it.id,
                                title = it.title,
                                imageUrl = it.imageUrl,
                                author = it.author,
                                referenceUrl = it.referenceUrl,
                                category = it.category,
                                isBookmarked = articleDao.exists(it.id)
                            )
                        }
                },
                onFailure = {
                    emptyList()
                }
            )
        )
    }

    fun getArticlesHome(): Flow<List<BookmarkableArticle>> = articleDao.getAllArticleBookmark()
        .combine(networkArticles) { bookmarkedArticles, networkArticles ->
            networkArticles.takeLast(3)
                .map { networkArticle ->
                    networkArticle.copy(isBookmarked = bookmarkedArticles.any { it.articleId == networkArticle.articleId })
                }
        }

    fun getArticles(): Flow<List<BookmarkableArticle>> = articleDao.getAllArticleBookmark()
        .combine(networkArticles) { bookmarkedArticles, networkArticles ->
            networkArticles
                .map { networkArticle ->
                    networkArticle.copy(isBookmarked = bookmarkedArticles.any { it.articleId == networkArticle.articleId })
                }
        }


    suspend fun bookmarkArticle(bookmarkableArticle: BookmarkableArticle) {
        val article = bookmarkableArticle.toArticleEntity()
        if (article.isBookmarked) articleDao.delete(article.articleId)
        else articleDao.insert(article)
    }


    fun getDetailArticle(articleId: String): Flow<BookmarkableArticle> = flow {
        val response = apiService.getArticleDetail(articleId)
        emit(
            response.fold(
                onSuccess = { it.articleData },
                onFailure = { ArticleData.empty() }
            )
        )
    }.map {
        BookmarkableArticle(
            articleId = it.id,
            title = it.title,
            imageUrl = it.imageUrl,
            author = it.author,
            referenceUrl = it.referenceUrl,
            category = it.category,
            body = it.bodies,
            isBookmarked = articleDao.exists(it.id)
        )
    }

    fun getBookmarkArticles(): Flow<List<BookmarkableArticle>> {
        return articleDao.getAllArticleBookmark().map { articleEntities ->
            articleEntities.map { entity ->
                BookmarkableArticle(
                    articleId = entity.articleId,
                    title = entity.title,
                    imageUrl = entity.imageUrl,
                    author = entity.author,
                    referenceUrl = entity.referenceUrl,
                    category = entity.category,
                    isBookmarked = true
                )
            }
        }
    }

}