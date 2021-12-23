package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants

@Composable
fun SettingsScreen(navController: NavController,
                   viewModel: SettingsViewModel = hiltViewModel(),
)
{
    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            ,
            horizontalAlignment = Alignment.CenterHorizontally) {

            HeaderSectionSettings(navController)

            Text("Ustawienia aplikacji",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption)

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick ={
                    navController.navigate(route = Screen.AboutAppScreen.route)
                },
                Modifier.size(width = 300.dp, height = 50.dp),
                //colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

                ) {
                Text(text = "Informacje o aplikacji",
                    color = kWhite,
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick ={
                    navController.navigate(route = Screen.HelpScreen.route)
                },
                Modifier.size(width = 300.dp, height = 50.dp),
                //colors = ButtonDefaults.buttonColors(backgroundColor = kBlueDark),

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
                //colors = ButtonDefaults.buttonColors(backgroundColor = kBlueDark),

                ) {
                Text(text = "Polityka prywatności",
                    style = MaterialTheme.typography.button
                )
            }




            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {
                    Constants.checkIsDark()
                },
                Modifier.size(width = 250.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

                ) {
                Text(text = "Tryb ciemny/jasny",
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick ={
                    navController.navigate(route = Screen.SignIn.route)
                },
                Modifier.size(width = 250.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

                ) {
                Text(text = "Wyloguj",
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.button
                )
            }
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
                Modifier.size(30.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }

    }
}

