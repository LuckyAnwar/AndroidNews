/*
 * Copyright 2023 Vincent Tsen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vtsen.hashnode.dev.androidnews.ui.screens.onearticle

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import vtsen.hashnode.dev.androidnews.domain.usecase.AddBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.AddReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.ClearArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetArticleStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.GetOneArticleUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RefreshArticlesStatusUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveBookmarkArticlesUseCase
import vtsen.hashnode.dev.androidnews.domain.usecase.RemoveReadArticlesUseCase
import vtsen.hashnode.dev.androidnews.ui.main.viewmodel.ArticlesViewModel

class OneArticleViewModel(
    getArticleStatusUseCase: GetArticleStatusUseCase,
    refreshArticlesStatusUseCase: RefreshArticlesStatusUseCase,
    clearArticlesStatusUseCase: ClearArticlesStatusUseCase,
    addBookmarkArticlesUseCase: AddBookmarkArticlesUseCase,
    removeBookmarkArticlesUseCase: RemoveBookmarkArticlesUseCase,
    addReadArticlesUseCase: AddReadArticlesUseCase,
    removeReadArticlesUseCase: RemoveReadArticlesUseCase,
    getOneArticleUseCase: GetOneArticleUseCase,
    articleId: String,
) : ArticlesViewModel(
        getArticleStatusUseCase,
        refreshArticlesStatusUseCase,
        clearArticlesStatusUseCase,
        addBookmarkArticlesUseCase,
        removeBookmarkArticlesUseCase,
        addReadArticlesUseCase,
        removeReadArticlesUseCase,
    ) {
    val article =
        getOneArticleUseCase(articleId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = null,
            )
}
