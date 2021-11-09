package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.kWhite


@Composable
fun SeeOpinionsScreen(navController: NavController,
                      viewModel: OtherUserViewModel = hiltViewModel())
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(kWhite),
        horizontalAlignment = Alignment.CenterHorizontally) {


        HeaderSectionSeeOpinionOtherUser(navController)


        Text("Opinie o użytkowniku",
            style = MaterialTheme.typography.caption)




    }
}

@Composable
fun HeaderSectionSeeOpinionOtherUser(navController: NavController)
{
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){


        IconButton(
            onClick = {
                navController.navigate(route = Screen.OtherUserProfile.route)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to user profile ",
                Modifier.size(30.dp)
            )
        }




    }
}