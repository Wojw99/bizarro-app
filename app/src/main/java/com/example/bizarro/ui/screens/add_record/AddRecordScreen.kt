package com.example.bizarro.ui.screens.add_record

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bizarro.ui.components.*
import com.example.bizarro.ui.screens.filter.headerModifier
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

val textFieldModifier = Modifier.fillMaxWidth()

@ExperimentalComposeUiApi
@Composable
fun AddRecordScreen(
    viewModel: AddRecordViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = false

    val context = LocalContext.current

    val headerStyle = TextStyle(
        color = kBlack,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    )

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult

        viewModel.imageUri.value = uri

        if (Build.VERSION.SDK_INT < 28) {
            viewModel.imageBitmap.value =
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, uri!!)
            viewModel.imageBitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    BizarroTheme(darkTheme = Constants.isDark.value)
    {
        Box {
            // * * * * * * BODY * * * * * *
            Column(
                modifier = Modifier
                    .background(colors.background)
                    .padding(horizontal = Dimens.standardPadding * 2)
                    .padding(top = Dimens.topBarHeight)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // * * * * * RECORD TITLE * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.recordTitle,
                    style = headerStyle,
                    color = colors.onSurface
                )
                CustomOutlinedTextField(
                    value = viewModel.titleText.value,
                    onValueChange = { viewModel.titleText.value = it },
                    placeholderText = Strings.title,
                    keyboardType = KeyboardType.Text,
                    modifier = textFieldModifier,
                )

                // * * * * * RECORD DESCRIPTION * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.recordDescription,
                    style = headerStyle,
                    color = colors.onSurface
                )
                CustomOutlinedTextField(
                    value = viewModel.descriptionText.value,
                    onValueChange = { viewModel.descriptionText.value = it },
                    placeholderText = Strings.recordDescription,
                    keyboardType = KeyboardType.Text,
                    modifier = textFieldModifier,
                )

                // * * * * * RECORD TYPE * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.recordType,
                    style = headerStyle,
                    color = colors.onSurface
                )
                RadioGroup(
                    selectedLabel = viewModel.selectedType,
                    labels = viewModel.typeLabels
                )

                // * * * * * TYPE DEPENDENT SECTION * * * * *
                TypeDependentSection()

                // * * * * * CATEGORY * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.category,
                    style = headerStyle,
                    color = colors.onSurface
                )
                RadioGroup(
                    selectedLabel = viewModel.selectedCategory,
                    labels = viewModel.categoryLabels
                )

                // * * * * * PROVINCE * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.province,
                    style = headerStyle,
                    color = colors.onSurface
                )
                RadioGroup(
                    selectedLabel = viewModel.selectedProvince,
                    labels = viewModel.provinceLabels
                )

                // * * * * * ADDRESS * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.address,
                    style = headerStyle,
                    color = colors.onSurface
                )
                CustomOutlinedTextField(
                    value = viewModel.cityText.value,
                    onValueChange = { viewModel.cityText.value = it },
                    labelText = Strings.city,
                    keyboardType = KeyboardType.Text,
                    modifier = textFieldModifier,
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomOutlinedTextField(
                    value = viewModel.streetText.value,
                    onValueChange = { viewModel.streetText.value = it },
                    labelText = Strings.street,
                    keyboardType = KeyboardType.Text,
                    modifier = textFieldModifier,
                )
                Spacer(modifier = Modifier.height(8.dp))
                CustomOutlinedTextField(
                    value = viewModel.numberText.value,
                    onValueChange = { viewModel.numberText.value = it },
                    labelText = Strings.number,
                    keyboardType = KeyboardType.Text,
                    modifier = textFieldModifier,
                )

                // * * * * * PHOTO * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.photo,
                    style = headerStyle,
                    color = colors.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (viewModel.imageUri.value != null) {
                    val painter = rememberImagePainter(viewModel.imageUri.value)
                    Image(
                        painter = painter,
                        contentDescription = Strings.photo,
                        modifier = textFieldModifier.height(250.dp),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        // launch only for images
                        launcher.launch("image/*")
                    },
                    modifier = textFieldModifier,
                ) {
                    Text(text = Strings.select)
                }

                Spacer(modifier = Modifier.height(128.dp))
            }


            // * * * * * ACCEPT BUTTON * * * * *
            Button(
                onClick = {
                    viewModel.confirm(context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(Dimens.standardPadding)
            ) {
                Text(text = Strings.confirm, color = kWhite)
            }

            // * * * * * * TOP BAR * * * * * *
            TopBar(
                navController = navController,
                title = if (viewModel.isEditScreen.value) Strings.editRecord else Strings.addRecord,
                actions = listOf(
                    TopBarAction(
                        onClick = { viewModel.cleanStates() },
                        icon = Icons.Default.Clear,
                        contentDescription = Strings.clearFilters,
                    ),
                ),
                modifier = Modifier
                    .background(colors.background)
                    .align(Alignment.TopCenter)
            )

            // * * * * * * ERROR DIALOG * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                ConfirmAlertDialog(
                    onDismiss = { viewModel.clearError() },
                    title = Strings.error,
                    body = viewModel.loadError.value,
                )
            }

            // * * * * * * SUCCESS DIALOG * * * * * *
            if (viewModel.isSuccess.value) {
                ConfirmAlertDialog(
                    onDismiss = {
                        navController.popBackStack()
                    },
                    title = Strings.success2,
                    body = Strings.success,
                )
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                LoadingBox()
            }
        }
    }


}


