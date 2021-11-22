package com.example.bizarro.ui.screens.user_profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun EditProfileScreen(navController: NavController,
                      viewModel: UserProfileViewModel = hiltViewModel(),)
{
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderEditProfileScreen(navController)

        Text("Edytuj informacje:",
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(40.dp))




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
                Button(onClick = { viewModel.getUserProfile() }) {
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

        // * * * * * * USER PROFILE EDIT SECTION * * * * * *
        if (!viewModel.isLoading.value) {
            //RecordList(navController = navController)

            //UserInformation()
            EditFieldsSection()

            Spacer(modifier = Modifier.height(50.dp))

            //UserButtonSection(navController)

        }

        // * * * * * * PROGRESS BAR * * * * * *
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }


    }


}




@Composable
fun HeaderEditProfileScreen(navController: NavController)
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

@Composable
fun EditFieldsSection(viewModel: UserProfileViewModel = hiltViewModel())
{
    //        var editDataName by remember {
//            mutableStateOf(TextFieldValue(viewModel.nameUser))
//        }
    //var editEmail by remember { mutableStateOf(TextFieldValue(viewModel.emailUser)) }
    //var editPhoneNumber by remember { mutableStateOf(TextFieldValue(viewModel.phoneUser)) }

    //var editUserDescription by remember { mutableStateOf(TextFieldValue(viewModel.userDescription)) }


    TextField(
        value = viewModel.nameUser,
        onValueChange = {
            viewModel.nameUser = it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "PersonIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )


    Spacer(modifier = Modifier.height(30.dp))

    TextField(
        value = viewModel.emailUser,
        onValueChange = {
            viewModel.emailUser = it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )

    Spacer(modifier = Modifier.height(30.dp))

    TextField(
        value = viewModel.phoneUser,
        onValueChange = {
            viewModel.phoneUser = it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "PhoneIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )

    Spacer(modifier = Modifier.height(30.dp))

    TextField(
        value = viewModel.userDescription,
        onValueChange = {
            viewModel.userDescription= it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Info, contentDescription = "InfoIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor),
        modifier = Modifier.width(300.dp)

    )

    Spacer(modifier = Modifier.height(80.dp))

    Button(
        onClick ={
                Timber.d("UserName: ${viewModel.nameUser}")
            //viewModel.updateName(editDataName.text)

//                navController.navigate(
//                    route = Screen.UserProfile.route

//                    navArgument("editName",){
//                        type = NavType.StringType,
//                        nullable = true
//                    }
            //)

        },
        Modifier.size(width = 250.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = kBlack),

        ) {
        Text(text = "Zapisz",
            style = MaterialTheme.typography.button
        )
    }
}