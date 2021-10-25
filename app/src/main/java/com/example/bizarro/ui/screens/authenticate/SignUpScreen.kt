package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen

@Composable
fun SignUpScreen(navController: NavController)
{
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //Text(text = "SignIn")
        Button(
            onClick = {
                navController.navigate(route = Screen.SignIn.route)



            }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = "GoToLogin")
        }


    }
}