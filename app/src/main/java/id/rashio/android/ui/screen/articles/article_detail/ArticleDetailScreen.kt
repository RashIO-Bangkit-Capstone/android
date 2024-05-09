package id.rashio.android.ui.screen.articles.article_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.rashio.android.R
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.components.article_detail.ArticleInfo
import id.rashio.android.ui.components.article_detail.BannerArticle
import id.rashio.android.ui.components.article_detail.BodyArticle

@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    navController: NavController
) {

    val article by viewModel.article.collectAsState()
    var isBookmarked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = article) {
        isBookmarked = article.isBookmarked
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComp(
                title = "Article",
                onBackClick = { navController.popBackStack() },
                actions = listOf {
                    IconButton(onClick = {
                        viewModel.bookmarkArticle(article)
                        isBookmarked = !isBookmarked
                    }) {
                        if (isBookmarked) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_bookmark_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )

        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                BannerArticle(imageUrl = article.imageUrl, title = article.title)
                ArticleInfo(
                    author = article.author,
                    referenceUrl = article.referenceUrl,
                    category = article.category
                )
                BodyArticle(bodyArticle = article.body)
            }

        }
    )

}