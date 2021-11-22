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
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.search.RecordList
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings

@Composable
fun UserProfileScreen(
    navController:NavController,

    viewModel: UserProfileViewModel = hiltViewModel(),

) {
    //val context = LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderSectionUserProfile(navController)

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
                )
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
                Button(onClick = { viewModel.GetUserProfile() }) {
                    Text(
                        text = Strings.refresh,
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

            UserInformation()

            Spacer(modifier = Modifier.height(50.dp))

            UserButtonSection(navController)

        }

        // * * * * * * PROGRESS BAR * * * * * *
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }




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
        //painter = painterResource(id = R.drawable.ic_baseline_person_24),
        painter = rememberImagePainter(viewModel.userImage),
        //val painter = rememberImagePainter(
        //record.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
        contentDescription = "User Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, blueColor, RoundedCornerShape(10))
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center) {
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,)
    {

        Icon(Icons.Default.Person, "Icon description", tint = kBlack)

        //Spacer(modifier = Modifier.width(15.dp))

        Text(viewModel.nameUser,
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Bold
            ))

    }



    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,)
        {
            //Spacer(modifier = Modifier.width(25.dp))

            Icon(Icons.Default.Email, "Icon description", tint = kBlack)

            //Spacer(modifier = Modifier.width(15.dp))

            Text(viewModel.emailUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                ))
        }

    }



    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,)
        {
            //Spacer(modifier = Modifier.width(85.dp))

            Icon(Icons.Default.Phone, "Icon description", tint = kBlack)

            //Spacer(modifier = Modifier.width(10.dp))

            Text(viewModel.phoneUser,
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                ))
        }

    }

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
fun UserButtonSection(navController: NavController)
{
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
            navController.navigate(route = Screen.SeeYourOpinionsScreen.route)

            //Toast.makeText(context, viewModel.nameUser, Toast.LENGTH_SHORT).show()

        },
        Modifier.size(width = 180.dp, height = 40.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = blueColor),
    )
    {
        Text(text = "Zobacz opinie",
            style = MaterialTheme.typography.button
        )
    }
}


@Composable
fun DescriptionHeader()
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

        ) {

            //Spacer(modifier = Modifier.width(120.dp))

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





