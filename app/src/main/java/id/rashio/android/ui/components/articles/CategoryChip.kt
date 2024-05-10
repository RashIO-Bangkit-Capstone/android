package id.rashio.android.ui.components.articles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    text: String,
    selected: Boolean = false,
    onSelected: (Boolean) -> Unit
) {

    FilterChip(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        onClick = { onSelected(!selected) },
        selected = selected,
        label = { Text(text) }
    )
}