package vtsen.hashnode.dev.androidnews.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import vtsen.hashnode.dev.androidnews.domain.model.Article
import vtsen.hashnode.dev.androidnews.domain.usecase.*
import vtsen.hashnode.dev.androidnews.domain.mapper.toArticlesUiState
import vtsen.hashnode.dev.androidnews.domain.model.ArticlesUiState

open class ArticlesViewModel(
    protected val getArticleStatusUseCase: GetArticleStatusUseCase,
    protected val refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    protected val clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    protected val addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    protected val removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    protected val addReadArticlesUseCase: AddReadArticlesUseCase,
    protected val removeReadArticlesUseCase: RemoveReadArticlesUseCase,
    protected val getArticleUseCase: GetArticleUseCase,
) : ViewModel() {

    val uiState = getArticleStatusUseCase().map { repositoryStatus ->
        repositoryStatus.toArticlesUiState()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = ArticlesUiState.Success
    )

    fun refresh() = viewModelScope.launch {
        refreshArticlesStatusUseCase()
    }

    fun clearStatus() = clearArticlesStatusUseCase()

    fun onReadClick(article: Article) = viewModelScope.launch {
        if(article.read) {
            removeReadArticlesUseCase(article.id)
        } else {
            addReadArticlesUseCase(article.id)
        }
    }

    fun onBookmarkClick(article: Article) = viewModelScope.launch {
        if(article.bookmarked) {
            removeBookmarkArticlesUseCase(article.id)
        } else {
            addBookmarkArticlesUseCase(article.id)
        }
    }

    fun getArticle(articleId: String) = getArticleUseCase(articleId)
}

