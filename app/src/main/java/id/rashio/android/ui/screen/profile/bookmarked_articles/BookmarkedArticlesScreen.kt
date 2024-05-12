package id.rashio.android.ui.screen.profile.bookmarked_articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import id.rashio.android.R
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.components.home.ArticleCard
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun BookmarkedArticlesScreen(
    navController: NavController,
    viewModel: BookmarkedArticleViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {

    val bookmarkedArticle by viewModel.bookmarkedArticle.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))


    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(
                title = "Bookmark Articles",
                onBackClick = { navController.popBackStack() })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                if (bookmarkedArticle.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever
                        )
                        Text(
                            text = "No articles found",
                            color = Color.Gray,
                            fontFamily = poppinsFontFamily,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {
                        repeat(bookmarkedArticle.size) {
                            val article = bookmarkedArticle[it]
                            ArticleCard(
                                bookmarkableArticle = article,
                                onBookmarkClick = {
                                    viewModel.bookmarkArticle(article)
                                },
                                onArticleClick = {
                                    navigateToDetail(article.articleId)
                                }
                            )
                        }
                    }
                }
            }
        })

}