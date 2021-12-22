package com.example.bizarro.ui.screens.record_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.add_record.AddRecordViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@Composable
fun RecordDetailsScreen(
    navController: NavController,
    viewModel: RecordDetailsViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    Box {
        // * * * * * * BODY * * * * * *
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(kWhite)
                .padding(top = Dimens.topBarHeight)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty()
                && !viewModel.isLoading.value
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = viewModel.loadError.value,
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = {
                        viewModel.updateProfileInfo()
                        viewModel.updateRecordInfo()
                    }) {
                        Text(
                            text = Strings.refresh,
                        )
                    }
                }
            }

            // * * * * * * BODY * * * * * *
            if (!viewModel.isLoading.value && viewModel.loadError.value.isEmpty()) {
                RecordDetailsBody()
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        // * * * * * * TOP BAR * * * * * *
        TopBar(
            navController = navController,
            title = viewModel.topBarTitle.value,
            modifier = Modifier
                .background(kWhite)
                .align(Alignment.TopCenter),
            actions = if (viewModel.editActionVisible.value)
                listOf(
                    TopBarAction(
                        onClick = {
                            AddRecordViewModel.record = RecordDetailsViewModel.record
                            navController.navigate(Screen.AddRecord.route)
                        },
                        icon = Icons.Default.Edit,
                        contentDescription = Strings.edit,
                    )
                ) else listOf(),
        )
    }
}

@Composable
fun RecordDetailsBody(
    viewModel: RecordDetailsViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        // * * * * * * * * IMAGE * * * * * * * *
        Box() {
            val painter = rememberImagePainter(
                viewModel.recordImagePath.value
            )

            Image(
                painter = painter,
                contentDescription = Strings.recordImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            )

            if (painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
        }

        // * * * * * * * * TITLE SECTION * * * * * * * *
        Column(
            modifier = Modifier.padding(
                horizontal = Dimens.standardPadding * 2,
                vertical = Dimens.standardPadding
            )
        ) {
            Text(
                text = viewModel.recordName.value,
                style = TextStyle(fontSize = 20.sp, color = kGray),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = viewModel.recordHeader.value,
                style = TextStyle(fontSize = 36.sp, color = kBlack, fontWeight = FontWeight.Bold),
            )

            Text(
                text = viewModel.recordLabel.value,
                style = TextStyle(fontSize = 18.sp, color = kBlack),
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = viewModel.recordCreationDateLabel.value,
                style = TextStyle(fontSize = 16.sp, color = kGray),
            )
        }

        // * * * * * * * * SPACER * * * * * * * *
        Box(
            modifier = Modifier
                .height(25.dp)
                .fillMaxWidth()
                .background(kLightGray)
        )

        val sectionsPadding = 24.dp
        val sectionsTextModifier = Modifier.padding(horizontal = 0.dp)
        // * * * * * * DESCRIPTION * * * * * *
        Column(
            modifier = Modifier.padding(sectionsPadding)
        ) {
            val iconSize = 27.dp
            Row {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.description,
                    style = TextStyle(
                        color = kBlack,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = viewModel.recordBody.value,
                style = TextStyle(color = kGray, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * OPINIONS * * * * * *
        Column(
            modifier = Modifier.padding(sectionsPadding)
        ) {
            val iconSize = 27.dp
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.Star,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.opinions,
                    style = TextStyle(
                        color = kBlack,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .background(kBlueDark, RoundedCornerShape(Dimens.cornerRadius))
                        .padding(horizontal = 40.dp, vertical = 3.dp)

                ) {
                    Text(
                        text = Strings.rate,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = kWhite,
                            fontWeight = FontWeight.SemiBold
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = viewModel.recordGeneralOpinion.value,
                style = TextStyle(
                    color = kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            Text(
                text = viewModel.recordGeneralOpinionDesc.value,
                style = TextStyle(color = kGray, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * CATEGORY * * * * * *
        Column(
            modifier = Modifier.padding(sectionsPadding)
        ) {
            val iconSize = 27.dp
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.category,
                    style = TextStyle(
                        color = kBlack,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = viewModel.recordCategory.value,
                style = TextStyle(
                    color = kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            Text(
                text = viewModel.recordCategoryDesc.value,
                style = TextStyle(color = kGray, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * ADDRESS * * * * * *
        Column(
            modifier = Modifier.padding(sectionsPadding)
        ) {
            val iconSize = 27.dp
            // * * * HEADER * * *
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.LocationOn,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.address,
                    style = TextStyle(
                        color = kBlack,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = viewModel.recordAddress.value,
                style = TextStyle(
                    color = kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            // * * * MAP * * *
        }


        // * * * * * * * * * *
    }
}