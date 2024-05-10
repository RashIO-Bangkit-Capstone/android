package id.rashio.android.ui.components.articles.article_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BodyArticle(bodyArticle: List<String>) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp,
        )
    ) {
        bodyArticle.forEach {
            Text(
                text = it,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(
                    bottom = 16.dp
                )
            )
        }
    }

}