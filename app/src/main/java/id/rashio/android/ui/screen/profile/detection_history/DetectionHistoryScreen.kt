package id.rashio.android.ui.screen.profile.detection_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.components.profile.detection_history.HistoryCard

@Composable
fun DetectionHistoryScreen(
    navController: NavController,
    viewModel: DetectionHistoryViewModel = hiltViewModel(),
) {
    val detectionHistory by viewModel.historyDetection.collectAsState()

    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(
                title = "Detection History",
                onBackClick = { navController.popBackStack() })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0XFFF9F9F9))
                ) {
                    repeat(detectionHistory.size) {
                        val item = detectionHistory[it]
                        HistoryCard(item.imageUrl, item.result, item.percentage, item.createdAt)
                    }
                }
            }
        })

}