package com.example.bizarro.ui.screens.add_record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.RadioGroup
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.filter.headerModifier
import com.example.bizarro.ui.screens.filter.headerStyle
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import com.example.bizarro.utils.models.TopBarAction

@ExperimentalComposeUiApi
@Composable
fun AddRecordScreen(
    viewModel: AddRecordViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = false

    Surface(color = kGray) {
        Box {
            // * * * * * * BODY * * * * * *
            Box(
                modifier = Modifier
                    .background(kWhite)
                    .padding(horizontal = Dimens.standardPadding * 2)
                    .padding(top = Dimens.topBarHeight)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    val textFieldModifier = Modifier.fillMaxWidth()

                    // * * * * * RECORD TITLE * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.recordTitle,
                        style = headerStyle,
                    )
                    CustomOutlinedTextField(
                        value = viewModel.titleText.value,
                        onValueChange = { viewModel.titleText.value = it },
                        labelText = Strings.title,
                        keyboardType = KeyboardType.Text,
                        modifier = textFieldModifier,
                    )

                    // * * * * * RECORD DESCRIPTION * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.recordDescription,
                        style = headerStyle,
                    )
                    CustomOutlinedTextField(
                        value = viewModel.descriptionText.value,
                        onValueChange = { viewModel.descriptionText.value = it },
                        labelText = Strings.recordDescription,
                        keyboardType = KeyboardType.Text,
                        modifier = textFieldModifier,
                    )

                    // * * * * * RECORD TYPE * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.recordType,
                        style = headerStyle,
                    )
                    RadioGroup(selectedLabel = viewModel.selectedType, labels = viewModel.typeLabels)

                    // * * * * * TYPE DEPENDENT SECTION * * * * *
                    TypeDependentSection()

                    // * * * * * CATEGORY * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.category,
                        style = headerStyle,
                    )
                    RadioGroup(selectedLabel = viewModel.selectedCategory, labels = viewModel.categoryLabels)

                    // * * * * * PROVINCE * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.province,
                        style = headerStyle,
                    )
                    RadioGroup(selectedLabel = viewModel.selectedProvince, labels = viewModel.provinceLabels)

                    // * * * * * ADDRESS * * * * *
                    Text(
                        modifier = headerModifier,
                        text = Strings.address,
                        style = headerStyle,
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
                    
                    Spacer(modifier = Modifier.height(128.dp))
                }
            }

            // * * * * * ACCEPT BUTTON * * * * *
            Button(
                onClick = {
                    navController.navigate(Screen.Search.route) {
                        // remove all previous screen in the stack
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
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
                title = Strings.addRecord,
                actions = listOf(
                    TopBarAction(
                        onClick = { viewModel.cleanStates() },
                        icon = Icons.Default.Clear,
                        contentDescription = Strings.clearFilters,
                    ),
                ),
                modifier = Modifier
                    .background(kWhite)
                    .align(Alignment.TopCenter)
            )
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
    Column(modifier = modifier) {
        // * * * * * PRICE MIN-MAX HEADER * * * * *
        if(type == Constants.TYPE_SELL) {
            Text(
                modifier = headerModifier,
                text = Strings.sellPriceHeader,
                style = headerStyle,
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
        if(type == Constants.TYPE_SELL || type == Constants.TYPE_BUY || type == Constants.TYPE_RENT) {
            CustomOutlinedTextField(
                value = viewModel.priceText.value,
                onValueChange = { viewModel.priceText.value = it },
                labelText = Strings.price,
                keyboardType = KeyboardType.Number,
            )
        }

        // * * * * * ADDITIONAL RENT * * * * *
        if(type == Constants.TYPE_RENT) {
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
        if(type == Constants.TYPE_SWAP) {
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