package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen

@Composable
fun UserProfileScreen(navController:NavController) {

    Column() {

        Button(onClick ={
            navController.navigate(route = Screen.SignIn.route)
        }) {
            Text(text = "Logout")
        }

    }



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "User profile")
    }
}