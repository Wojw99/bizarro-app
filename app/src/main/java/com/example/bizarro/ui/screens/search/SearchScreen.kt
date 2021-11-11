package com.example.bizarro.ui.screens.search

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
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
import com.example.bizarro.util.Strings


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
        // * * * * * * PROGRESS BAR * * * * * *
        if (viewModel.isLoading.value) {
            Box(modifier = Modifier.fillMaxSize().background(kLightGray)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        // * * * * * * ERROR TEXT * * * * * *
        if (viewModel.loadError.value.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize().background(kLightGray)) {
                Text(
                    text = viewModel.loadError.value,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // * * * * * * EMPTY TEXT * * * * * *
        if (viewModel.recordList.value.isEmpty()
            && !viewModel.isLoading.value
            && viewModel.loadError.value.isEmpty()
        ) {
            Box(modifier = Modifier.fillMaxSize().background(kLightGray)) {
                Text(
                    text = Strings.listIsEmpty,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // * * * * * * RECORD LIST * * * * * *
        if (viewModel.recordList.value.isNotEmpty() && !viewModel.isLoading.value) {
            RecordList(navController = navController)
        }

        Column {
            // * * * * * * SEARCH BAR * * * * * *
            SearchBar(
                hint = Strings.search,
                onSearch = { text ->
                    viewModel.nameText.value = text
                    viewModel.updateRecordList()
                },
            )

            Spacer(modifier = Modifier.height(Dimens.standardPadding))

            // * * * * * * FILTER BAR * * * * * *
            Text(text = "Filtry")

            Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }
    }

}

@Composable
fun SearchBar(
    hint: String = "",
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .background(kWhite, RoundedCornerShape(Dimens.cornerRadius))
            .height(40.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize(),
        ) {
            //if (text.isEmpty()) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(Icons.Default.Search, "Icon here", tint = kGray)
            //}

            Spacer(modifier = Modifier.width(10.dp))

            Box {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        onSearch(it)
                        isHintDisplayed = it.isEmpty()
                    },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = kBlack, fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardActions = KeyboardActions(onDone = {
                        onSearch(text)
                    })

                )
                if (isHintDisplayed) {
                    Text(color = kGray, text = hint, modifier = Modifier.fillMaxWidth())
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun RecordList(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    LazyColumn(modifier = modifier.background(kLightGray)) {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            if (index == 0)
                Spacer(modifier = Modifier.height(64.dp))

            RecordBox(record = recordList[index], navController = navController)

            if (index == recordList.size - 1)
                Spacer(modifier = Modifier.height(64.dp))
            else
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