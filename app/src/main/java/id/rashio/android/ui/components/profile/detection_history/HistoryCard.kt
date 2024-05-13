package id.rashio.android.ui.components.profile.detection_history

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("ConstantLocale")
val inputFormat =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

@SuppressLint("ConstantLocale")
val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

@Composable
fun HistoryCard(imageUrl: String, result: String, percentage: Float, createdAt: String) {

    val date = inputFormat.parse(createdAt)
    val formattedDate = outputFormat.format(date!!)

    val decimalFormat = DecimalFormat("#.00")
    val formattedPercentage = decimalFormat.format(percentage * 100)


    Card(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .height(125.dp)
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {

        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
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
                        .padding(start = 16.dp)
                ) {
                    Text(text = result, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    MultiStyleText(
                        text1 = "Akurasi: ",
                        color1 = Color(0XFFCA965C),
                        text2 = "$formattedPercentage%",
                        color2 = Color(0XFF876445)
                    )
                }

                Text(
                    text = formattedDate,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.Bottom)
                )

            }
        }

    }

}

@Composable
fun MultiStyleText(text1: String, color1: Color, text2: String, color2: Color) {
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(color = color1)) {
            append(text1)
        }
        withStyle(style = SpanStyle(color = color2, fontWeight = FontWeight.Bold)) {
            append(text2)
        }
    }, fontSize = 11.sp)
}
