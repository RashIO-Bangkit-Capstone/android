package id.rashio.android.ui.screen.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.SettingsClient
import id.rashio.android.R
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.HeadingText
import id.rashio.android.ui.components.home.ArticleCard
import id.rashio.android.ui.components.home.BannerHome
import id.rashio.android.ui.components.home.Greetings
import id.rashio.android.ui.components.home.ItemFeature

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    activity: Activity,
    navigateToDetail: (Int) -> Unit,
    navigateToIdentify: () -> Unit,
    navigateToArticle: () -> Unit,
    navigateToHistory: () -> Unit
) {
    val articles by viewModel.articles.collectAsState()
    val userData by viewModel.userData.collectAsState()

    val accessLocation = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )
    val snackbarHostState = remember { SnackbarHostState() }

    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val settingsClient: SettingsClient = LocationServices.getSettingsClient(activity)

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.loadWeatherInfo()
            }
        }

    LaunchedEffect(key1 = Unit) {
        accessLocation.launchMultiplePermissionRequest()
    }

    LaunchedEffect(key1 = accessLocation) {
        if (accessLocation.allPermissionsGranted) {
            viewModel.checkLocationSettings(settingsClient, locationRequest, startForResult::launch)
        } else {
            snackbarHostState.showSnackbar("Location permissions not granted")
        }
    }


    Scaffold(modifier = Modifier.fillMaxWidth(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                    .background(Color.White)
            ) {
                Greetings(viewModel.state, userData.name)
                BannerHome()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(16.dp)
                )
                {
                    ItemFeature(
                        image = R.drawable.identify_skin_icon,
                        text = R.string.identify_skin,
                        onClick = {
                            navigateToIdentify()
                        })
                    ItemFeature(
                        image = R.drawable.articles_icon,
                        text = R.string.articles,
                        onClick = {
                            navigateToArticle()
                        })
                    ItemFeature(
                        image = R.drawable.detection_history_icon,
                        text = R.string.detection_history,
                        onClick = {
                            navigateToHistory()
                        })
                    ItemFeature(
                        image = R.drawable.derma_icon,
                        text = R.string.derma,
                        onClick = {
                            val gmmIntentUri = Uri.parse("geo:0,0?q=dermatology+terdekat")
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            mapIntent.setPackage("com.google.android.apps.maps")
                                activity.startActivity(mapIntent)
                        }
                    )
                }
                HeadingText(text = "Artikel Terkini", modifier = Modifier.padding(16.dp))
                repeat(articles.size) {
                    val article = articles[it]
                    ArticleCard(
                        bookmarkableArticle = article,
                        onBookmarkClick = viewModel::bookmarkArticle,
                        onArticleClick = {
                            navigateToDetail(article.articleId)
                        }
                    )
                }
            }
        })
}