package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.screens.add_record.textFieldModifier
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.Dimens

@ExperimentalComposeUiApi
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthenticateViewModel = hiltViewModel(),
) {
    viewModel.appState.hideBottomMenu()

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Box {
            // * * * * * BODY * * * * *
            SignUpScreenBody(navController)

            // * * * * * * ERROR DIALOG * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                ConfirmAlertDialog(
                    onDismiss = { viewModel.clearError() },
                    title = Strings.error,
                    body = viewModel.loadError.value,
                )
            }

            // * * * * * * SUCCESS * * * * * *
            if (viewModel.successfullyRegister.value) {
                ConfirmAlertDialog(
                    onDismiss = {
                        viewModel.successfullyRegister.value = false
                        navController.popBackStack()
                    },
                    title = Strings.success2,
                    body = Strings.success,
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
fun SignUpScreenBody(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthenticateViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = Dimens.standardPadding * 2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacerHeight = 16.dp

        Spacer(modifier = Modifier.height(100.dp))

        Text(
            Strings.welcomeToBizarro,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(spacerHeight * 4))

        CustomOutlinedTextField(
            value = viewModel.userNameRegisterText.value,
            onValueChange = { viewModel.userNameRegisterText.value = it },
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
            value = viewModel.emailRegisterText.value,
            onValueChange = { viewModel.emailRegisterText.value = it },
            labelText = Strings.email,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "email icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        CustomOutlinedTextField(
            value = viewModel.passwordRegisterText.value,
            onValueChange = { viewModel.passwordRegisterText.value = it },
            labelText = Strings.password,
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

        Spacer(modifier = Modifier.height(spacerHeight))

        CustomOutlinedTextField(
            value = viewModel.passwordRepeatRegisterText.value,
            onValueChange = { viewModel.passwordRepeatRegisterText.value = it },
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
                viewModel.register()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
        ) {
            Text(text = Strings.register, color = kWhite)
        }

        Spacer(modifier = Modifier.height(spacerHeight * 4))

        // * * * * * BACK BUTTON * * * * *
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = null,
        ) {
            Text(text = Strings.returnToLogin, color = kGray)
        }
    }
}