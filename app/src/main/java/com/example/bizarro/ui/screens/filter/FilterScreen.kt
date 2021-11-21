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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.CommonMethods
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings
import com.example.bizarro.util.models.CheckState
import com.example.bizarro.util.models.TopBarAction

val headerStyle = TextStyle(
    color = kBlack,
    fontSize = 20.sp,
    fontWeight = FontWeight.SemiBold,
)

val checkListModifier = Modifier.padding(
    bottom = 15.dp,
    top = 5.dp,
)

@Composable
fun FilterScreen(
    viewModel: FilterViewModel = hiltViewModel(),
    navController: NavController,
) {
    viewModel.appState.bottomMenuVisible.value = false

    Surface(color = kGray) {
        // * * * * * * BODY * * * * * *
        Box(
            modifier = Modifier
                .background(kWhite)
                .padding(Dimens.standardPadding * 2)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column {
                Spacer(modifier = Modifier.height(Dimens.topBarHeight))

                // * * * * * RECORD TYPE * * * * *
                Text(
                    text = Strings.recordType,
                    style = headerStyle,
                )
                CheckList( viewModel.typeStateList, modifier = checkListModifier)

                // * * * * * CATEGORY * * * * *
                Text(
                    text = Strings.category,
                    style = headerStyle,
                )
                CheckList(viewModel.categoryStateList, modifier = checkListModifier)

                // * * * * * PROVINCE * * * * *
                Text(
                    text = Strings.province,
                    style = headerStyle,
                )
                CheckList(viewModel.provinceStateList, modifier = checkListModifier)

                // * * * * * PROVINCE * * * * *
                Text(
                    text = Strings.province,
                    style = headerStyle,
                )
                CheckList(viewModel.provinceStateList, modifier = checkListModifier)
            }
        }

        // * * * * * * TOP BAR * * * * * *
        TopBar(
            navController = navController,
            title = Strings.filterTitle,
            actions = listOf(
                TopBarAction(
                    onClick = {

                    },
                    icon = Icons.Default.Clear,
                    contentDescription = Strings.clearFilters,
                ),
            ),
            modifier = Modifier.background(kWhite)
        )
    }
}

@Composable
fun CheckList(
    radioStates: List<MutableState<CheckState>>,
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
                            onCheckClick(state)
                        } else {
                            onRadioClick(state, radioStates)
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
                            checked = state.value.checked,
                            colors = CheckboxDefaults.colors(kBlueDark),
                            onCheckedChange = {
                                onCheckClick(state)
                            }
                        )
                    } else {
                        RadioButton(
                            selected = state.value.checked,
                            colors = RadioButtonDefaults.colors(kBlueDark),
                            onClick = {
                                onRadioClick(state, radioStates)
                            }
                        )
                    }

                    Spacer(modifier = Modifier.width(Dimens.standardPadding))

                    // * * * TEXT * * *
                    Text(
                        text = CommonMethods.formatRecordTypeText(state.value.text),
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
    state: MutableState<CheckState>,
) {
    state.value = CheckState(state.value.text, !state.value.checked)
}

private fun onRadioClick(
    state: MutableState<CheckState>,
    radioStates: List<MutableState<CheckState>>,
) {
    for (state2 in radioStates)
        state2.value = CheckState(state2.value.text, false)
    state.value = CheckState(state.value.text, true)
}

@Composable
fun CheckButtonBox() {

}