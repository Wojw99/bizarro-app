package com.example.bizarro.ui.screens.password_reset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.add_record.textFieldModifier
import com.example.bizarro.ui.screens.authenticate.AuthenticateViewModel
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@ExperimentalComposeUiApi
@Composable
fun PasswordResetScreen(
    navController: NavController,
    viewModel: PasswordResetViewModel = hiltViewModel(),
) {
    viewModel.appState.hideBottomMenu()

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Box {
            // * * * * * * BODY * * * * * *
            if(viewModel.isResetRequestSent.value) {
                ResetPasswordCodeBody(navController)
            } else {
                ResetPasswordBody(navController)
            }

            // * * * * * * TOP BAR * * * * * *
            TopBar(
                navController = navController,
                modifier = Modifier
                    .background(colors.background)
                    .align(Alignment.TopCenter),
            )

            // * * * * * * ERROR DIALOG * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                ConfirmAlertDialog(
                    onDismiss = { viewModel.clearError() },
                    title = Strings.error,
                    body = viewModel.loadError.value,
                )
            }

            // * * * * * * SUCCESS * * * * * *
            if (viewModel.isSuccess.value) {
                viewModel.isSuccess.value = false
                navController.popBackStack()
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
fun ResetPasswordCodeBody(
    navController: NavController,
    viewModel: PasswordResetViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(horizontal = Dimens.standardPadding * 2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val spacerHeight = 16.dp

        // * * * * * INFO * * * * *
        Text(text = Strings.passwordCodeInfo, color = colors.onSurface)

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * CODE * * * * *
        CustomOutlinedTextField(
            value = viewModel.code.value,
            onValueChange = {  viewModel.code.value = it },
            labelText = Strings.code,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * PASSWORD * * * * *
        CustomOutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            labelText = Strings.password,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock, contentDescription = "lock icon",
                    tint = colors.onSurface
                )
            },
            visualTransformation = PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * PASSWORD REPEAT * * * * *
        CustomOutlinedTextField(
            value = viewModel.passwordRepeat.value,
            onValueChange = { viewModel.passwordRepeat.value = it },
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

        Spacer(modifier = Modifier.height(spacerHeight * 6))

        // * * * * * RESET PASSWORD * * * * *
        Button(
            onClick = {
                viewModel.resetPassword()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.onSurface),
        ) {
            Text(text = Strings.confirm, color = kWhite)
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ResetPasswordBody(
    navController: NavController,
    viewModel: PasswordResetViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(horizontal = Dimens.standardPadding * 2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val spacerHeight = 16.dp

        // * * * * * INFO * * * * *
        Text(text = Strings.passwordRestInfo, color = colors.onSurface)

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * EMAIL * * * * *
        CustomOutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            labelText = Strings.email,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "email icon",
                    tint = colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight * 6))

        // * * * * * RESET PASSWORD * * * * *
        Button(
            onClick = {
                viewModel.sendResetRequest()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = colors.onSurface),
        ) {
            Text(text = Strings.passwordReset, color = kWhite)
        }
    }
}