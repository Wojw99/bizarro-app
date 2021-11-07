package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.blueColor
import com.example.bizarro.ui.theme.darkColor

@Composable
fun OtherUserProfileScreen(navController: NavController)
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(80.dp))

        OtherUserImage()

        OtherUserInfo()



        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                navController.navigate(route = Screen.AddOpinion.route)
        }) {
            Image(
                painterResource(R.drawable.ic_baseline_star_24),
                contentDescription ="Dodaj opinie",
                modifier = Modifier.size(35.dp))

            Text(text = "Dodaj opinię",Modifier.padding(start = 10.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.SeeOpinionOtherUser.route)
            },
            Modifier.size(width = 180.dp, height = 60.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),
        )
        {
            Text(text = "Zobacz opinie",
                style = MaterialTheme.typography.button
            )
        }


    }

}


@Composable
fun OtherUserImage()
{

    Image(
        painter = painterResource(id = R.drawable.ic_baseline_person_24),
        contentDescription = "Other user Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, Color.Blue, RoundedCornerShape(10))
    )



}


@Composable
fun OtherUserInfo()
{
    Text("Tomasz Kowalski",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        )
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text("tkowalski@gmail.com",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        )
    )

    Spacer(modifier = Modifier.height(10.dp))

    Text("987 654 321",
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        )
    )


}