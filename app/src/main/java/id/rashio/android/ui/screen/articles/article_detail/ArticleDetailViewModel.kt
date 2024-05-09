package id.rashio.android.ui.screen.articles.article_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.rashio.android.data.model.BookmarkableArticle
import id.rashio.android.data.repository.ArticleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val articleId: String = checkNotNull(savedStateHandle["articleId"])
    val article = articleRepository.getDetailArticle(articleId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = BookmarkableArticle(
                articleId = 0,
                title = "",
                imageUrl = "",
                author = "",
                referenceUrl = "",
                category = "",
                isBookmarked = false,
                body = emptyList()
            )
        )

    fun bookmarkArticle(bookmarkableArticle: BookmarkableArticle) {
        viewModelScope.launch {
            articleRepository.bookmarkArticle(bookmarkableArticle)
        }
    }

}
