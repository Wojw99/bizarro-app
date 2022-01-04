package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.bizarro.ui.screens.add_record.AddRecordViewModel
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@Composable
fun UserProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = true

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderSectionUserProfile(navController)

            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty() && !viewModel.isLoading.value) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = viewModel.loadError.value,
                        color = colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = { viewModel.getUserProfile() }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }

            // * * * * * * USER PROFILE * * * * * *
            if (!viewModel.isLoading.value) {
                //RecordList(navController = navController)

                UserInformation()

                //Spacer(modifier = Modifier.height(10.dp))

                UserButtonSection(navController)

            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }


        }

    }


}

@Composable
fun HeaderSectionUserProfile(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        IconButton(

            onClick = {
                navController.navigate(route = Screen.Settings.route)
            },
            modifier = Modifier.align(Alignment.CenterEnd)

        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings Button",
                Modifier.size(30.dp),
                tint = colors.onSurface
            )

        }
    }
}

@Composable
fun UserInformation(viewModel: UserProfileViewModel = hiltViewModel()) {

    Image(
        //painter = painterResource(id = R.drawable.ic_baseline_person_24),
        painter = rememberImagePainter(viewModel.userImage),
        //val painter = rememberImagePainter(
        //record.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
        contentDescription = "User Image",
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
            .background(colors.secondaryVariant),

        contentAlignment = Alignment.Center
    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            )
            {

                Icon(Icons.Default.Person, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    viewModel.nameUser,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    )
                )

            }
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            )
            {

                Icon(Icons.Default.Email, "Icon description", tint = MaterialTheme.colors.onSurface)


                Text(
                    viewModel.emailUser,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            )
            {

                Icon(Icons.Default.Phone, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    viewModel.phoneUser,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            Spacer(modifier = Modifier.size(20.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,

                ) {

                Icon(Icons.Default.Info, "Icon description", tint = MaterialTheme.colors.onSurface)

                Text(
                    text = "Opis profilu:",
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 25.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start
                    ),
                )
            }

            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = viewModel.userDescription,
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                    )
            )
        }

    }

}

@Composable
fun UserButtonSection(navController: NavController) {


    Button(
        onClick = {
            navController.navigate(route = Screen.EditProfile.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding)
    ) {

        Image(
            painterResource(R.drawable.ic_baseline_person_24),
            contentDescription = "Edytuj profil",
            modifier = Modifier.size(30.dp),
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(text = Strings.edit_profile)
    }

    Button(
        onClick = {
            navController.navigate(route = Screen.SeeYourOpinionsScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding)
    ) {

        Image(
            painterResource(R.drawable.ic_baseline_star_24),
            contentDescription = "Zobacz opinie o sobie",
            modifier = Modifier.size(30.dp),
        )

        Text(text = Strings.see_opinions)
    }

    Spacer(modifier = Modifier.height(30.dp))


}







