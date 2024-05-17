package id.rashio.android.ui.screen.profile.detection_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import id.rashio.android.ui.components.profile.detection_history.HistoryCard
import id.rashio.android.ui.theme.poppinsFontFamily
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DetectionHistoryScreen(
    navController: NavController,
    viewModel: DetectionHistoryViewModel = hiltViewModel(),
) {
    val detectionHistory by viewModel.historyDetection.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_found))


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
                    .verticalScroll(rememberScrollState())
            ) {
                if (detectionHistory.isEmpty()) {
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
                            text = "No history found",
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
                            .background(Color(0XFFF9F9F9))
                    ) {
                        repeat(detectionHistory.size) {
                            val item = detectionHistory[it]
                            HistoryCard(
                                item.imageUrl,
                                item.result,
                                item.percentage,
                                item.createdAt
                            ) { result, percentage, imageUrl ->
                                val encodedUrl =
                                    URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString())
                                navController.navigate("DetectionResult/${result}/$${percentage}/${encodedUrl}")
                            }
                        }
                    }
                }
            }
        })
}
