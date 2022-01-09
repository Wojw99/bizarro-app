package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.Dimens

val verticalPadding = Dimens.standardPadding
val horizontalPadding = Dimens.standardPadding * 2

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthenticateViewModel = hiltViewModel(),
) {
    viewModel.appState.hideBottomMenu()

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Box{
            // * * * * * BODY * * * * *
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                Text(
                    "Zapisz się do Bizarro!",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(80.dp))

                RegisterFields(navController)
            }

            // * * * * * * ERROR DIALOG * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                ConfirmAlertDialog(
                    onDismiss = { viewModel.clearError() },
                    title = Strings.error,
                    body = viewModel.loadError.value,
                )
            }

            // * * * * * * SUCCESS * * * * * *
            if (viewModel.successfullyLogin.value) {
                viewModel.successfullyLogin.value = false
                navController.popBackStack()
                navController.navigate(Screen.UserRecordList.route)
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                LoadingBox()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun RegisterFields(
    navController: NavController,
    viewModel: AuthenticateViewModel = hiltViewModel()
) {
    CustomOutlinedTextField(
        value = viewModel.emailRegisterText.value,
        onValueChange = {
            viewModel.emailRegisterText.value = it
        },
        placeholderText = "Podaj swój email",
        labelText = "Email",
        keyboardType = KeyboardType.Text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email, contentDescription = "EmailIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomOutlinedTextField(
        value = viewModel.passwordRegisterText.value,
        onValueChange = {
            viewModel.passwordRegisterText.value = it
        },
        placeholderText = "Podaj swoje hasło",
        labelText = "Hasło",
        keyboardType = KeyboardType.Password,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
        visualTransformation = PasswordVisualTransformation(),
    )

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick = {
            viewModel.register()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    )
    {
        Text(
            text = "Zarejestruj",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    )
    {
        Text(
            text = "Logowanie",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }
}