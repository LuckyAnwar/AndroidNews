package vtsen.hashnode.dev.androidnews.ui.screens.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.screens.common.ArticlesScreen
import vtsen.hashnode.dev.androidnews.viewmodel.MainViewModel

@Composable
fun BookmarksScreen(
    viewModel: MainViewModel,
    navigateToArticle: (Int) -> Unit,
) {
    if(viewModel.bookmarkedArticles == null) return

    ArticlesScreen(
        viewModel = viewModel,
        articles = viewModel.bookmarkedArticles!! ,
        navigateToArticle = navigateToArticle,
        R.string.no_bookmarked_articles_desc,
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {

    val viewModel = MainViewModel(LocalContext.current, mockArticles = true)

    BookmarksScreen(
        viewModel,
        navigateToArticle = {})
}
