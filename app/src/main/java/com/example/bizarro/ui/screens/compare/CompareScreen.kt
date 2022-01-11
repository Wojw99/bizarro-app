package com.example.bizarro.ui.screens.compare

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.api.models.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.components.RecordBox
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@ExperimentalCoilApi
@Composable
fun CompareScreen(
    navController: NavController,
    viewModel: CompareViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = true

    BizarroTheme(darkTheme = Constants.isDark.value)
    {
        Box {
            // * * * * * * BODY * * * * * *
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.secondaryVariant)
            ) {
                // * * * * * * * TOP BAR * * * * * * *
                Box(
                    modifier = Modifier
                        .height(Dimens.topBarHeight)
                        .fillMaxWidth()
                        .padding(Dimens.standardPadding),
                ) {
//                    Text(
//                        text = "4 / 5",
//                        modifier = Modifier.align(Alignment.Center),
//                        style = TextStyle(
//                            textAlign = TextAlign.Center,
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            color = colors.primaryVariant,
//                        ),
//                    )
//                    Box(modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .fillMaxHeight()
//                        .clickable { viewModel.cleanCompareList() }
//                    ) {
//                        Text(
//                            modifier = Modifier.align(Alignment.Center),
//                            text = Strings.clear,
//                            style = TextStyle(
//                                textAlign = TextAlign.End,
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.SemiBold,
//                                color = colors.primaryVariant
//                            ),
//                        )
//                    }
                }

                // * * * * * * * COMPARE LIST * * * * * * *
                if(!viewModel.recordList.value.isEmpty()) {
                    //CompareTable(navController = navController)
                    CompareList(navController = navController)
                } else {
                    Box(modifier = Modifier.fillMaxSize().padding(bottom = Dimens.barHeight)) {
                        Text(text = Strings.listIsEmpty, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                LoadingBox()
            }
        }
    }


}

@Composable
fun CompareTable(
    navController: NavController,
    viewModel: CompareViewModel = hiltViewModel(),
) {
    LazyRow {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            CompareTableItem(record = recordList[index])
        }
    }
}

@Composable
fun CompareTableItem(
    modifier: Modifier = Modifier,
    record: Record,
    onGoClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    viewModel: CompareViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val spaceSize = 4.dp
    val spacerModifier =
        Modifier
            .height(spaceSize)
            .fillMaxWidth()
            .background(colors.secondaryVariant)

    Box(modifier = Modifier.padding(start = spaceSize)) {
        Column(
            modifier = Modifier
                .background(colors.background)
                .width(screenWidth / 2)
                .height(screenHeight),
        ) {
            // * * * * * NAME * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = record.name,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize)
                )
            }

            Box(modifier = spacerModifier)

            // * * * * * NAME * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = viewModel.getHeader(record),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize)
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * NAME * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = CommonMethods.convertToRecordBoxDateFormat(record.creationDate),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize)
                )
            }
        }
    }
}

@Composable
fun CompareList(
    navController: NavController,
    viewModel: CompareViewModel = hiltViewModel(),
) {
    LazyRow {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            CompareListItem(
                record = recordList[index],
                onGoClick = {
                    RecordDetailsViewModel.record = recordList[index]
                    RecordDetailsViewModel.userId = recordList[index].userId
                    navController.navigate(Screen.RecordDetails.route)
                },
            )

            if (index == recordList.size - 1)
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CompareListItem(
    modifier: Modifier = Modifier,
    record: Record,
    onGoClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    viewModel: CompareViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier.padding(
            start = Dimens.standardPadding,
            bottom = 60.dp + Dimens.standardPadding
        )
    ) {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp

        Box(
            modifier = modifier
                .width(screenWidth - Dimens.standardPadding * 2)
                .fillMaxHeight()
                .background(colors.background, RoundedCornerShape(Dimens.cornerRadius))
        ) {
            // * * * * * BODY * * * * *
            Column() {
                // * * * * * * * * IMAGE * * * * * * * *
                Box(modifier = Modifier.weight(1f)) {
                    var painter = painterResource(id = R.drawable.bike_default)

                    if (record.imagePath != null) {
                        painter = rememberImagePainter(
                            CommonMethods.getUrlForImage(record.imagePath)
                        )
                        if (painter.state is ImagePainter.State.Loading) {
                            CircularProgressIndicator()
                        } else if (painter.state is ImagePainter.State.Error) {
                            painter = painterResource(id = R.drawable.bike_default)
                        }
                    }

                    Image(
                        painter = painter,
                        contentDescription = Strings.recordImage,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(Dimens.cornerRadius)),
                    )
                }

                // * * * * * * * * DESCRIPTION * * * * * * * *
                Column(
                    modifier = Modifier.padding(
                        horizontal = Dimens.standardPadding * 2,
                        vertical = Dimens.standardPadding,
                    )
                ) {
                    // * * * * * * * * TITLE * * * * * * * *
                    Text(
                        text = record.name,
                        style = TextStyle(fontSize = 18.sp, color = colors.onSurface),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = viewModel.getHeader(record),
                        style = TextStyle(
                            fontSize = 26.sp,
                            color = colors.onSurface,
                            fontWeight = FontWeight.Bold,

                            ),
                    )

                    Text(
                        text = viewModel.getLabel(record),
                        style = TextStyle(fontSize = 16.sp, color = colors.onSurface),
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    val style1 = TextStyle(
                        fontSize = 15.sp,
                        color = colors.onSurface,
                    )
                    val style2 = TextStyle(
                        fontSize = 19.sp,
                        color = colors.onSurface,
                        fontWeight = FontWeight.Bold,
                    )
                    val spacerHeight = 8.dp

                    // * * * * * * * * DATE * * * * * * * *
                    Text(text = Strings.added, style = style1)
                    Text(
                        text = CommonMethods.convertToLabelDateFormat(record.creationDate),
                        style = style2,
                        color = colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    // * * * * * * * * CATEGORY * * * * * * * *
                    Text(text = Strings.category, style = style1, color = colors.onSurface)
                    Text(
                        text = record.category,
                        style = style2,
                        color = colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    // * * * * * * * * PROVINCE * * * * * * * *
                    Text(text = Strings.province, style = style1, color = colors.onSurface)
                    Text(
                        text = record.addressProvince,
                        style = style2,
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))

                    // * * * * * * * * ADDRESS * * * * * * * *
                    Text(text = Strings.address, style = style1, color = colors.onSurface)
                    Text(
                        text = "${record.addressCity}, ${record.addressStreet} ${record.addressNumber}",
                        style = style2,
                        color = colors.onSurface
                    )
                }
            }

            // * * * * * BUTTONS * * * * *
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.standardPadding),
                horizontalArrangement = Arrangement.End,
            ) {
//                IconButton(
//                    onClick = {
//                        onDeleteClick()
//                    },
//                    modifier = Modifier.background(kBlueDark, shape = CircleShape),
//                ) {
//                    Icon(Icons.Default.Delete, contentDescription = Strings.delete, tint = kWhite)
//                }
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                IconButton(
                    onClick = {
                        onGoClick()
                    },
                    modifier = Modifier.background(kBlueDark, shape = CircleShape),
                ) {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = Strings.details,
                        tint = kWhite
                    )
                }
            }
        }
    }

}