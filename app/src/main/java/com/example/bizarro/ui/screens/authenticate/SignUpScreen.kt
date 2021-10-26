package com.example.bizarro.ui.screens.authenticate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen

@Composable
fun SignUpScreen(navController: NavController)
{


    Button(
        onClick ={
            navController.navigate(route = com.example.bizarro.ui.Screen.SignIn.route)

        },
        Modifier.size(width = 250.dp, height = 50.dp),
        //colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
    )
    {
        Text(text = "Register Account",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )

    }



    //Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        //Text(text = "SignIn")
//        Button(
//            onClick = {
//                navController.navigate(route = Screen.SignIn.route)
//
//
//
//            }, modifier = Modifier.align(Alignment.Center)) {
//            Text(text = "Register Account")
//        }


    //}
}