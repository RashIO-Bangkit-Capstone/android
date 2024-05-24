package id.rashio.android.ui.screen.profile.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.rashio.android.R
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopBarComp(
                title = stringResource(R.string.tentang_aplikasi),
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
                        .padding(top = 21.dp, start = 16.dp, end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rashio_logo),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = "RashIO",
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(180.dp)
                            .height(1.dp)
                            .border(width = 1.dp, color = Color.Gray)
                            .alpha(0.2f)
                    )
                    Text(
                        text = stringResource(id = R.string.tentang_desc),
                        fontFamily = poppinsFontFamily,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Text(
                        text = "”Stay Informed, Take Action.\n" +
                                "Detection and Prevention in One Place!”",
                        fontFamily = poppinsFontFamily,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )

                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(
                            text = "visit us at ",
                            fontFamily = poppinsFontFamily,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        val context = LocalContext.current

                        Text(
                            text = "rashio.tech",
                            fontFamily = poppinsFontFamily,
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.clickable {
                                val intent =
                                    Intent(Intent.ACTION_VIEW, Uri.parse("https://rashio.tech"))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        })

}