package com.example.bizarro.ui.screens.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.*
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

val headerStyle = TextStyle(
    color = kBlack,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
)

val headerModifier = Modifier.padding(top = 15.dp, bottom = 5.dp)

@ExperimentalComposeUiApi
@Composable
fun FilterScreen(
    viewModel: FilterViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = false


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
                // * * * * * RECORD TYPE * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.recordType,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold
                    ),
                )
                RadioGroup(selectedLabel = viewModel.selectedType, labels = viewModel.typeLabels)

                // * * * * * TYPE DEPENDENT SECTION * * * * *
                TypeDependentSection()

                // * * * * * CATEGORY * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.category,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold),


                )
                RadioGroup(
                    selectedLabel = viewModel.selectedCategory,
                    labels = viewModel.categoryLabels
                )

                // * * * * * PROVINCE * * * * *
                Text(
                    modifier = headerModifier,
                    text = Strings.province,
                    style = TextStyle(
                        color = colors.onSurface,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold),
                )
                RadioGroup(
                    selectedLabel = viewModel.selectedProvince,
                    labels = viewModel.provinceLabels
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            // * * * * * ACCEPT BUTTON * * * * *
            Button(
                onClick = {
                    viewModel.saveFilters()
//                navController.navigate(Screen.Search.route) {
//                    // remove all previous screen in the stack
//                    popUpTo(navController.graph.findStartDestination().id) {
//                        inclusive = true
//                    }
//                }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(Dimens.standardPadding)
            ) {
                Text(text = Strings.confirm)
            }

            // * * * * * * TOP BAR * * * * * *
            TopBar(
                navController = navController,
                title = Strings.filterTitle,
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

            // * * * * * * PROGRESS BAR * * * * * *
            if(viewModel.isLoading.value) {
                LoadingBox()
            }
        }
    }


}

@ExperimentalComposeUiApi
@Composable
fun TypeDependentSection(
    modifier: Modifier = Modifier,
    viewModel: FilterViewModel = hiltViewModel()
) {
    val type = viewModel.selectedType.value
    Column(modifier = modifier) {
        // * * * * * PRICE MIN-MAX HEADER * * * * *
        if (type == Constants.TYPE_SELL) {
            Text(
                modifier = headerModifier,
                text = Strings.sellPriceHeader,
                style = TextStyle(
                    color = colors.onSurface,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold),
            )
        } else if (type == Constants.TYPE_BUY) {
            Text(
                modifier = headerModifier,
                text = Strings.buyPriceHeader,
                style = headerStyle,
            )
        } else if (type == Constants.TYPE_RENT) {
            Text(
                modifier = headerModifier,
                text = Strings.rentHeader1,
                style = headerStyle,
            )
        }

        // * * * * * PRICE MIN-MAX TEXT FIELD * * * * *
        if (type == Constants.TYPE_SELL || type == Constants.TYPE_BUY || type == Constants.TYPE_RENT) {
            Row(modifier = modifier.fillMaxWidth()) {
                CustomOutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = viewModel.priceMinText.value,
                    onValueChange = { viewModel.priceMinText.value = it },
                    labelText = Strings.min,
                    keyboardType = KeyboardType.Number,
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                CustomOutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = viewModel.priceMaxText.value,
                    onValueChange = { viewModel.priceMaxText.value = it },
                    labelText = Strings.max,
                    keyboardType = KeyboardType.Number,
                )
            }
        }

        // * * * * * ADDITIONAL RENT * * * * *
        if (type == Constants.TYPE_RENT) {
            Text(
                modifier = headerModifier,
                text = Strings.rentHeader2,
                style = headerStyle,
            )
            CustomOutlinedTextField(
                value = viewModel.rentPeriodText.value,
                onValueChange = { viewModel.rentPeriodText.value = it },
                labelText = Strings.rentHint,
                keyboardType = KeyboardType.Number,
            )
        }

        // * * * * * SWAP * * * * *
        if (type == Constants.TYPE_SWAP) {
            Text(
                modifier = headerModifier,
                text = Strings.swapHeader,
                style = headerStyle,
            )
            CustomOutlinedTextField(
                value = viewModel.swapObjectText.value,
                onValueChange = { viewModel.swapObjectText.value = it },
                labelText = Strings.swapHint,
                keyboardType = KeyboardType.Text,
            )
        }
    }
}