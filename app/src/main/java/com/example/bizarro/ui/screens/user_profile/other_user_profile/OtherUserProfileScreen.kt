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
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.screens.user_profile.settings.SettingsViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings

@Composable
fun OtherUserProfileScreen(navController: NavController,
                           viewModel: OtherUserViewModel = hiltViewModel(),)
{

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            HeaderSectionOtherUserProfile(navController)

            // * * * * * * ERROR TEXT * * * * * *
            if(viewModel.loadError.value.isNotEmpty() && !viewModel.isLoading.value)
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = viewModel.loadError.value,
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = { viewModel.getOtherUserProfile() }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }

            // * * * * * * EMPTY TEXT * * * * * *
//        if (viewModel.recordList.value.isEmpty()
//            && !viewModel.isLoading.value
//            && viewModel.loadError.value.isEmpty()
//        ) {
//            Text(
//                text = Strings.listIsEmpty,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }

            // * * * * * * USER PROFILE * * * * * *
            if (!viewModel.isLoading.value) {
                //RecordList(navController = navController)

                OtherUserInfo()

                Spacer(modifier = Modifier.height(50.dp))

                OtherUserButtonSection(navController)

            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

        }
    }



}


@Composable
fun OtherUserInfo(viewModel: OtherUserViewModel = hiltViewModel())
{

    Image(
        painter = rememberImagePainter(viewModel.userImage),
        contentDescription = "Other user Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, kBlueDark, RoundedCornerShape(10))
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            //Spacer(modifier = Modifier.width(55.dp))

            Icon(Icons.Default.Person, "Icon description", tint = MaterialTheme.colors.onSurface)

            //Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.nameUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            //Spacer(modifier = Modifier.width(25.dp))

            Icon(Icons.Default.Email, "Icon description", tint = MaterialTheme.colors.onSurface)

            //Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.emailUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
        {
            //Spacer(modifier = Modifier.width(85.dp))

            Icon(Icons.Default.Phone, "Icon description", tint = MaterialTheme.colors.onSurface)

            //Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.phoneUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onSurface
                )
            )
        }
    }


    UserDescriptionHeader()

    Text(
        text = viewModel.userDescription,
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
            )
    )

}

@Composable
fun OtherUserButtonSection(navController: NavController)
{
    Button(
        onClick = {
            navController.navigate(route = Screen.AddOpinion.route)
        }) {
        Image(
            painterResource(R.drawable.ic_baseline_star_24),
            contentDescription ="Dodaj opinie",
            modifier = Modifier.size(35.dp),
        )

        Text(text = "Dodaj opinię",
            Modifier.padding(start = 10.dp),
            color = kWhite)
    }

    Spacer(modifier = Modifier.height(30.dp))

    Button(
        onClick ={
            navController.navigate(route = Screen.SeeOpinionOtherUser.route)
        },
        Modifier.size(width = 200.dp, height = 60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
    )
    {
        Text(text = "Zobacz opinie",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background
        )
    }
}

@Composable
fun HeaderSectionOtherUserProfile(navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        contentAlignment = Alignment.Center){

        IconButton(
            onClick = {
                navController.navigate(route = Screen.Settings.route)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to user record ",
                Modifier.size(30.dp),
                tint = MaterialTheme.colors.onSurface
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
            .padding(12.dp),
        contentAlignment = Alignment.Center

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            //Spacer(modifier = Modifier.width(110.dp))

            Icon(Icons.Default.Info, "Icon description", tint = MaterialTheme.colors.onSurface)

            Text(text = "Opis profilu:",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSurface
                ),
            )
        }
    }
}