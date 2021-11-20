package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.AppState
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.blueColor
import com.example.bizarro.ui.theme.darkColor
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Strings
import com.example.bizarro.util.models.TopBarAction

@Composable
fun SettingsScreen(navController: NavController,
                   viewModel: SettingsViewModel = hiltViewModel(),)
{

    Column(modifier = Modifier
        .fillMaxSize()
        .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderSectionSettings(navController)

        Text("Ustawienia aplikacji",
            style = MaterialTheme.typography.caption)

        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.AboutAppScreen.route)
            },
            Modifier.size(width = 300.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),

            ) {
            Text(text = "Informacje o aplikacji",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.HelpScreen.route)
            },
            Modifier.size(width = 300.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),

            ) {
            Text(text = "Pomoc",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.PrivacyPolicyScreen.route)
            },
            Modifier.size(width = 300.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),

            ) {
            Text(text = "Polityka prywatności",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.OtherUserProfile.route)
            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),

            ) {
            Text(text = "Tryb ciemny/jasny",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.SignIn.route)
            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),

            ) {
            Text(text = "Wyloguj",
                style = MaterialTheme.typography.button
            )
        }




    }




}

@Composable
fun HeaderSectionSettings(navController: NavController)
{
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){


        IconButton(
            onClick = {
                navController.navigate(route = Screen.UserProfile.route)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to user profile ",
                Modifier.size(30.dp)
            )
        }




    }
}

