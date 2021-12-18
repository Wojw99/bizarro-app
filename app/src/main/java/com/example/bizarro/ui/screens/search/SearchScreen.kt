package com.example.bizarro.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.bizarro.ui.components.CustomIconButton
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.*
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings

val topRecordListMargin = 115.dp

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = true

    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(kLightGray)
                .padding(horizontal = Dimens.standardPadding)
        ) {
            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty()
                && !viewModel.isLoading.value) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = viewModel.loadError.value,
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = { viewModel.updateRecordList() }) {
                        Text(
                            text = Strings.refresh,
                        )
                    }
                }
            }

            // * * * * * * EMPTY TEXT * * * * * *
            if (viewModel.recordList.value.isEmpty()
                && !viewModel.isLoading.value
                && viewModel.loadError.value.isEmpty()
            ) {
                Text(
                    text = Strings.listIsEmpty,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // * * * * * * RECORD LIST * * * * * *
            if (viewModel.recordList.value.isNotEmpty() && !viewModel.isLoading.value) {
                RecordList(navController = navController)
            }

            Column(modifier = Modifier.padding(vertical = Dimens.standardPadding)) {
                // * * * * * * SEARCH BAR * * * * * *
                SearchBar(
                    hint = Strings.search,
                    onSearch = { text ->
                        viewModel.nameText.value = text
                        viewModel.updateRecordList()
                    },
                    initialText = viewModel.nameText.value,
                )

                Spacer(modifier = Modifier.height(Dimens.standardPadding))

                // * * * * * * FILTER BAR * * * * * *
                if (viewModel.hasFilters()) {
                    FilterList(navController = navController)
                } else {
                    CustomIconButton(
                        text = Strings.filter,
                        iconVector = Icons.Default.Add,
                        contentDescription = "Add icon",
                        reverseColors = true,
                        onButtonClick = {
                            navController.navigate(Screen.Filter.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(Dimens.standardPadding))
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}


@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    initialText: String = "",
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf(initialText)
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "" && initialText == "")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .background(kWhite, RoundedCornerShape(Dimens.cornerRadius))
            .height(Dimens.barHeight)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Icon(Icons.Default.Search, "Icon here", tint = kGray)

            Spacer(modifier = Modifier.width(10.dp))

            Box {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        isHintDisplayed = it.isEmpty()
                    },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = kBlack, fontSize = 16.sp),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onSearch(text)
                    }),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
fun FilterList(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Row {
        LazyRow {
            val filterList = viewModel.filterList.value
            val itemCount = filterList.size

            items(itemCount) { index ->
                // * * * * * ADD FILTER * * * * *
                if (index == 0) {
                    CustomIconButton(
                        text = "",
                        iconVector = Icons.Default.Add,
                        contentDescription = "Add filer",
                        reverseColors = true,
                        onButtonClick = {
                            navController.navigate(Screen.Filter.route)
                        },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }

                // * * * * * FILTER BUTTON * * * * *
                val filter = filterList[index]
                if (filter.values.isNotEmpty()) {
                    CustomIconButton(
                        text = filter.values.first(),
                        iconVector = Icons.Default.Close,
                        contentDescription = "Close icon",
                        onButtonClick = {
                            if (!viewModel.isLoading.value) {
                                viewModel.clearFilterAtIndex(index)
                                viewModel.updateRecordList()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}

@Composable
fun RecordList(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
) {
    LazyColumn(modifier = modifier.background(kLightGray)) {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            if (index == 0)
                Spacer(modifier = Modifier.height(topRecordListMargin))

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
        Row {
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
        Constants.TYPE_SELL -> {
            h1 = if (record.salePrice != null) {
                CommonMethods.convertToPriceFormat(record.salePrice)
            } else {
                Strings.undefined
            }
            h2 = Strings.sellPrice
        }
        Constants.TYPE_BUY -> {
            h1 = if (record.purchasePrice != null) {
                CommonMethods.convertToPriceFormat(record.purchasePrice)
            } else {
                Strings.undefined
            }
            h2 = Strings.purchasePrice
        }
        Constants.TYPE_RENT -> {
            h1 = if (record.rentalPeriod != null && record.rentalPrice != null) {
                "${CommonMethods.convertToPriceFormat(record.rentalPrice)} - ${record.rentalPeriod} ${Strings.days}"
            } else {
                Strings.undefined
            }
            h2 = Strings.rentalPeriodPrice
        }
        Constants.TYPE_SWAP -> {
            h1 = if (record.swapObject != null) {
                record.swapObject
            } else {
                Strings.undefined
            }
            h2 = Strings.swapObject
            h1Size = 16.sp
        }
    }

    Box {
        Column {
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