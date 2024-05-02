package id.rashio.android.ui.screen.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import id.rashio.android.R
import id.rashio.android.ui.components.ConfirmPasswordTextField
import id.rashio.android.ui.components.EmailFieldWithIcon
import id.rashio.android.ui.components.RegisterPasswordTextField
import id.rashio.android.ui.components.TextFieldWithIcon
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun RegisterScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var phoneValue by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val registerButtonEnabled by remember { derivedStateOf { (passwordValue == confirmPassword) and (passwordValue.isNotBlank() and confirmPassword.isNotBlank()) } }

    val uiState = viewModel.uiState.collectAsState().value
    when (uiState) {
        is RegisterUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Show loading
                CircularProgressIndicator()
            }
        }

        is RegisterUiState.Success -> {
            // Navigate to home
            LaunchedEffect(key1 = uiState.authenticated) {
                if (uiState.authenticated) {
                    navigateBack()
                }
            }

        }

        is RegisterUiState.Error -> {
            // Show error
            val context = LocalContext.current
            val message = uiState.message
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.register),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp
        )
        Text(
            text = stringResource(R.string.register_desc),
            color = MaterialTheme.colorScheme.primary,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.vector_register),
            contentDescription = "Register Vector",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextFieldWithIcon(
            label = stringResource(R.string.nama),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            value = nameValue,
            onValueChange = { nameValue = it },
            leadingIcon = Icons.Rounded.Person,
            contentDescription = "Person Icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldWithIcon(
            label = stringResource(R.string.no_hp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            value = phoneValue,
            onValueChange = { phoneValue = it },
            leadingIcon = Icons.Rounded.Phone,
            contentDescription = "Phone Icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        EmailFieldWithIcon(
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            value = emailValue,
            onValueChange = { emailValue = it },
            leadingIcon = Icons.Rounded.Email,
            contentDescription = "Email Icon",
        )

        RegisterPasswordTextField(
            passwordValue = passwordValue,
            onPasswordChange = { passwordValue = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
            label = stringResource(R.string.password),
            imeAction = ImeAction.Next,
        )

        ConfirmPasswordTextField(
            confirmPassword = confirmPassword,
            passwordValue = passwordValue,
            onPasswordChange = { confirmPassword = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
            label = stringResource(R.string.confirm_password),
            imeAction = ImeAction.Done,
        )


        Button(
            onClick = {
                viewModel.register(
                    name = nameValue,
                    email = emailValue,
                    phoneNumber = phoneValue,
                    password = passwordValue,
                    confirmPassword = confirmPassword
                )
            },
            enabled = registerButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.register))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row {
            Text(
                text = stringResource(R.string.login_button_desc),
                color = Color.Black,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.login),
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.clickable {

                    navigateBack()
                }
            )
        }

    }
}