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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen

import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.blueColor
import com.example.bizarro.ui.theme.darkColor
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.ui.theme.lightblueColor

@Composable
fun SignUpScreen(navController: NavController,
                 viewModel: AuthenticateViewModel = hiltViewModel(),)
{

    Column(

        modifier = Modifier
        .fillMaxSize()
        .background(kWhite),
        horizontalAlignment = Alignment.CenterHorizontally) {


        Spacer(modifier = Modifier.height(100.dp))


        Text("Sign Up to Bizarro!",
            style = MaterialTheme.typography.caption)

        Spacer(modifier = Modifier.height(80.dp))

        var textRegisterEmail by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value =textRegisterEmail,
            onValueChange ={
                textRegisterEmail = it
            },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Type your e-mail") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = blueColor,
                unfocusedBorderColor = darkColor)
        )

        Spacer(modifier = Modifier.height(20.dp))

        var textRegisterPassword by remember { mutableStateOf(TextFieldValue("")) }


        OutlinedTextField(
            value = textRegisterPassword,
            onValueChange ={
                textRegisterPassword = it
            },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Type your password") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon" )
            }
        )

        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick ={
                //navController.navigate(route = com.example.bizarro.ui.Screen.SignIn.route)
                //Toast.makeText(this,"Account registered", Toast.LENGTH_SHORT).show()
            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = lightblueColor),
        )
        {
            Text(text = "Register Account",
                style = MaterialTheme.typography.button
            )

        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick ={
                navController.navigate(route = com.example.bizarro.ui.Screen.SignIn.route)

            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = lightblueColor),
        )
        {
            Text(text = "Go to Sign In",
                style = MaterialTheme.typography.button
            )

        }
    }

}