package com.example.bizarro.ui.screens.user_profile.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.search.topRecordListMargin
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
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
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
                color = colors.primaryVariant,
                style = MaterialTheme.typography.caption
            )

            Spacer(modifier = Modifier.height(80.dp))

            Box(modifier = Modifier
                .background(colors.secondaryVariant)
                .align(Alignment.CenterHorizontally)
            )
            {
                Column()
                {
                    Spacer(modifier = Modifier.height(5.dp))

                    Box(modifier = Modifier
                        .background(colors.background)
                        .clickable {
                            navController.navigate(route = Screen.AboutAppScreen.route)
                        }
                        .height(80.dp)
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)

                    ) {
                        
                            Text(
                                modifier = Modifier
                                         .align(Alignment.CenterStart),
                                text = "Informacje o aplikacji",
                                color = colors.onBackground,
                                style = MaterialTheme.typography.button,

                                )

                            Icon(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(30.dp)
                                    ,
                                imageVector = Icons.Default.Info,
                                contentDescription = "Info Button",
                                tint = colors.onSurface
                            )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(modifier = Modifier
                        .background(colors.background)
                        .clickable {
                            navController.navigate(route = Screen.HelpScreen.route)
                        }
                        .height(80.dp)
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)
                    ) {
                        
                            Text(
                                modifier = Modifier
                                .align(Alignment.CenterStart),
                                text = "Pomoc",
                                color = colors.onBackground,
                                style = MaterialTheme.typography.button
                            )

                            Icon(
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .size(30.dp)
                                ,
                                imageVector = Icons.Default.Face,
                                contentDescription = "Help Button",
                                tint = colors.onSurface
                            )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(modifier = Modifier
                        .background(colors.background)
                        .clickable {
                            viewModel.appState.toggleDarkMode()
                        }
                        .height(80.dp)
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp)
                    ) {

                            Text(
                                modifier = Modifier.align(CenterStart),
                                text = "Tryb ciemny/jasny",
                                color = MaterialTheme.colors.onBackground,
                                style = MaterialTheme.typography.button
                            )
                        
                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(30.dp)
                            ,
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Dark/Light Button",
                            tint = colors.onSurface
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }

            }

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.UserRecordList.route) {
                            inclusive = true
                        }
                    }
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



