package id.rashio.android

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.rashio.android.ui.screen.articles.ArticlesScreen
import id.rashio.android.ui.screen.articles.article_detail.ArticleDetailScreen
import id.rashio.android.ui.screen.auth.login.LoginScreen
import id.rashio.android.ui.screen.auth.register.RegisterScreen
import id.rashio.android.ui.screen.detection.DetectionScreen
import id.rashio.android.ui.screen.home.HomeScreen
import id.rashio.android.ui.screen.profile.ProfileScreen
import id.rashio.android.ui.screen.splash_screen.SplashScreen
import id.rashio.android.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    val authState = mainViewModel.authState.collectAsState()

                    LaunchedEffect(key1 = authState.value) {
                        when (authState.value) {
                            AuthenticationState.Authenticated ->
                                navController.navigate("Home") {
                                    popUpTo("Login") {
                                        inclusive = true
                                    }
                                }

                            AuthenticationState.Unauthenticated ->
                                navController.navigate("Login") {
                                    popUpTo("Splash") {
                                        inclusive = true
                                    }
                                }

                            AuthenticationState.Unknown -> {
                                // Do nothing
                            }
                        }
                    }

                    NavHost(
                        navController = navController,
                        startDestination = "Splash",
                    ) {
                        composable("Splash") {
                            SplashScreen()
                        }
                        composable("Login",
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideOutOfContainer(
                                    animationSpec = tween(300, easing = EaseIn),
                                    towards = AnimatedContentTransitionScope.SlideDirection.End
                                )
                            }
                        ) {
                            LoginScreen(navigateToRegister = {
                                navController.navigate("Register")
                            })
                        }
                        composable("Register",
                            enterTransition = {
                                fadeIn(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                ) + slideIntoContainer(
                                    animationSpec = tween(300, easing = EaseOut),
                                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                                )
                            },
                            exitTransition = {
                                fadeOut(
                                    animationSpec = tween(
                                        300, easing = LinearEasing
                                    )
                                )
                            }
                        ) {
                            RegisterScreen(navigateBack = navController::navigateUp)
                        }
                        composable("Home") {
                            val context = LocalContext.current
                            val activity = context as? Activity
                            if (activity != null) {
                                HomeScreen(
                                    navController = navController,
                                    activity = activity,
                                    navigateToDetail = { articleId ->
                                        navController.navigate("DetailArticle/$articleId")
                                    })
                            }
                        }
                        composable("Detection") {
                            DetectionScreen(navController = navController)
                        }
                        composable("Profile") {
                            ProfileScreen(navController = navController)
                        }
                        composable("Articles") {
                            ArticlesScreen(
                                navController = navController,
                                navigateToDetail = { articleId ->
                                    navController.navigate("DetailArticle/$articleId")
                                })
                        }
                        composable("DetailArticle/{articleId}") {
                            ArticleDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
