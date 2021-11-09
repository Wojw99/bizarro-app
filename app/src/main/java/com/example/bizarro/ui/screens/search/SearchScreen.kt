package com.example.bizarro.ui.screens.search

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.CommonMethods
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Strings
import com.example.bizarro.util.Values

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray)
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (viewModel.singleRecord.value != null && viewModel.singleRecord2.value != null) {
                RecordBox(
                    entry = viewModel.singleRecord.value!!,
                    navController = navController,
                )
                Spacer(modifier = Modifier.height(10.dp))
                RecordBox(
                    entry = viewModel.singleRecord2.value!!,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RecordBox(
    entry: Record,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Values.CORNER_RADIUS))
            .clip(RoundedCornerShape(Values.CORNER_RADIUS))
            .background(kWhite)
            .height(150.dp)
            .clickable {
                navController.navigate(Screen.RecordDetails.route)
            }
    ) {
        Row() {
            // * * * * * * * * IMAGE BOX * * * * * * * *
            Box(modifier = Modifier.weight(12f), contentAlignment = Alignment.Center) {
                val painter = rememberImagePainter(
                    entry.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
                )

                Image(
                    painter = painter,
                    contentDescription = Strings.recordImage,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )

                if (painter.state is ImagePainter.State.Loading) {
                    CircularProgressIndicator()
                }
            }

            // * * * * * * * * CONTENT BOX * * * * * * * *
            Box(
                modifier = Modifier
                    .weight(11f)
                    .padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    // * * * * * * * * NAME * * * * * * * *
                    Text(
                        text = entry.name,
                        style = TextStyle(
                            fontSize = countTextSizeForName(entry.name),
                            fontWeight = FontWeight.SemiBold,
                            color = kBlack,
                        )
                    )

                    // * * * * * * * * TYPE SPECIFIC TITLE * * * * * * * *
                    Box() {
                        Column() {
                            Text(
                                text = CommonMethods.convertToPriceFormat(entry.salePrice!!),
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = kBlack,
                                )
                            )
                            Text(text = entry.type, style = TextStyle(fontSize = 18.sp))
                        }
                    }

                    // * * * * * * * * ADDRESS, DATE * * * * * * * *
                    Text(
                        text = "${entry.city}, ${CommonMethods.convertToRecordBoxDateFormat(entry.creationDate)}",
                        style = TextStyle(fontSize = 12.sp, color = kGray)
                    )
                }
            }
        }
    }
}

private fun countTextSizeForName(text: String): TextUnit {
    return if (text.length < 30) 16.sp
    else 13.sp
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizarroTheme {
        // SearchScreen()
    }
}