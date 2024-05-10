package id.rashio.android.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import id.rashio.android.R
import id.rashio.android.data.model.BookmarkableArticle
import id.rashio.android.ui.screen.home.WeatherState
import id.rashio.android.ui.theme.poppinsFontFamily
import kotlin.math.roundToInt

@Composable
fun WeatherDataDetail(
    value: Int,
    unit: String,
    icon: ImageVector,
    desc: String,
    modifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.primary
) {

    Row(horizontalArrangement = Arrangement.Start, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
        )
        Text(
            text = "$value $unit\n$desc",
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 10.sp,
            lineHeight = 11.sp,
            modifier = Modifier.padding(start = 4.dp)
        )

    }

}

@Composable
fun WeatherData(state: WeatherState, modifier: Modifier = Modifier) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(135.dp)
                )
            }
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.widthIn(60.dp),
                ) {
                    WeatherDataDetail(
                        value = data.windSpeed.roundToInt(),
                        unit = "Kmh",
                        icon = ImageVector.vectorResource(id = R.drawable.wind_icon),
                        desc = "Wind"
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    WeatherDataDetail(
                        value = data.humidity.roundToInt(),
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.humidity_icon),
                        desc = "Humidity"
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.widthIn(60.dp)
                ) {
                    WeatherDataDetail(
                        value = data.temperatureCelsius.roundToInt(),
                        unit = "°C",
                        icon = ImageVector.vectorResource(id = R.drawable.temp_icon),
                        desc = "Temp"
                    )
                    Spacer(modifier = Modifier.size(24.dp))

                    WeatherDataDetail(
                        value = data.pressure.roundToInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.wind_pressure),
                        desc = "Pressure"
                    )
                }
            }

        }
    }

}

@Composable
fun Greetings(state: WeatherState, name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(64.dp)
            .background(Color(0XFFF4DFBA))
            .padding(16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = "Today Details",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
            )
            WeatherData(state = state)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Halo, ${name}",
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
fun ItemFeature(image: Int, text: Int, onClick:  () -> Unit) {
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
            .clickable { onClick() }
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
fun ArticleCard(
    bookmarkableArticle: BookmarkableArticle,
    onBookmarkClick: (BookmarkableArticle) -> Unit,
    onArticleClick: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .clickable { onArticleClick(bookmarkableArticle.articleId) }
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            AsyncImage(
                model = bookmarkableArticle.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = bookmarkableArticle.title,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
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
                        text = bookmarkableArticle.author,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            IconButton(onClick = { onBookmarkClick(bookmarkableArticle) }) {
                if (bookmarkableArticle.isBookmarked) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }


        }


    }

}