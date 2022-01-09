package com.example.bizarro.ui.screens.user_profile.other_user_profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.R
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@Composable
fun AddOpinionScreen(
    navController: NavController,
    viewModel: OtherUserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    viewModel.appState.bottomMenuVisible.value = false

    if(viewModel.isSuccess.value) {
        viewModel.isSuccess.value = false
        navController.popBackStack()
    }

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Box{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //HeaderSectionAddOpinion(navController)
                Spacer(modifier = Modifier.padding(top = Dimens.topBarHeight))

//                Text(
//                    "Dodaj opinię o użytkowniku ${viewModel.nameUser}!",
//                    style = TextStyle(
//                        textAlign = TextAlign.Center,
//                        fontSize = 21.sp,
//                        fontFamily = FontFamily.Serif,
//                    ),
//                    color = colors.onSurface,
//                    modifier = Modifier.padding(horizontal = 24.dp),
//                )

                BikerImage()

                // * * * * * * ADD OPINION TO USER * * * * * *
                if (!viewModel.isLoading.value && viewModel.loadError.value.isEmpty()) {
                    //RecordList(navController = navController)
                    RadioButtonDemo()
                }
            }

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
                    Button(onClick = {
                        viewModel.getOtherUserProfile()
                    }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator()
            }

            // * * * * * * TOP BAR * * * * * *
            TopBar(
                navController = navController,
                title = "Dodaj opinię",
                modifier = Modifier.background(colors.background)
            )

            // * * * * * * ADD BUTTON * * * * * *
            Button(
                onClick = {
                    when {
                        viewModel.selectedReview.value == "" -> {
                            Toast.makeText(context, "Ocena jest wymagana", Toast.LENGTH_SHORT).show()
                        }
                        viewModel.textOpinion.value == "" -> {
                            Toast.makeText(context, "Komentarz jest wymagany", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            viewModel.addOpinion()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(Dimens.standardPadding),
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),
            ) {
                Text(
                    text = Strings.confirm,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.background,
                    )
                )
            }
        }
    }
}

@Composable
fun RadioButtonDemo(
    viewModel: OtherUserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Text("Zaznacz ocenę w stopniach od 1 do 5:", color = MaterialTheme.colors.onSurface)
    Spacer(modifier = Modifier.size(16.dp))
    Row {
        RadioButton(
            selected = viewModel.selectedReview.value == Review.review1, onClick = {
                viewModel.selectedReview.value = Review.review1
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.onSurface,
                disabledColor = colors.onSurface,
                unselectedColor = colors.onSurface
            )
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(Review.review1, color = MaterialTheme.colors.onSurface)

        Spacer(modifier = Modifier.size(16.dp))

        RadioButton(
            selected = viewModel.selectedReview.value == Review.review2, onClick = {
                viewModel.selectedReview.value = Review.review2
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.onSurface,
                disabledColor = colors.onSurface,
                unselectedColor = colors.onSurface
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(Review.review2, color = MaterialTheme.colors.onSurface)

        Spacer(modifier = Modifier.size(16.dp))

        RadioButton(
            selected = viewModel.selectedReview.value == Review.review3, onClick = {
                viewModel.selectedReview.value = Review.review3
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.onSurface,
                disabledColor = colors.onSurface,
                unselectedColor = colors.onSurface
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(Review.review3, color = MaterialTheme.colors.onSurface)

        Spacer(modifier = Modifier.size(16.dp))

        RadioButton(
            selected = viewModel.selectedReview.value == Review.review4,
            onClick = {
                viewModel.selectedReview.value = Review.review4
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.onSurface,
                disabledColor = colors.onSurface,
                unselectedColor = colors.onSurface
            ),
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(Review.review4, color = MaterialTheme.colors.onSurface)

        Spacer(modifier = Modifier.size(16.dp))

        RadioButton(
            selected = viewModel.selectedReview.value == Review.review5, onClick = {
                viewModel.selectedReview.value = Review.review5
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.onSurface,
                disabledColor = colors.onSurface,
                unselectedColor = colors.onSurface
            )
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(Review.review5, color = MaterialTheme.colors.onSurface)

        Spacer(modifier = Modifier.height(50.dp))

    }

    OutlinedTextField(
        value = viewModel.textOpinion.value,
        onValueChange = { text ->
            viewModel.textOpinion.value = text
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.standardPadding),
        placeholder = {
            Text(
                text = "Wpisz swój komentarz",
                color = MaterialTheme.colors.onSurface
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        ),
    )

    Spacer(modifier = Modifier.height(Dimens.topBarHeight))
}

@Composable
fun BikerImage(
    viewModel: OtherUserViewModel = hiltViewModel(),
) {
    val painter = if (viewModel.selectedReview.value == Review.review1) {
        painterResource(id = R.drawable.opinion_1)
    } else if (viewModel.selectedReview.value == Review.review2) {
        painterResource(id = R.drawable.opinion_2)
    } else if (viewModel.selectedReview.value == Review.review3) {
        painterResource(id = R.drawable.opinion_3)
    } else if (viewModel.selectedReview.value == Review.review4) {
        painterResource(id = R.drawable.opinion_4)
    } else {
        painterResource(id = R.drawable.opinion_5)
    }
    Image(
        painter,
        contentDescription = "biker image",
        modifier = Modifier.padding(Dimens.standardPadding * 2),
    )
}

object Review {
    const val review1 = "1"
    const val review2 = "2"
    const val review3 = "3"
    const val review4 = "4"
    const val review5 = "5"
}

