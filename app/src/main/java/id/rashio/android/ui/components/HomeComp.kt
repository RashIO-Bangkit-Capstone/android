package id.rashio.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
                    text = "Halo, Sobat RashIO",
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
                        text = "RashIO",
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
    Box(modifier = Modifier.background(Color(0XFFF4DFBA))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(40.dp, 40.dp, 0.dp, 0.dp))
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
                modifier = Modifier
                    .fillMaxSize()
                    .size(140.dp),
                painter = painterResource(id = R.drawable.banner_vector),
                contentDescription = null,

                )
        }
    }

}

@Composable
fun ItemFeature(image: Int, text: Int, destination: String, navController: NavController) {
    Box(
        modifier = Modifier
            .size(width = 88.dp, height = 90.dp)
            .clip(RoundedCornerShape(30.dp))
            .shadow(
                elevation = 25.dp,
                spotColor = Color.Black,
                shape = RoundedCornerShape(30.dp)
            )
            .background(Color(0XFFFCF5E7))
            .padding(8.dp)
            .clickable { navController.navigate(destination) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .weight(0.6f)
            )
            Text(
                text = stringResource(text),
                fontSize = 8.sp,
                lineHeight = 7.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                modifier = Modifier
                    .paddingFromBaseline(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .weight(0.4f),
            )
        }
    }
}

@Composable
fun HeadingText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ItemArticle(title: String, author: String, imageUrl: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f)
            )
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Row {
                    Text(
                        text = "by",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                    Text(
                        text = author,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
//            Icons.Outlined.BookmarkBorder
//            Image(
//                painter = painterResource(),
//                contentDescription = null,
//                modifier = Modifier
//                    .scale(2)
//                    .weight(1f)
//            )
            Image(
                painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                contentDescription = null,
                modifier = Modifier.weight(1f)
            )
        }


    }

}