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

        Text("Dodaj swoją opinię!",
            style = TextStyle(
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text("Jak oceniasz?",
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold
            ))

        Spacer(modifier = Modifier.height(40.dp))


        RadioButtonDemo()

        Spacer(modifier = Modifier.height(50.dp))

        var textOpinion by remember { mutableStateOf(TextFieldValue("")) }

        TextField(
            value = textOpinion,
            onValueChange = {
                textOpinion = it },

            label = { Text(text = "Komentarz do oceny") },
            placeholder = { Text(text = "Wpisz swój komentarz") },

            //modifier = Modifier.align(Alignment.Horizontal)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick ={

                //navController.navigate(route = com.example.bizarro.ui.Screen.Home.route)

                     },
            Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),

            ) {
            Text(text = "Dodaj komentarz",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            )
        }


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
fun RadioButtonDemo() {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
        val selectedReview = remember { mutableStateOf("") }
        Text("Zaznacz ocenę w stopniach od 1 do 5:")
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            RadioButton(selected = selectedReview.value == Review.review1, onClick = {
                selectedReview.value = Review.review1
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Review.review1)

            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(selected = selectedReview.value == Review.review2, onClick = {
                selectedReview.value = Review.review2
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Review.review2)

            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(selected = selectedReview.value == Review.review3, onClick = {
                selectedReview.value = Review.review3
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Review.review3)

            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(selected = selectedReview.value == Review.review4, onClick = {
                selectedReview.value = Review.review4
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Review.review4)

            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(selected = selectedReview.value == Review.review5, onClick = {
                selectedReview.value = Review.review5
            })
            Spacer(modifier = Modifier.size(16.dp))
            Text(Review.review5)




        }
    }
//}
object Review {
    const val review1 = "1"
    const val review2 = "2"
    const val review3 = "3"
    const val review4 = "4"
    const val review5 = "5"
}

