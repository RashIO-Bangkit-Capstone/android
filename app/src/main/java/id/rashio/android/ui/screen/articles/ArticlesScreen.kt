package id.rashio.android.ui.screen.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ArticlesScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        content = { innerPadding ->
            Column(modifier = Modifier.consumeWindowInsets(innerPadding)) {

                Text(text = "Articles Screen")
            }
        })

}