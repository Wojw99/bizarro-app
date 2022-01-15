package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@Composable
fun OtherUserProfileScreen(
    navController: NavController,
    viewModel: OtherUserViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        val backgroundColor = if (viewModel.appState.isDarkTheme.value) MaterialTheme.colors.background else kVeryLightGray

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // * * * * * * TOP BAR ICON * * * * * *
            TopBar(
                navController = navController,
                showBackButton = false,
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
                OtherUserButtonSection(navController = navController)
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Spacer(modifier = Modifier.height(Dimens.bottomBarHeight))
        }
    }
}

@Composable
fun OtherUserInfo(
    viewModel: OtherUserViewModel = hiltViewModel(),
) {
    val textSize = 18.sp
    val iconSize = 20.dp
    val textToIconSpace = 16.dp

    // * * * * * * IMAGE * * * * * *
    var painter: Painter? = null
    if (viewModel.userImage.value != null) {
        painter = rememberImagePainter(CommonMethods.getUrlForImage(viewModel.userImage.value!!))
    } else {
        painter = painterResource(id = R.drawable.user_default)
    }

    Image(
        painter = painter,
        contentDescription = "User Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, MaterialTheme.colors.primary, RoundedCornerShape(10))
    )

    Spacer(modifier = Modifier.size(textToIconSpace / 2))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = Dimens.standardPadding)
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .background(MaterialTheme.colors.surface)
            .clickable {},
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = textToIconSpace * 2, vertical = textToIconSpace)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // * * * * * * USERNAME * * * * * *
            Text(
                viewModel.userName,
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.size(textToIconSpace))

            // * * * * * * NAME * * * * * *
            Icon(
                Icons.Default.Person,
                "Icon description",
                tint = colors.primary,
                modifier = Modifier.size(iconSize),
            )
            Text(
                viewModel.nameUser,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = textSize,
                    fontFamily = FontFamily.Default,
                )
            )

            Spacer(modifier = Modifier.size(textToIconSpace))

            // * * * * * * E_MAIL * * * * * *
            Icon(
                Icons.Default.Email,
                "Icon description",
                tint = colors.primary,
                modifier = Modifier.size(iconSize),
            )
            Text(
                viewModel.emailUser,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = textSize,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.size(textToIconSpace))

            // * * * * * * PHONE * * * * * *
            Icon(
                Icons.Default.Phone,
                "Icon description",
                tint = colors.primary,
                modifier = Modifier.size(iconSize),
            )
            Text(
                viewModel.phoneUser,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = textSize,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.size(textToIconSpace))

            // * * * * * * ADDRESS * * * * * *
            Icon(
                Icons.Default.LocationOn,
                "Location description",
                tint = colors.primary,
                modifier = Modifier.size(iconSize),
            )
            Text(
                text = viewModel.address,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = textSize,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Start
                ),
            )

            Spacer(modifier = Modifier.size(textToIconSpace))

            // * * * * * * DESCRIPTION * * * * * *
            Icon(
                Icons.Default.Info,
                "Icon description",
                tint = colors.primary,
                modifier = Modifier.size(iconSize),
            )
            Text(
                text = viewModel.userDescription,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = textSize,
                    fontFamily = FontFamily.Default,
                    textAlign = TextAlign.Center
                ),
            )
        }
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

