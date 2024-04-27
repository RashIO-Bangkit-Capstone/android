package id.rashio.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    contentDescription: String,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(leadingIcon, contentDescription = contentDescription)
        },
    )
}

@Composable
fun PasswordTextField(
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit,
    label: String,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
) {
    val icon = if (passwordVisible)
        Icons.Filled.Visibility
    else
        Icons.Filled.VisibilityOff

    val visualTransformation = if (passwordVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        singleLine = true,
        value = passwordValue,
        onValueChange = onPasswordChange,
        leadingIcon = {
            Icon(Icons.Rounded.Lock, contentDescription = "Lock Icon")
        },
        trailingIcon = {
            IconButton(onClick = onPasswordVisibilityToggle) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(
            onDone = {
                localFocusManager.clearFocus()
            }
        ),
    )
}

