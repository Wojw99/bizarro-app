package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen

@Composable
fun UserProfileScreen(navController:NavController) {

    Column(


        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderSectionUserProfile(navController)


        UserImage()

        //Spacer(modifier = Modifier.height(40.dp))

        UserInfo(navController)

        //Spacer(modifier = Modifier.height(20.dp))



        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {}) {
            Image(
                painterResource(R.drawable.ic_baseline_star_24),
                contentDescription ="Zobacz opinie",
                modifier = Modifier.size(35.dp))

            Text(text = "Zobacz opinie",Modifier.padding(start = 10.dp))
        }


        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick ={
                navController.navigate(route = com.example.bizarro.ui.Screen.SignIn.route)
            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),

            ) {
            Text(text = "Wyloguj",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
        }

    }

}

@Composable
fun HeaderSectionUserProfile(navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){

        IconButton(

            onClick = {
                navController.navigate(route = Screen.Settings.route)
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings Button",
                Modifier.size(30.dp)
            )

        }
    }
}

@Composable
fun UserImage()
{

    Image(
        painter = painterResource(id = R.drawable.ic_baseline_person_24),
        contentDescription = "User Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, Color.Blue, RoundedCornerShape(10))
    )

    

}

@Composable
fun UserInfo(navController: NavController)
{
    Text("Jan Kowalski",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        ))

    Spacer(modifier = Modifier.height(10.dp))

    Text("jkowalski@gmail.com",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        ))

    Spacer(modifier = Modifier.height(10.dp))

    Text("123 456 789",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        ))

    Spacer(modifier = Modifier.height(50.dp))

    Button(
        onClick ={
            navController.navigate(route = Screen.EditProfile.route)
        },
        Modifier.size(width = 180.dp, height = 40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
    )
    {
        Text(text = "Edytuj profil",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        )
    }





}



