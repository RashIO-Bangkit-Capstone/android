package id.rashio.android

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import id.rashio.android.ui.screen.login.LoginScreen
import id.rashio.android.ui.screen.register.RegisterScreen
import id.rashio.android.ui.screen.splash_screen.SplashScreen
import id.rashio.android.ui.theme.AppTheme

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

                    NavHost(
                        navController = navController,
                        startDestination = "Splash",
                        ){
                        composable("Splash"){
                            SplashScreen(navController = navController)
                        }
                        composable("Login"){
                            LoginScreen(
                                navController = navController,

                            )

                        }
                        composable("Register"){
                            RegisterScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
