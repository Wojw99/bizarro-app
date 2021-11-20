package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.search.TypeSpecificTitle
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.CommonMethods
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings


@ExperimentalFoundationApi
@Composable
fun SeeOpinionsScreen(navController: NavController,
                      viewModel: OtherUserViewModel = hiltViewModel())
{
    Column(modifier = Modifier
        .fillMaxSize()
        .background(kWhite),
        horizontalAlignment = Alignment.CenterHorizontally) {


        HeaderSectionSeeOpinionOtherUser(navController)


        Text("Opinie o użytkowniku:",
            style = MaterialTheme.typography.caption)

        Spacer(modifier = Modifier.height(30.dp))

        UserOpinionsList()

        Spacer(modifier = Modifier.height(30.dp))

        //Text(text = viewModel.opinionOtherUserList.size.toString(),
        //style = MaterialTheme.typography.caption)

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

@ExperimentalFoundationApi
@Composable
fun UserOpinionsList(
    viewModel: OtherUserViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray)
    ){

        val opinionList = viewModel.opinionsOtherUser.value
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

