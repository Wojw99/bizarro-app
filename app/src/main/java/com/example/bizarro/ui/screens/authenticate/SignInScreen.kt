package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bizarro.ui.NavGraph
import com.example.bizarro.ui.NavGraph
import com.example.bizarro.ui.Screen

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun SignInScreen(navController: NavController)
{
    //Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //Text(text = "SignIn")


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
//            //.border(1.dp, Color.Red, RectangleShape)
//            .fillMaxWidth()
//            .padding(20.dp)
            ) {

        Button(onClick ={
            navController.navigate(route = com.example.bizarro.ui.Screen.SignUp.route)
        }) {
            Text(text = "GoToRegister")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick ={
            navController.navigate(route = com.example.bizarro.ui.Screen.Home.route)
        }) {
            Text(text = "Login")
        }


    }

    
}