package id.rashio.android.ui.screen.identify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import id.rashio.android.ui.components.TopBarComp


@Composable
fun IdentifyScreem(
    navController: NavController,
) {
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
                Text(text = "Identify Screen")
            }
        })

}