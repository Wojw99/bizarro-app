package com.example.bizarro.ui.screens.user_profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.util.Constants

@Composable
fun AboutAppScreen(navController: NavController,
                   viewModel: SettingsViewModel = hiltViewModel(),)
{

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            HeaderSectionAboutApp(navController)

            Text("Informacje o aplikacji",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface)


        }
    }

}

@Composable
fun HeaderSectionAboutApp(navController: NavController)
{
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){


        IconButton(
            onClick = {
                navController.navigate(route = Screen.Settings.route)
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