@ExperimentalComposeUiApi
@Composable
fun TypeDependentSection(
    modifier: Modifier = Modifier,
    viewModel: AddRecordViewModel = hiltViewModel()
) {
    val type = viewModel.selectedType.value
    val headerStyle = TextStyle(
        color = kBlack,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    )

    Column(modifier = modifier) {
        // * * * * * PRICE MIN-MAX HEADER * * * * *
        if (type == Constants.TYPE_SELL) {
            Text(
                modifier = headerModifier,
                text = Strings.sellPriceHeader,
                style = headerStyle,
                color = colors.onSurface
            )
        } else if (type == Constants.TYPE_BUY) {
            Text(
                modifier = headerModifier,
                text = Strings.buyPriceHeader,
                style = headerStyle,
                color = colors.onSurface
            )
        } else if (type == Constants.TYPE_RENT) {
            Text(
                modifier = headerModifier,
                text = Strings.rentHeader1,
                style = headerStyle,
                color = colors.onSurface
            )
        }

        // * * * * * PRICE MIN-MAX TEXT FIELD * * * * *
        if (type == Constants.TYPE_SELL || type == Constants.TYPE_BUY || type == Constants.TYPE_RENT) {
            CustomOutlinedTextField(
                value = viewModel.priceText.value,
                onValueChange = { viewModel.priceText.value = it },
                placeholderText = Strings.price,
                keyboardType = KeyboardType.Number,
                modifier = textFieldModifier,
            )
        }

        // * * * * * ADDITIONAL RENT * * * * *
        if (type == Constants.TYPE_RENT) {
            Text(
                modifier = headerModifier,
                text = Strings.rentHeader2,
                style = headerStyle,
                color = colors.onSurface
            )
            CustomOutlinedTextField(
                value = viewModel.rentPeriodText.value,
                onValueChange = { viewModel.rentPeriodText.value = it },
                placeholderText = Strings.rentHint,
                keyboardType = KeyboardType.Number,
                modifier = textFieldModifier,
            )
        }

        // * * * * * SWAP * * * * *
        if (type == Constants.TYPE_SWAP) {
            Text(
                modifier = headerModifier,
                text = Strings.swapHeader,
                style = headerStyle,
                color = colors.onSurface
            )
            CustomOutlinedTextField(
                value = viewModel.swapObjectText.value,
                onValueChange = { viewModel.swapObjectText.value = it },
                placeholderText = Strings.swapHint,
                keyboardType = KeyboardType.Text,
                modifier = textFieldModifier,
            )
        }
    }
}