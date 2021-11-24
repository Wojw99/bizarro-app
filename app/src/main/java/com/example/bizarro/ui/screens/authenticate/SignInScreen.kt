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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.darkColor
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.ui.theme.lightblueColor


@Composable
fun SignInScreen(navController: NavController,
                 viewModel: AuthenticateViewModel = hiltViewModel(),)
{
   Column(

      modifier = Modifier
         .fillMaxSize()
         .background(kWhite),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      Spacer(modifier = Modifier.height(100.dp))


      Text("Witaj w Bizarro!",
         style = MaterialTheme.typography.caption)

      Spacer(modifier = Modifier.height(80.dp))

      loginFields(navController)


   }
}

@Composable
fun loginFields(navController: NavController)
{

   var textLoginEmail by remember { mutableStateOf(TextFieldValue("")) }
   var textLoginPassword by remember { mutableStateOf(TextFieldValue("")) }

   OutlinedTextField(
      value =textLoginEmail,
      onValueChange ={
         textLoginEmail = it
      },
      //label = { Text(text = "Email") },
      placeholder = { Text(text = "Podaj swój email") },
      leadingIcon = {
         Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(
         focusedBorderColor = darkColor,
         unfocusedBorderColor = darkColor)
   )

   Spacer(modifier = Modifier.height(20.dp))

   OutlinedTextField(
      value = textLoginPassword,
      onValueChange ={
         textLoginPassword = it
      },
      //label = { Text(text = "Hasło") },
      placeholder = { Text(text = "Podaj swoje hasło") },
      leadingIcon = {
         Icon(imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon" )
      }
   )

   Spacer(modifier = Modifier.height(80.dp))

   Button(
      onClick ={
         navController.navigate(route = com.example.bizarro.ui.Screen.UserProfile.route)
      },
      Modifier.size(width = 250.dp, height = 50.dp),
      colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),

      ) {
      Text(text = "Zaloguj",
         style = MaterialTheme.typography.button
      )

   }

   Spacer(modifier = Modifier.height(20.dp))

   Text("ALBO",
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
      colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),
   )
   {
      Text(text = "Zapisz się",
         style = MaterialTheme.typography.button
      )
   }
}