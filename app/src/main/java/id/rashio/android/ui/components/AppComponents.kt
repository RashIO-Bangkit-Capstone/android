package id.rashio.android.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun EmailFieldWithIcon(
    modifier: Modifier = Modifier,
    label: String,
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: ImageVector,
    contentDescription: String,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    var isEmailValid by remember { mutableStateOf(true) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        value = value,
        onValueChange = {
            onValueChange(it)
            isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
        },
        leadingIcon = {
            Icon(leadingIcon, contentDescription = contentDescription)
        },
        interactionSource = interactionSource,
        isError = !isEmailValid,
        supportingText = {
            if (!isEmailValid && value.isNotBlank()) {
                Text(text = "Email is not valid", color = MaterialTheme.colorScheme.error)
            }
        }
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
        )
    )
}

@Composable
fun RegisterPasswordTextField(
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

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

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
        interactionSource = interactionSource,
        isError = if (isFocused) passwordValue.length < 8 else false,
        supportingText = {
            if (passwordValue.length < 8 && passwordValue.isNotBlank()) {
                Text(
                    text = "Password must be at least 8 characters",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Composable
fun ConfirmPasswordTextField(
    confirmPassword: String,
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

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        singleLine = true,
        value = confirmPassword,
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
        interactionSource = interactionSource,
        isError = if (isFocused) confirmPassword != passwordValue else false,
        supportingText = {
            if (passwordValue != confirmPassword && confirmPassword.isNotBlank()) {
                Text(text = "Password does not match", color = MaterialTheme.colorScheme.error)
            }

        }
    )
}

