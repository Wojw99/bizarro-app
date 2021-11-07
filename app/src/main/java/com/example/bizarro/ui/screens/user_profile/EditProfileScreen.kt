package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.darkColor
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite

@Composable
fun EditProfileScreen(navController: NavController,
                      viewModel: SettingsEditUserProfileViewModel = hiltViewModel(),)
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(kWhite),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderEditProfileScreen(navController)

        Text("Edytuj informacje",
            style =MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(40.dp))


        var editDataName by remember { mutableStateOf(TextFieldValue("")) }
        var editEmail by remember { mutableStateOf(TextFieldValue("")) }
        var editPhoneNumber by remember { mutableStateOf(TextFieldValue("")) }


        TextField(
            value = editDataName,
            onValueChange = {
                editDataName = it
            },

            label = { Text(text = "Edytuj imię i nazwisko")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "PersonIcon" )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkColor,
                unfocusedBorderColor = darkColor)

        )


        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = editEmail,
            onValueChange = {
                editEmail = it
            },

            label = { Text(text = "Edytuj e-mail")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkColor,
                unfocusedBorderColor = darkColor)

        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = editPhoneNumber,
            onValueChange = {
                editPhoneNumber = it
            },

            label = { Text(text = "Edytuj numer telefonu")},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "PhoneIcon" )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkColor,
                unfocusedBorderColor = darkColor)

        )


        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick ={

            },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = kGray),

            ) {
            Text(text = "Zapisz",
                style = MaterialTheme.typography.button
            )
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