package id.rashio.android.ui.screen.detection

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import id.rashio.android.R
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.TopBarComp

@Composable
fun DetectionScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DetectionViewModel = hiltViewModel()
) {

    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.selectImage(uri)
        }


    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(
                title = "Detection",
                onBackClick = { navController.popBackStack() })
        },
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = BottomNavigationItem.getMenuBottomItems()
            )
        }, content = { innerPadding ->
            Column(modifier = Modifier.consumeWindowInsets(innerPadding)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val painter =
                        if (viewModel.imageUri != null) rememberAsyncImagePainter(model = viewModel.imageUri) else painterResource(
                            id = R.drawable.baseline_crop_original_24
                        )
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier.size(300.dp)
                    )
                    Text(
                        stringResource(R.string.desc_detection_inst),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                    Button(
                        onClick = { selectImageLauncher.launch("image/*") }, modifier = Modifier
                            .padding(top = 16.dp)
                            .width(250.dp)
                            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.extraLarge),
                        colors = buttonColors(
                            Color.White,
                            MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = stringResource(R.string.galeri))
                    }
                    Button(
                        onClick = {

                        }, modifier = Modifier
                            .padding(top = 8.dp)
                            .width(250.dp)
                            .border(1.dp, Color.Gray, shape = MaterialTheme.shapes.extraLarge),
                        colors = buttonColors(
                            Color.White,
                            MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = stringResource(R.string.camera))
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 36.dp)
                            .width(250.dp),
                    ) {
                        Text(text = stringResource(R.string.mulai_deteksi))
                    }

                }
            }
        })

}
