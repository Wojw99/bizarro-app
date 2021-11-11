package com.example.bizarro.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.*
import com.example.bizarro.util.Dimens


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = true
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray)
            .padding(Dimens.standardPadding)
    ) {
        Column {
            // * * * * * * SEARCH BAR * * * * * * 
            Text(text = "Szukaj")

            Spacer(modifier = Modifier.height(Dimens.standardPadding))

            // * * * * * * FILTER BAR * * * * * *
            Text(text = "Filtry")

            Spacer(modifier = Modifier.height(Dimens.standardPadding))

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            } else {
                RecordList(navController = navController)
            }

            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = viewModel.loadError.value,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                RecordList(navController = navController)
            }

            // * * * * * * EMPTY TEXT * * * * * *
            if (viewModel.recordList.value.isEmpty()
                && !viewModel.isLoading.value
                && viewModel.loadError.value.isEmpty()
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = Strings.listIsEmpty,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                RecordList(navController = navController)
            }

            // * * * * * * BOTTOM OFFSET * * * * * *
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun RecordList(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    LazyColumn() {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            RecordBox(record = recordList[index], navController = navController)
            Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }
    }
}

@Composable
fun RecordBox(
    record: Record,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
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
                    record.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
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
                        text = record.name,
                        style = TextStyle(
                            fontSize = countTextSizeForName(record.name),
                            fontWeight = FontWeight.Normal,
                            color = kBlack,
                        )
                    )

                    // * * * * * * * * TYPE SPECIFIC TITLE * * * * * * * *
                    TypeSpecificTitle(record = record)

                    // * * * * * * * * ADDRESS, DATE * * * * * * * *
                    Text(
                        text = "${record.city}, ${CommonMethods.convertToRecordBoxDateFormat(record.creationDate)}",
                        style = TextStyle(fontSize = 12.sp, color = kGray)
                    )
                }
            }
        }
    }
}

@Composable
fun TypeSpecificTitle(record: Record, modifier: Modifier = Modifier) {
    var h1 = ""
    var h2 = ""
    var h1Size = 20.sp

    when (record.type) {
        "sprzedam" -> {
            h1 = if (record.salePrice != null) {
                CommonMethods.convertToPriceFormat(record.salePrice)
            } else {
                Strings.undefined
            }
            h2 = Strings.sellPrice
        }
        "kupię" -> {
            h1 = if (record.purchasePrice != null) {
                CommonMethods.convertToPriceFormat(record.purchasePrice)
            } else {
                Strings.undefined
            }
            h2 = Strings.purchasePrice
        }
        "wypożyczę" -> {
            h1 = if (record.rentalPeriod != null && record.rentalPrice != null) {
                "${CommonMethods.convertToPriceFormat(record.rentalPrice)}, ${record.rentalPeriod} ${Strings.days}"
            } else {
                Strings.undefined
            }
            h2 = Strings.rentalPeriodPrice
        }
        "zamienię" -> {
            h1 = if (record.swapObject != null) {
                record.swapObject
            } else {
                Strings.undefined
            }
            h2 = Strings.swapObject
            h1Size = 16.sp
        }
    }

    Box() {
        Column() {
            Text(
                text = h1,
                style = TextStyle(
                    fontSize = h1Size,
                    fontWeight = FontWeight.SemiBold,
                    color = kBlack,
                )
            )
            Text(text = h2, style = TextStyle(fontSize = 13.sp))
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