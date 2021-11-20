package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.kLightGray


@ExperimentalFoundationApi
@Composable
fun SeeYourOpinionsScreen(navController: NavController,
                          viewModel: UserProfileViewModel = hiltViewModel()
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {


        HeaderSectionSeeOpinionUserProfile(navController)

        Text("Opinie o Tobie:",
            style = MaterialTheme.typography.caption)


        Spacer(modifier = Modifier.height(30.dp))

        LoggedUserOpinionsList()

        Spacer(modifier = Modifier.height(30.dp))


    }


}


@Composable
fun HeaderSectionSeeOpinionUserProfile(navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){
        IconButton(
            onClick = {
                navController.navigate(route = Screen.UserProfile.route)
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

@ExperimentalFoundationApi
@Composable
fun LoggedUserOpinionsList(
    viewModel: UserProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray)
    ){

        val opinionList = viewModel.opinionsLoggedUser.value
        val opinionListCount = opinionList.size


        opinionList.forEach{
                item ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),

                    text = item,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}