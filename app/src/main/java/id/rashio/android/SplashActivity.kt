package id.rashio.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import id.rashio.android.ui.theme.AppTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            AppTheme {
                SplashScreen()
            }
        }
    }

    @Preview
    @Composable
    private fun SplashScreen() {
        val linear = Brush.linearGradient(
            listOf(
                Color.hsl(0f,0f,98f),
                Color.hsl(32f,51f,58f)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(linear),
            contentAlignment = Alignment.Center
        ) {

        }
    }

}