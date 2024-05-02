package id.rashio.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.rashio.android.data.model.listFeatures
import id.rashio.android.ui.components.BannerHome
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.Greetings
import id.rashio.android.ui.components.HeadingText
import id.rashio.android.ui.components.ItemArticle
import id.rashio.android.ui.components.ItemFeature


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val articles by viewModel.articles.collectAsState()

    Scaffold(modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = BottomNavigationItem.getMenuBottomItems()
            )
        }, content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(state = rememberScrollState())
                    .background(Color.White)) {
                        Greetings()
                        BannerHome()
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = modifier.padding(16.dp)
                        ) {
                            items(listFeatures, key = { it.textFeature }) { feature ->
                                ItemFeature(
                                    image = feature.imageFeature,
                                    text = feature.textFeature,
                                    destination = feature.destination,
                                    navController = navController
                                )
                            }
                        }
                        HeadingText(text = "Artikel Terkini")
                        repeat(articles.size) {
                            val article = articles[it]
                            ItemArticle(
                                title = article.title,
                                imageUrl = article.imageUrl,
                                author = article.author,
                            )
                        }
                    }
        })
}