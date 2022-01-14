package com.example.bizarro.ui.screens.compare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.api.models.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.LoadingBox
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
                // * * * * * * * COMPARE LIST * * * * * * *
                if (!viewModel.recordList.value.isEmpty()) {
                    if (viewModel.tableView.value) {
                        CompareTable(navController = navController)
                    } else {
                        CompareList(navController = navController)
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = Dimens.barHeight)
                    ) {
                        Text(
                            text = Strings.listIsEmpty,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // * * * * * * * TOP BAR * * * * * * *
            Row(
                modifier = Modifier.padding(Dimens.standardPadding).fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                FloatingActionButton(
                    onClick = {
                        viewModel.changeView()
                    },
                    backgroundColor = colors.primary,
                    contentColor = colors.background,
                ) {
                    if (viewModel.tableView.value) {
                        Icon(Icons.Filled.Info, "arrow")
                    } else {
                        Icon(Icons.Outlined.Info, "arrow")
                    }
                }

                Spacer(modifier = Modifier.width(Dimens.standardPadding))

                FloatingActionButton(
                    onClick = {
                        viewModel.cleanCompareList()
                    },
                    backgroundColor = colors.primary,
                    contentColor = colors.background,
                ) {
                    Icon(Icons.Filled.Clear, "clear icon")
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
            if (index == 0) {
                CompareTableDescriptions()
            }

            CompareTableItem(
                record = recordList[index],
                onGoClick = {
                    RecordDetailsViewModel.record = recordList[index]
                    RecordDetailsViewModel.userId = recordList[index].userId
                    navController.navigate(Screen.RecordDetails.route)
                },
            )

            if (index == itemCount - 1) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}

@Composable
fun CompareTableDescriptions(
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp / 2
    val spaceSize = 4.dp
    val textStyle = TextStyle(fontWeight = FontWeight.Bold)

    val spacerModifier =
        Modifier
            .height(spaceSize)
            .fillMaxWidth()
            .background(colors.secondaryVariant)

    Box(
        modifier = modifier
            .padding(
                start = spaceSize,
                bottom = Dimens.bottomBarHeight + spaceSize,
                top = spaceSize
            )
    ) {
        Column(
            modifier = Modifier
                .background(colors.background)
                .width(93.dp)
                .height(screenHeight),
        ) {
            // * * * * * IMAGE * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.secondaryVariant)
                    .height(200.dp),
            ) {
            }
            Box(modifier = spacerModifier)

            // * * * * * NAME * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Tytuł",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * HEADER * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Nagłówek",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * TYPE * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Typ",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * DESCRIPTION * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Opis",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * DATE * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Data wystawienia",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * CATEGORY * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Kategoria",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * ADDRESS * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "Adres",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    style = textStyle,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)
        }
    }
}

@Composable
fun CompareTableItem(
    modifier: Modifier = Modifier,
    record: Record,
    onGoClick: () -> Unit = {},
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

    Box(
        modifier = modifier
            .padding(
                start = spaceSize,
                bottom = Dimens.bottomBarHeight + spaceSize,
                top = spaceSize
            )
            .clickable { onGoClick() }
    ) {
        Column(
            modifier = Modifier
                .background(colors.background)
                .width(150.dp)
                .height(screenHeight),
        ) {
            // * * * * * IMAGE * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                var painter = painterResource(id = R.drawable.bike_default)

                if (record.imagePath != null) {
                    painter = rememberImagePainter(CommonMethods.getUrlForImage(record.imagePath))

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
                    modifier = Modifier.fillMaxSize(),
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
                    text = record.name,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * HEADER * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = viewModel.getHeader(record),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * DESCRIPTION * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = record.type,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * DESCRIPTION * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = record.body,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * DATE * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = CommonMethods.convertToRecordBoxDateFormat(record.creationDate),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * CATEGORY * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = record.category,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)

            // * * * * * ADDRESS * * * * *
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                Text(
                    text = "${record.addressCity}, ${record.addressProvince}",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(spaceSize),
                    maxLines = 3,
                    color = colors.onSurface
                )
            }
            Box(modifier = spacerModifier)
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
    viewModel: CompareViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .padding(
                start = Dimens.standardPadding,
                bottom = Dimens.bottomBarHeight + Dimens.standardPadding,
                top = Dimens.standardPadding
            )
            .clickable {
                onGoClick()
            }
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
        }
    }
}