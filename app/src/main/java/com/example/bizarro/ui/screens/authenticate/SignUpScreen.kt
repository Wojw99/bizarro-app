package com.example.bizarro.ui.screens.authenticate

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens


@Composable
fun SignUpScreen(navController: NavController,
                 viewModel: AuthenticateViewModel = hiltViewModel(),)
{
    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(100.dp))

            Text("Zapisz się do Bizarro!",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface)

            Spacer(modifier = Modifier.height(80.dp))

            RegisterFields(navController)

        }
    }
}

@Composable
fun RegisterFields(navController: NavController,
                   viewModel: AuthenticateViewModel = hiltViewModel())
{

    val context = LocalContext.current

    OutlinedTextField(
        value =viewModel.emailRegisterText.value,
        onValueChange ={
            viewModel.emailRegisterText.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),
        placeholder = { Text(text = "Podaj swój email") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon",
            tint =  MaterialTheme.colors.onSurface)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        )
    )

    Spacer(modifier = Modifier.height(20.dp))


    OutlinedTextField(
        value = viewModel.passwordRegisterText.value,
        onValueChange ={
            viewModel.passwordRegisterText.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),
        placeholder = { Text(text = "Podaj swoje hasło") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon",
            tint = MaterialTheme.colors.onSurface)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface)
    )

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick = {
            Toast.makeText(context, "Zarejestrowano", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),

        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    ) {

        Text(
            text = "Zarejestruj",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        onClick = {
            navController.navigate(route = Screen.SignIn.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),

        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    ) {

        Text(
            text = "Logowanie",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,
        )
    }
}