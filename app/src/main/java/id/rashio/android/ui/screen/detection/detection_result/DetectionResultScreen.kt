package id.rashio.android.ui.screen.detection.detection_result

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import id.rashio.android.ui.components.TopBarComp

@Composable
fun DetectionResultScreen(
    navController: NavController,
    viewModel: DetectionResultViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState().value


    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(title = "Hasil Deteksi",
                onBackClick = { navController.popBackStack() },
                actions = listOf {
                    Text(
                        text = "Selesai",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { navigateToHome() }
                    )
                })
        }, content = { innerPadding ->
            when (uiState) {
                is DiseaseUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is DiseaseUiState.Success -> {
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFF9F9F9))
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(300.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center

                            ) {
                                AsyncImage(
                                    model = uiState.imageUrl,
                                    contentDescription = "Result Image",
                                    modifier = Modifier
                                        .height(200.dp)
                                        .width(282.dp)
                                        .padding(16.dp)
                                )
                                Text(
                                    text = uiState.diseaseName,
                                    fontSize = 18.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                )

                                Text(
                                    text = "Confidence :",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 16.dp)
                                )
                                CircularProgessBar(percentage = uiState.percentage, number = 100)
                                Spacer(
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(
                                modifier = Modifier.size(5.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp),
                            horizontalAlignment = Alignment.Start,
                        ) {
                            Text(
                                text = "Deskripsi",
                                fontSize = 16.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                            )
                            Text(
                                text = uiState.diseaseDescription,
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            Text(
                                text = "Penanganan Awal",
                                fontSize = 16.sp,
                                color = Color.Black,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                            )
                            Text(
                                text = uiState.diseaseTreatment,
                                fontSize = 14.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Justify,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                        }

                    }
                }

                is DiseaseUiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = uiState.message)
                    }
                }
            }
        })
}

@Composable
fun CircularProgessBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color(0XFFCA965C),
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f, animationSpec = tween(
            durationMillis = animDuration, delayMillis = animDelay
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = "${(curPercentage.value * number).toInt()}%",
            color = Color(0XFF876445),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}