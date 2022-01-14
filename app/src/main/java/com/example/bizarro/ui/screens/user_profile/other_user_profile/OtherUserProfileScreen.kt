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
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@Composable
fun OtherUserProfileScreen(
    navController: NavController,
    viewModel: OtherUserViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            TopBar(
                navController = navController,
                title = Strings.empty,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.CenterHorizontally)
            )

            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty() && !viewModel.isLoading.value) {
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


            // * * * * * * USER PROFILE * * * * * *
            if (!viewModel.isLoading.value) {

                OtherUserInfo()

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
fun OtherUserInfo(viewModel: OtherUserViewModel = hiltViewModel()) {

    Image(
        painter = rememberImagePainter(viewModel.userImage),
        contentDescription = "Other user Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, kBlueDark, RoundedCornerShape(10))
    )

    Spacer(modifier = Modifier.size(20.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(MaterialTheme.colors.secondaryVariant),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {

                Icon(Icons.Default.Person, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    viewModel.nameUser,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {

                Icon(Icons.Default.Email, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    viewModel.emailUser,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {

                Icon(Icons.Default.Phone, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    viewModel.phoneUser,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onSurface
                    )
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {

                Icon(
                    Icons.Default.Info,
                    "Icon description",
                    tint = MaterialTheme.colors.onSurface
                )

                Text(
                    text = "Opis profilu:",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSurface
                    ),
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

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

    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(MaterialTheme.colors.secondaryVariant),
        contentAlignment = Alignment.Center
    ) {

    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(MaterialTheme.colors.secondaryVariant),
        contentAlignment = Alignment.Center
    ) {

    }

}

@Composable
fun OtherUserButtonSection(navController: NavController) {

    Button(
        onClick = {
            OtherUserViewModel.otherUserId = RecordDetailsViewModel.userId!!
            navController.navigate(route = Screen.AddOpinion.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding)
    ) {

        Image(
            painterResource(R.drawable.ic_baseline_star_24),
            contentDescription = "Dodaj opinię",
            modifier = Modifier.size(30.dp),
        )

        Text(
            text = "Dodaj opinię",
            Modifier.padding(start = 10.dp),
            color = kWhite
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            OtherUserViewModel.otherUserId = RecordDetailsViewModel.userId!!
            navController.navigate(route = Screen.SeeOpinionOtherUser.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface)
    ) {


        Text(
            text = "Zobacz opinie",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background
        )
    }


}

