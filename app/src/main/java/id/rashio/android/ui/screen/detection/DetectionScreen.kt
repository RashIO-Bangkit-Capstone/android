package id.rashio.android.ui.screen.detection

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import id.rashio.android.R
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.theme.poppinsFontFamily
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Objects

@Composable
fun DetectionScreen(
    navController: NavController,
    viewModel: DetectionViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val selectImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                viewModel.imageDetection = getFileFromUri(uri, context)
            }
        }

    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            viewModel.imageDetection = file
        }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = uiState) {

        if (uiState is DetectionUiState.Success) {
            val successUiState = uiState as DetectionUiState.Success
            val encodedUrl =
                URLEncoder.encode(successUiState.imageUrl, StandardCharsets.UTF_8.toString())
            navController.navigate("DetectionResult/${successUiState.nameDisease}/$${successUiState.percentage}/${encodedUrl}")
        }
        if (uiState is DetectionUiState.Error) {

            Toast.makeText(context, (uiState as DetectionUiState.Error).message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.not_detected))


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
            when (uiState) {
                DetectionUiState.Empty,
                is DetectionUiState.Success -> {
                    Column(modifier = Modifier.consumeWindowInsets(innerPadding)) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            val painter =
                                if (viewModel.imageDetection != null) rememberAsyncImagePainter(
                                    model = viewModel.imageDetection
                                ) else painterResource(
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
                                onClick = { selectImageLauncher.launch("image/*") },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .width(250.dp)
                                    .border(
                                        1.dp,
                                        Color.Gray,
                                        shape = MaterialTheme.shapes.extraLarge
                                    ),
                                colors = buttonColors(
                                    Color.White,
                                    MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = stringResource(R.string.galeri))
                            }
                            Button(
                                onClick = {
                                    cameraLauncher.launch(uri)
                                }, modifier = Modifier
                                    .padding(top = 8.dp)
                                    .width(250.dp)
                                    .border(
                                        1.dp,
                                        Color.Gray,
                                        shape = MaterialTheme.shapes.extraLarge
                                    ),
                                colors = buttonColors(
                                    Color.White,
                                    MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = stringResource(R.string.camera))
                            }

                            Spacer(modifier = Modifier.size(16.dp))
                            Button(
                                onClick = {
                                    viewModel.predictImage()
                                },
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 36.dp)
                                    .width(250.dp),
                                enabled = viewModel.imageDetection != null
                            ) {
                                Text(text = stringResource(R.string.mulai_deteksi))
                            }

                        }
                    }
                }

                is DetectionUiState.Error -> {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Whoops!",
                            fontFamily = poppinsFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )

                        LottieAnimation(
                            composition = composition,
                            iterations = LottieConstants.IterateForever
                        )

                        Text(
                            text = "We can't detect your image",
                            fontFamily = poppinsFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 12.dp)
                        )

                        Text(
                            text = "Please check your image and try again",
                            fontFamily = poppinsFontFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Text(text = "Okay")
                        }

                    }

                }

                DetectionUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        })

}

@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

fun getFileFromUri(uri: Uri, context: Context): File {
    val timeStamp: String = SimpleDateFormat(
        FILENAME_FORMAT,
        Locale.US
    ).format(System.currentTimeMillis())
    val contentResolver: ContentResolver = context.contentResolver

    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val photoFile: File = File.createTempFile(timeStamp, ".jpg", storageDir)

    val inputStream = contentResolver.openInputStream(uri) as InputStream
    val outputStream: OutputStream = FileOutputStream(photoFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)

    outputStream.close()
    inputStream.close()

    return photoFile
}

private const val FILENAME_FORMAT = "yyyy-MM-dd:HH-mm-ss-SSS"
