package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            TopBar(
                navController = navController,
                title = Strings.empty,

                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                "Ustawienia aplikacji",
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    navController.navigate(route = Screen.AboutAppScreen.route)
                },
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 30.dp,
                    pressedElevation = 30.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standardPadding),
                shape = RoundedCornerShape(50)
            ) {

                Image(
                    painterResource(R.drawable.ic_baseline_info_24),
                    contentDescription = "Informacje o aplikacji",
                    modifier = Modifier.size(30.dp),
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Informacje o aplikacji",
                    color = kWhite,
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate(route = Screen.PrivacyPolicyScreen.route)
                },
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 30.dp,
                    pressedElevation = 30.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standardPadding),
                        shape = RoundedCornerShape(50)
            ) {

                Image(
                    painterResource(R.drawable.ic_baseline_privacy_tip_24),
                    contentDescription = "privacy policy",
                    modifier = Modifier.size(30.dp),
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Polityka prywatności",
                    color = kWhite,
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    navController.navigate(route = Screen.HelpScreen.route)
                },
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 30.dp,
                    pressedElevation = 30.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standardPadding),
                shape = RoundedCornerShape(50)
            ) {

                Image(
                    painterResource(R.drawable.ic_baseline_help_24),
                    contentDescription = "Pomoc",
                    modifier = Modifier.size(30.dp),
                )

                Spacer(modifier = Modifier.width(10.dp))


                Text(
                    text = "Pomoc",
                    color = kWhite,
                    style = MaterialTheme.typography.button
                )
            }


            Spacer(modifier = Modifier.height(100.dp))

            Button(
                onClick = {
                    Constants.checkIsDark()
                },
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 30.dp,
                    pressedElevation = 30.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standardPadding),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

                ) {

                Icon(Icons.Default.Refresh, "Icon description", tint = MaterialTheme.colors.background)

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Tryb ciemny/jasny",
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.button
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = {
                    navController.navigate(route = Screen.SignIn.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    //.fillMaxWidth()
                    .padding(Dimens.standardPadding),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
                ) {
                Text(
                    text = "Wyloguj",
                    color = MaterialTheme.colors.background,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}



