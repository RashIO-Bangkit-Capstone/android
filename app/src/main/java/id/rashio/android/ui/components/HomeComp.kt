package id.rashio.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.rashio.android.R
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun Greetings() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(64.dp)
            .background(Color(0XFFF4DFBA))
            .padding(16.dp)
    ) {
        Text(
            text = "Today Details",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Image(
                painter = painterResource(id = R.drawable.weather_example),
                contentDescription = null,
                Modifier.size(140.dp)
            )
            Column {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.wind_icon),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.humidity_icon),
                        contentDescription = null
                    )
                }
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.uv_icon),
                        contentDescription = null
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.temp_icon),
                        contentDescription = null
                    )
                }

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Halo, Sobat Rashio",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                )

                Row(modifier = Modifier) {
                    Text(
                        text = "Selamat datang di Aplikasi ",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                    )
                    Text(
                        text = "Rashio",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFontFamily,
                    )

                }
            }

            Image(
                painter = painterResource(id = R.drawable.rashio_logo),
                contentDescription = "RashIO Logo",
                modifier = Modifier.size(80.dp)
            )

        }
    }
}

@Composable
fun BannerHome() {
    Box(modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .background(
                    Color(0XFFFCF5E7)
                )
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                modifier = Modifier.padding(24.dp),
                text = "Stay\r\nInformed,\r\nTake Action!",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
            )

            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.banner_vector),
                contentDescription = null,

                )
        }
    }

}