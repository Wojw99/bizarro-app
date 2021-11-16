package com.example.bizarro.ui.screens.user_profile

import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.theme.*

@Composable
fun UserProfileScreen(
    navController:NavController,

    viewModel: UserProfileViewModel = hiltViewModel(),

) {
    val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(kWhite),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderSectionUserProfile(navController)

        UserInformation()

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick ={
                navController.navigate(route = Screen.EditProfile.route)
            },
            Modifier.size(width = 180.dp, height = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = darkColor),
        )
        {
            Text(text = "Edytuj profil",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick ={
                //navController.navigate(route = Screen.SeeYourOpinionsScreen.route)
                Toast.makeText(context, viewModel.nameUser, Toast.LENGTH_SHORT).show()

                     },
            Modifier.size(width = 180.dp, height = 40.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
        )
        {
            Text(text = "Zobacz opinie",
                style = MaterialTheme.typography.button
            )
        }

        Spacer(modifier = Modifier.height(60.dp))





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
fun UserInformation(viewModel: UserProfileViewModel = hiltViewModel())
{

    Image(
        painter = painterResource(id = R.drawable.ic_baseline_person_24),
        contentDescription = "User Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, blueColor, RoundedCornerShape(10))
    )

    Text(viewModel.nameUser,
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold
        ))

    Spacer(modifier = Modifier.height(10.dp))

    Text(viewModel.emailUser,
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        ))

    Spacer(modifier = Modifier.height(10.dp))

    Text(viewModel.phoneUser,
        style = TextStyle(
            fontSize = 30.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal
        ))

    Spacer(modifier = Modifier.height(30.dp))

    DescriptionHeader()

    Text(
        text = viewModel.userDescription,
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )
    )


}


@Composable
fun DescriptionHeader()
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {

            Spacer(modifier = Modifier.width(120.dp))

            Icon(Icons.Default.Info, "Icon description", tint = kBlack)

            Text(text = "Opis profilu:",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                ),)
        }

    }
}





