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
package vtsen.hashnode.dev.androidnews.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import vtsen.hashnode.dev.androidnews.domain.model.ArticleUi

class GetUnreadArticlesUseCase(
    private val getAllArticlesUseCase: GetAllArticlesUseCase,
) {
    operator fun invoke(title: String? = null): Flow<List<ArticleUi>> {
        val allArticlesFlow = getAllArticlesUseCase()

        val unreadArticlesFlow = allArticlesFlow.map { articleUiList ->
            articleUiList.filter { articleUi ->

                if (title.isNullOrEmpty()) {
                    !articleUi.read
                } else {
                    !articleUi.read && articleUi.title.contains(title)
                }
            }
        }

        return unreadArticlesFlow
    }
}
