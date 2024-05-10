package id.rashio.android.ui.screen.articles

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
class ArticlesViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val articles = articleRepository.getArticles()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
            initialValue = emptyList()
        )

    fun bookmarkArticle(bookmarkableArticle: BookmarkableArticle) {
        viewModelScope.launch {
            articleRepository.bookmarkArticle(bookmarkableArticle)
        }
    }

}