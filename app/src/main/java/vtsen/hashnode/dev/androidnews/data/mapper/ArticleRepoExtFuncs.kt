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
package vtsen.hashnode.dev.androidnews.data.mapper

import vtsen.hashnode.dev.androidnews.data.local.ArticleEntity
import vtsen.hashnode.dev.androidnews.data.repository.ArticleRepo

fun ArticleRepo.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = link.toUrlPath(),
        title = title,
        link = link,
        author = author,
        pubDate = pubDate,
        image = image,
        feedTitle = feedTitle,
    )
}
