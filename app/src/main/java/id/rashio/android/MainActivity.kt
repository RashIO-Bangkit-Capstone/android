package id.rashio.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.rashio.android.ui.screen.auth.login.LoginScreen
import id.rashio.android.ui.screen.auth.register.RegisterScreen
import id.rashio.android.ui.screen.home.HomeScreen
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
                                navController.navigate("Home")

                            AuthenticationState.Unauthenticated ->
                                navController.navigate("Login")

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
                            SplashScreen(navController = navController)
                        }
                        composable("Login") {
                            LoginScreen(navigateToRegister = {
                                navController.navigate("Register")
                            }, onLogin = { email, password ->
                                mainViewModel.login(email, password)
                            })
                        }
                        composable("Register") {
                            RegisterScreen(navController = navController)
                        }
                        composable("Home") {
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
