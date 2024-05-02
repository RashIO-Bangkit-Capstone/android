package id.rashio.android.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.rashio.android.data.model.listFeatures
import id.rashio.android.ui.components.BannerHome
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.Greetings
import id.rashio.android.ui.components.ItemFeature


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = BottomNavigationItem.getMenuBottomItems()
            )
        }, content = { innerPadding ->
            Column(modifier = Modifier.consumeWindowInsets(innerPadding)) {
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
                Column {
                    Text("Ini Space aja")
                }
            }
        })
}