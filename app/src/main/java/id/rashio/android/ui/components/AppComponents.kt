package id.rashio.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun AuthTextField(labelValue: String, icon: ImageVector) {

    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(labelValue) },
        value = textValue,
        onValueChange = {
            textValue = it
        },
        leadingIcon = {
                Icon(icon, contentDescription ="" )
        }
    )

}