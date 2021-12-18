package com.example.bizarro.ui.screens.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.CustomTextField
import com.example.bizarro.ui.components.TopBar
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

val sectionModifier = Modifier.padding(
    bottom = 15.dp,
    top = 5.dp,
)

@ExperimentalComposeUiApi
@Composable
fun FilterScreen(
    viewModel: FilterViewModel = hiltViewModel(),
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
                    .padding(top = Dimens.topBarHeight, bottom = 50.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Column {
                    // * * * * * RECORD TYPE * * * * *
                    Text(
                        text = Strings.recordType,
                        style = headerStyle,
                    )
                    CheckList(viewModel.typeStateList, modifier = sectionModifier)

                    // * * * * * TYPE DEPENDENT SECTION * * * * *
                    TypeDependentSection(modifier = sectionModifier)

                    // * * * * * CATEGORY * * * * *
                    Text(
                        text = Strings.category,
                        style = headerStyle,
                    )
                    CheckList(viewModel.categoryStateList, modifier = sectionModifier)

                    // * * * * * PROVINCE * * * * *
                    Text(
                        text = Strings.province,
                        style = headerStyle,
                    )
                    CheckList(viewModel.provinceStateList, modifier = sectionModifier)

                    // * * * * * PROVINCE * * * * *
                    Text(
                        text = Strings.province,
                        style = headerStyle,
                    )
                    CheckList(viewModel.provinceStateList, modifier = sectionModifier)
                }
            }

            // * * * * * ACCEPT BUTTON * * * * *
            Button(
                onClick = {
                    viewModel.saveFilters()
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
                title = Strings.filterTitle,
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
    viewModel: FilterViewModel = hiltViewModel()
) {
    // * * * * * SELL * * * * *
    if(viewModel.typeStateList[Constants.TYPE_SELL]?.value == true) {
        Column {
            Text(
                text = Strings.sellPriceHeader,
                style = headerStyle,
            )
            Row(modifier = modifier.fillMaxWidth().background(Color.Green)) {
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    onTextChanged = { text -> },
                    hint = Strings.min,
                )
                Spacer(modifier = Modifier.width(Dimens.standardPadding))
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    onTextChanged = { text -> },
                    hint = Strings.max,
                )
            }
        }
    } else if(viewModel.typeStateList[Constants.TYPE_BUY]?.value == true) {
        Column {
            Text(
                text = Strings.buyPriceHeader,
                style = headerStyle,
            )
            CustomTextField(
                modifier = modifier.fillMaxWidth(),
                onTextChanged = { text -> },
                hint = Strings.buyPriceHint,
            )
        }
    } else if(viewModel.typeStateList[Constants.TYPE_SWAP]?.value == true) {
        Column {
            Text(
                text = Strings.swapHeader,
                style = headerStyle,
            )
            CustomTextField(
                modifier = modifier.fillMaxWidth(),
                onTextChanged = { text -> },
                hint = Strings.swapHint,
            )
        }
    } else if(viewModel.typeStateList[Constants.TYPE_RENT]?.value == true) {
        Column {
            Text(
                text = Strings.rentHeader1,
                style = headerStyle,
            )
            CustomTextField(
                modifier = modifier.fillMaxWidth(),
                onTextChanged = { text -> },
                hint = Strings.rentHint1,
            )
            Text(
                text = Strings.rentHeader2,
                style = headerStyle,
            )
            CustomTextField(
                modifier = modifier.fillMaxWidth(),
                onTextChanged = { text -> },
                hint = Strings.rentHint2,
            )
        }
    }
}

@Composable
fun CheckList(
    radioStates: Map<String, MutableState<Boolean>>,
    modifier: Modifier = Modifier,
    multipleSelect: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        for (state in radioStates) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(Dimens.cornerRadius))
                    .clickable {
                        if (multipleSelect) {
                            onCheckClick(state.value)
                        } else {
                            onRadioClick(state.value, radioStates)
                        }
                    }
            ) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = Dimens.standardPadding,
                        vertical = 7.dp,
                    )
                ) {
                    // * * * RADIO BUTTON * * *
                    if (multipleSelect) {
                        Checkbox(
                            checked = state.value.value,
                            colors = CheckboxDefaults.colors(kBlueDark),
                            onCheckedChange = {
                                onCheckClick(state.value)
                            }
                        )
                    } else {
                        RadioButton(
                            selected = state.value.value,
                            colors = RadioButtonDefaults.colors(kBlueDark),
                            onClick = {
                                onRadioClick(state.value, radioStates)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(Dimens.standardPadding))

                    // * * * TEXT * * *
                    Text(
                        text = CommonMethods.formatRecordTypeText(state.key),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = kBlack,
                        ),
                    )
                }
            }
        }
    }
}

private fun onCheckClick(
    state: MutableState<Boolean>,
) {
    state.value = !state.value
}

private fun onRadioClick(
    state: MutableState<Boolean>,
    radioStates: Map<String, MutableState<Boolean>>,
) {
    for (state2 in radioStates)
        state2.value.value = false
    state.value = true
}

@Composable
fun CheckButtonBox() {

}