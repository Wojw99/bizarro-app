package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
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
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen

@Composable
fun AddOpinionScreen(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {


        HeaderSectionAddOpinion(navController)

        //Spacer(modifier = Modifier.height(80.dp))

        Text("Dodaj swoją opinię!",
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(80.dp))

        AddOpinionSection()




    }
}




@Composable
fun HeaderSectionAddOpinion(navController: NavController)
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


@Composable
fun AddOpinionSection()
{



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {



        var expanded by remember { mutableStateOf(false) }
        val suggestions = listOf("Item1", "Item2", "Item3")

        Button(onClick = { expanded = !expanded }){
            Text ("DropDown")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //do something ...
                }) {
                    Text(text = label)
                }
            }
        }


        var textOpinion by remember { mutableStateOf(TextFieldValue("")) }

        TextField(
            value = textOpinion,
            onValueChange = {
                textOpinion = it },

            label = { Text(text = "Komentarz") },
            placeholder = { Text(text = "Wpisz swój komentarz") },

            modifier = Modifier.align(Alignment.CenterEnd))


        
    }


}