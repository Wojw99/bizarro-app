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
import androidx.navigation.NavController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@ExperimentalComposeUiApi
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: AuthenticateViewModel = hiltViewModel(),
) {
    viewModel.appState.hideBottomMenu()

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Box {
            // * * * * * * BODY * * * * * *
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(100.dp))

                Text(
                    "Witaj w Bizarro!",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.height(80.dp))

                LoginFields(navController)
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
                ConfirmAlertDialog(
                    onDismiss = { navController.navigate(Screen.UserRecordList.route) },
                    title = Strings.success2,
                    body = Strings.confirmLogin,
                )
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
fun LoginFields(
    navController: NavController,
    viewModel: AuthenticateViewModel = hiltViewModel()
) {
    CustomOutlinedTextField(
        value = viewModel.emailLoginText.value,
        onValueChange = {
            viewModel.emailLoginText.value = it
        },
        placeholderText = "Podaj swój email",
        labelText = "Email",
        keyboardType = KeyboardType.Text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email, contentDescription = "EmailIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
       modifier = Modifier
          .fillMaxWidth()
          .padding(Dimens.standardPadding),
    )

    Spacer(modifier = Modifier.height(20.dp))

    CustomOutlinedTextField(
        value = viewModel.passwordLoginText.value,
        onValueChange = {
            viewModel.passwordLoginText.value = it
        },
        placeholderText = "Podaj swoje hasło",
        labelText = "Hasło",
        keyboardType = KeyboardType.Password,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
       modifier = Modifier
          .fillMaxWidth()
          .padding(Dimens.standardPadding),
        visualTransformation = PasswordVisualTransformation(),
    )

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick = { viewModel.login() },
        Modifier
           .size(width = 250.dp, height = 50.dp)
           .fillMaxWidth()
           .padding(Dimens.standardPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    ) {
        Text(
            text = "Zaloguj",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }

   Spacer(modifier = Modifier.height(10.dp))

    Text(
        "ALBO",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface
        )
    )

    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            navController.navigate(route = Screen.SignUp.route)
        },
        Modifier
           .size(width = 250.dp, height = 50.dp)
           .fillMaxWidth()
           .padding(Dimens.standardPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    )
    {
        Text(
            text = "Zarejestruj",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }
}