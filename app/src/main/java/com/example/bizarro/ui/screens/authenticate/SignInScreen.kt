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
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Constants


@Composable
fun SignInScreen(navController: NavController,
                 viewModel: AuthenticateViewModel = hiltViewModel(),)
{

   BizarroTheme(
      darkTheme = Constants.isDark.value
   ) {

      Column(

         modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {

         Spacer(modifier = Modifier.height(100.dp))


         Text("Witaj w Bizarro!",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSurface
         )

         Spacer(modifier = Modifier.height(80.dp))

         loginFields(navController)


      }

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
         Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon",
         tint = MaterialTheme.colors.onSurface)
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(

         focusedBorderColor = MaterialTheme.colors.onSurface,
         unfocusedBorderColor = MaterialTheme.colors.onSurface,
         textColor = MaterialTheme.colors.onSurface)
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
         Icon(imageVector = Icons.Default.Lock, contentDescription = "PasswordIcon",
         tint = MaterialTheme.colors.onSurface)
      },
      colors = TextFieldDefaults.outlinedTextFieldColors(
         focusedBorderColor = MaterialTheme.colors.onSurface,
         unfocusedBorderColor = MaterialTheme.colors.onSurface,
         textColor = MaterialTheme.colors.onSurface)
   )

   Spacer(modifier = Modifier.height(80.dp))

   Button(
      onClick ={
         navController.navigate(route = com.example.bizarro.ui.Screen.UserProfile.route)
      },
      Modifier.size(width = 250.dp, height = 50.dp),
      colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

      ) {
      Text(text = "Zaloguj",
         style = MaterialTheme.typography.button,
         color = MaterialTheme.colors.background,
      )

   }

   Spacer(modifier = Modifier.height(20.dp))

   Text("ALBO",
      style = TextStyle(
         fontSize = 20.sp,
         fontFamily = FontFamily.Serif,
         fontWeight = FontWeight.Bold,
         color = MaterialTheme.colors.onSurface
      ))


   Spacer(modifier = Modifier.height(20.dp))

   Button(
      onClick ={
         navController.navigate(route = com.example.bizarro.ui.Screen.SignUp.route)

      },
      Modifier.size(width = 250.dp, height = 50.dp),
      colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
   )
   {
      Text(text = "Zapisz się",
         style = MaterialTheme.typography.button,
         color = MaterialTheme.colors.background,
      )
   }
}