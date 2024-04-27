package id.rashio.android.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import id.rashio.android.R
import id.rashio.android.ui.components.PasswordTextField
import id.rashio.android.ui.components.TextFieldWithIcon
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun RegisterScreen(modifier: Modifier = Modifier, navController: NavController) {
    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var phoneValue by remember { mutableStateOf("") }


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

        Spacer(modifier = Modifier.height(24.dp))

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

        TextFieldWithIcon(
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

        Spacer(modifier = Modifier.height(16.dp))


        PasswordTextField(
            passwordValue = passwordValue,
            onPasswordChange = { passwordValue = it },
            passwordVisible = passwordVisible,
            onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
            label = stringResource(R.string.password),
            imeAction = ImeAction.Done,
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
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
                    navController.popBackStack()
                    navController.navigate("Login")
                }
            )
        }

    }
}