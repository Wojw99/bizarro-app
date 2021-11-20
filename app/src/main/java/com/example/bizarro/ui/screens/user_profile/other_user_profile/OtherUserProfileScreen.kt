package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.blueColor
import com.example.bizarro.ui.theme.darkColor
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kLightGray

@Composable
fun OtherUserProfileScreen(navController: NavController,
                           viewModel: OtherUserViewModel = hiltViewModel(),)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        HeaderSectionOtherUserProfile(navController)

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
fun OtherUserInfo(viewModel: OtherUserViewModel = hiltViewModel())
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

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Spacer(modifier = Modifier.width(55.dp))

            Icon(Icons.Default.Person, "Icon description", tint = kBlack)

            Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.nameOtherUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Spacer(modifier = Modifier.width(25.dp))

            Icon(Icons.Default.Email, "Icon description", tint = kBlack)

            Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.emailOtherUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            Spacer(modifier = Modifier.width(85.dp))

            Icon(Icons.Default.Phone, "Icon description", tint = kBlack)

            Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.phoneOtherUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }


    UserDescriptionHeader()

    Text(
        text = viewModel.userOtherDescription,
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )
    )

}

@Composable
fun HeaderSectionOtherUserProfile(navController: NavController)
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
                contentDescription = "Back to user record ",
                Modifier.size(30.dp)
            )
        }

    }
}

@Composable
fun UserDescriptionHeader()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Spacer(modifier = Modifier.width(110.dp))

            Icon(Icons.Default.Info, "Icon description", tint = kBlack)

            Text(text = "Opis profilu:",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),
            )
        }
    }
}