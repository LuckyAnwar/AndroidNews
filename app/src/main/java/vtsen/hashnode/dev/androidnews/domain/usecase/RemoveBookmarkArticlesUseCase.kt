package vtsen.hashnode.dev.androidnews.domain.usecase

import vtsen.hashnode.dev.androidnews.data.repository.UserPreferencesRepository

class RemoveBookmarkArticlesUseCase(
    private val userPrefsRepository: UserPreferencesRepository
) {
    suspend operator fun invoke(articleId: String) {
        userPrefsRepository.removeBookmarkArticle(articleId)
    }
}