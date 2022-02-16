package vtsen.hashnode.dev.androidnews.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingMedium
import vtsen.hashnode.dev.androidnews.ui.theme.PaddingSmall
import vtsen.hashnode.dev.androidnews.utils.Utils
import vtsen.hashnode.dev.androidnews.viewmodel.Article

@Composable
fun ArticleCard(article: Article, onArticleCardClick: (Int) -> Unit) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
            .clickable {
                onArticleCardClick(article.id)
            }

    ) {
        ArticleRow(article)
        Spacer(Modifier.padding(PaddingSmall))

        ArticleBottomRow(article)
        Spacer(Modifier.padding(PaddingSmall))

        Divider(thickness = 2.dp)
    }
}

@Composable
private fun ArticleRow(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ArticleContent(article)
        ArticleImage(article)
    }
}

@Composable
private fun ArticleContent(article: Article) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(end = PaddingSmall)
    ) {

        Text(text = article.title, fontWeight = FontWeight.SemiBold)

        Spacer(Modifier.padding(PaddingMedium))
        Text(text = Utils.getElapsedTime(article.pubDate))
    }

}

@Composable
private fun ArticleImage(article: Article) {
    Image(
        painter = rememberImagePainter(
            data = article.image,
            builder = {
                placeholder(R.drawable.loading_animation)
            }
        ),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .size(150.dp, 150.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
private fun ArticleBottomRow(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        AddBookMarkIconButton(article)

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_share),
                contentDescription = null
            )
        }

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_radio_button_unchecked),
                contentDescription = null
            )
        }

        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_public),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AddBookMarkIconButton(article: Article) {
    IconButton(onClick = {}) {
        Icon(
            painter = painterResource(R.drawable.ic_bookmark_border),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ArticleCard(
        article = Utils.createArticle(),
        onArticleCardClick = { }
    )
}


