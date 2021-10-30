package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SignInScreen(navController: NavController)
{
   Column(

      modifier = Modifier
         .fillMaxSize()
         .background(Color.White),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      Spacer(modifier = Modifier.height(100.dp))


      Text("Welcome to Bizarro!",
         style = TextStyle(
            fontSize = 32.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
         ))

      Spacer(modifier = Modifier.height(80.dp))


      var textLoginEmail by remember { mutableStateOf(TextFieldValue("")) }

      OutlinedTextField(
         value =textLoginEmail,
         onValueChange ={
            textLoginEmail = it
         },
         label = { Text(text = "Email") },
         placeholder = { Text(text = "Type your e-mail") },
         leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
         },
         colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Black)
      )

      Spacer(modifier = Modifier.height(20.dp))

      var textLoginPassword by remember { mutableStateOf(TextFieldValue("")) }

      OutlinedTextField(
         value = textLoginPassword,
         onValueChange ={
            textLoginPassword = it
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
            navController.navigate(route = com.example.bizarro.ui.Screen.Home.route)
         },
         Modifier.size(width = 250.dp, height = 50.dp),
         colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),

         ) {
         Text(text = "Sign In",
            style = TextStyle(
               fontSize = 20.sp,
               fontFamily = FontFamily.Serif,
               fontWeight = FontWeight.Bold,
               color = Color.White,
            )
         )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text("OR",
         style = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
         ))


      Spacer(modifier = Modifier.height(20.dp))

      Button(
         onClick ={
            navController.navigate(route = com.example.bizarro.ui.Screen.SignUp.route)

         },
         Modifier.size(width = 250.dp, height = 50.dp),
         //colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
      )
      {
         Text(text = "Sign Up",
            style = TextStyle(
               fontSize = 20.sp,
               fontFamily = FontFamily.Serif,
               fontWeight = FontWeight.Bold,
               color = Color.White,
            )
         )
      }
   }
}