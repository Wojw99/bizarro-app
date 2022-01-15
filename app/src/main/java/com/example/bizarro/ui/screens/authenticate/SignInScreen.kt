package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.bizarro.ui.screens.add_record.textFieldModifier
import com.example.bizarro.ui.theme.*
import com.example.bizarro.ui.theme.kGray
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
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Box {
            // * * * * * * BODY * * * * * *
            SignInScreenBody(navController)

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
fun SignInScreenBody(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthenticateViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = Dimens.standardPadding * 2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val spacerHeight = 16.dp

        Text(
            Strings.welcomeToBizarro,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(spacerHeight * 4))

        CustomOutlinedTextField(
            value = viewModel.userNameLoginText.value,
            onValueChange = { viewModel.userNameLoginText.value = it },
            labelText = Strings.username,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = "person icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        CustomOutlinedTextField(
            value = viewModel.passwordLoginText.value,
            onValueChange = { viewModel.passwordLoginText.value = it },
            labelText = Strings.passwordRepeat,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, contentDescription = "lock icon",
                    tint = MaterialTheme.colors.onSurface
                )
            },
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(spacerHeight * 2))

        // * * * * * ACCEPT BUTTON * * * * *
        Button(
            onClick = {
                viewModel.login()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.onSurface),
        ) {
            Text(text = Strings.login, color = colors.surface)
        }

        Spacer(modifier = Modifier.height(spacerHeight * 6))

        // * * * * * GO TO REGISTER BUTTON * * * * *
        Button(
            onClick = {
                navController.navigate(route = Screen.SignUp.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = kLightGray),
        ) {
            Text(text = Strings.goToRegister, color = kBlack)
        }

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * PASSWORD RESET * * * * *
        Button(
            onClick = {
                navController.navigate(route = Screen.PasswordResetScreen.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = kLightGray),
        ) {
            Text(text = Strings.passwordDontRemember, color = kBlack)
        }
    }
}