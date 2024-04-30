package id.rashio.android.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = BottomNavigationItem.getMenuBottomItems()
            )
        }, content = { innerPadding ->
            Column(modifier = Modifier.consumeWindowInsets(innerPadding)) {
                Text(text = "Profile Screen")
            }
        })
}