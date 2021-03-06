package com.example.bizarro.ui.screens.record_details

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.add_record.AddRecordViewModel
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.CommonMethods
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

    BizarroTheme(darkTheme = viewModel.appState.isDarkTheme.value)
    {
        val backgroundColor = if (viewModel.appState.isDarkTheme.value) colors.background else kVeryLightGray
        val context = LocalContext.current

        Box {
            // * * * * * * BODY * * * * * *
            if (viewModel.loadError.value.isEmpty()) {
                RecordDetailsBody(
                    navController = navController,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .padding(top = Dimens.topBarHeight)
                        .verticalScroll(rememberScrollState()),
                )
            }

            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty()
                && !viewModel.isLoading.value
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colors.background),

                    ) {
                    Text(
                        text = viewModel.loadError.value,
                        color = colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = {
                        viewModel.updateProfileInfo()
                        viewModel.updateRecordInfo()
                    }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }

            // * * * * * * TOP BAR * * * * * *
            TopBar(
                navController = navController,
                title = viewModel.topBarTitle.value,
                modifier = Modifier
                    .background(colors.background)
                    .align(Alignment.TopCenter),
                actions =
                if (viewModel.isCurrentUser.value)
                    listOf(
                        TopBarAction(
                            onClick = {
                                AddRecordViewModel.record = RecordDetailsViewModel.record
                                navController.navigate(Screen.AddRecord.route)
                            },
                            icon = Icons.Default.Edit,
                            contentDescription = Strings.edit,
                        ),
                        TopBarAction(
                            onClick = {
                                viewModel.deleteRecord()
                            },
                            icon = Icons.Default.Delete,
                            contentDescription = Strings.delete,
                        )
                    )
                else if (!viewModel.recordInCompareList.value) listOf(
                    TopBarAction(
                        onClick = {
                            viewModel.addToCompareList()
                            Toast.makeText(
                                context,
                                Strings.addToCompareSuccessMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        icon = Icons.Default.FavoriteBorder,
                        contentDescription = "favorite border icon",
                    )
                )
                else if (viewModel.recordInCompareList.value) listOf(
                    TopBarAction(
                        onClick = {
                            viewModel.removeFromCompareList()
                            Toast.makeText(
                                context,
                                Strings.removeFromCompareSuccessMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        icon = Icons.Default.Favorite,
                        contentDescription = "favorite icon",
                    )
                )
                else listOf(),
                onTitleClick = {
                    if (!viewModel.isCurrentUser.value) {
                        OtherUserViewModel.otherUserId = RecordDetailsViewModel.userId!!
                        navController.navigate(Screen.OtherUserProfile.route)
                    }
                }
            )

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                LoadingBox()
            }

            // * * * * * * SUCCESS DIALOG * * * * * *
            if (viewModel.successfullyDeleted.value) {
                ConfirmAlertDialog(
                    onDismiss = {
                        navController.popBackStack()
                    },
                    title = Strings.success2,
                    body = Strings.success,
                )
            }
        }
    }
}

@Composable
fun RecordDetailsBody(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RecordDetailsViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        // * * * * * * * * IMAGE * * * * * * * *
        Box {
            var painter = painterResource(id = R.drawable.bike_default)

            if (viewModel.recordImagePath.value != null) {
                painter = rememberImagePainter(
                    CommonMethods.getUrlForImage(viewModel.recordImagePath.value!!)
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
                    .fillMaxWidth()
                    .height(300.dp),
            )
        }

        // * * * * * * * * TITLE SECTION * * * * * * * *
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, RoundedCornerShape(0.dp, 0.dp, Dimens.cornerRadius, Dimens.cornerRadius))
                .clip(RoundedCornerShape(0.dp, 0.dp, Dimens.cornerRadius, Dimens.cornerRadius))
                .background(colors.surface),
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Dimens.standardPadding * 2,
                    vertical = Dimens.standardPadding
                )
            ) {
                Text(
                    text = viewModel.recordName.value,
                    style = TextStyle(fontSize = 20.sp, color = colors.onSurface),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = viewModel.recordHeader.value,
                    style = TextStyle(
                        fontSize = 36.sp,
                        color = colors.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                )

                Text(
                    text = viewModel.recordLabel.value,
                    style = TextStyle(fontSize = 18.sp, color = colors.onSurface),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = viewModel.recordCreationDateLabel.value,
                    style = TextStyle(fontSize = 16.sp, color = colors.onSurface),
                )
            }
        }

        // * * * * * * * * SPACER * * * * * * * *
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )

        val sectionsPaddingHorizontal = 24.dp
        val sectionsPaddingVertical = 12.dp
        val sectionsTextModifier = Modifier.padding(horizontal = 0.dp)

        // * * * * * * DESCRIPTION * * * * * *
        Column(
            modifier = Modifier.padding(
                horizontal = sectionsPaddingHorizontal,
                vertical = sectionsPaddingVertical
            )
        ) {
            val iconSize = 27.dp
            Row {
                Icon(
                    Icons.Outlined.Info,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize),
                    tint = colors.onSurface
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.description,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = viewModel.recordBody.value,
                style = TextStyle(color = colors.onSurface, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * OPINIONS * * * * * *
        Column(
            modifier = Modifier.padding(
                horizontal = sectionsPaddingHorizontal,
                vertical = sectionsPaddingVertical
            )
        ) {
            val iconSize = 27.dp
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.Star,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize),
                    tint = colors.onSurface
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.opinions,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.weight(1f)
                )
                if (!viewModel.isCurrentUser.value) {
                    // Show opinion button only if record is not own by the logged user
                    Box(
                        modifier = Modifier
                            .background(
                                colors.primaryVariant,
                                RoundedCornerShape(Dimens.cornerRadius)
                            )
                            .clickable {
                                OtherUserViewModel.otherUserId = RecordDetailsViewModel.userId!!
                                navController.navigate(Screen.AddOpinion.route)
                            }
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 40.dp, vertical = 3.dp),
                            text = Strings.rate,
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = kWhite,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = viewModel.recordGeneralOpinion.value,
                style = TextStyle(
                    color = if (viewModel.appState.isDarkTheme.value) kBlueLight else kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            Text(
                text = CommonMethods.getDescriptionForNumberMark(viewModel.recordGeneralOpinionNumber.value),
                style = TextStyle(color = colors.onSurface, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * CATEGORY * * * * * *
        Column(
            modifier = Modifier.padding(
                horizontal = sectionsPaddingHorizontal,
                vertical = sectionsPaddingVertical
            )
        ) {
            val iconSize = 27.dp
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.Settings,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize),
                    tint = colors.onSurface
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.category,
                    style = TextStyle(
                        color = colors.onSurface,
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
                    color = if (viewModel.appState.isDarkTheme.value) kBlueLight else kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            Text(
                text = CommonMethods.getDescriptionForCategory(viewModel.recordCategory.value),
                style = TextStyle(color = colors.onSurface, fontSize = 18.sp),
                modifier = sectionsTextModifier,
            )
        }

        // * * * * * * ADDRESS * * * * * *
        Column(
            modifier = Modifier.padding(
                horizontal = sectionsPaddingHorizontal,
                vertical = sectionsPaddingVertical
            )
        ) {
            val iconSize = 27.dp
            // * * * HEADER * * *
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(
                    Icons.Outlined.LocationOn,
                    contentDescription = Strings.info,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize),
                    tint = colors.onSurface
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                Text(
                    text = Strings.address,
                    style = TextStyle(
                        color = colors.onSurface,
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
                    color = if (viewModel.appState.isDarkTheme.value) kBlueLight else kBlueDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = sectionsTextModifier,
            )

            // * * * MAP * * *
            // Spacer(modifier = Modifier.height(64.dp))
        }


        // * * * * * * * * * *
    }
}