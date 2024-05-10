package id.rashio.android.ui.components.articles.article_detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ArticleInfo(author: String, referenceUrl: String, category: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Kategori: $category",
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
        )
        Text(
            text = "Referensi: $author",
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
        )
        Text(
            text = "Referensi",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(referenceUrl))
                context.startActivity(intent)
            })
    }
}