package id.rashio.android.ui.screen.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import id.rashio.android.R
import id.rashio.android.ui.components.HeadingText
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.components.articles.CategoryChip
import id.rashio.android.ui.components.home.ArticleCard
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun ArticlesScreen(
    navController: NavController,
    viewModel: ArticlesViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {

    val articles by viewModel.articles.collectAsState()

    val categories =
        listOf("Umum", "Wajah", "Ruam", "Makanan", "Gatal", "Obat", "Alternatif", "Anak")
    var selectedCategory by remember { mutableStateOf("Umum") }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))


    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(
                title = "Article",
                onBackClick = { navController.popBackStack() })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                HeadingText(
                    text = "Categories",
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    categories.forEach { category ->
                        CategoryChip(
                            text = category,
                            selected = category == selectedCategory,
                            onSelected = { isSelected ->
                                if (isSelected) {
                                    selectedCategory = category
                                } else {
                                    selectedCategory = "Umum"
                                }
                            }
                        )
                    }
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    val filteredArticles = articles.filter { it.category == selectedCategory }

                    if (filteredArticles.isEmpty()) {
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
                            repeat(filteredArticles.size) {
                                val article = filteredArticles[it]
                                ArticleCard(
                                    bookmarkableArticle = article,
                                    onBookmarkClick = viewModel::bookmarkArticle,
                                    onArticleClick = {
                                        navigateToDetail(article.articleId)
                                    }
                                )
                            }
                        }
                    }

                }
            }
        })

}