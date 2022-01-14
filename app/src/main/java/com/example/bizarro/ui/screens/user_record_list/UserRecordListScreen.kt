package com.example.bizarro.ui.screens.user_record_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.RecordBox
import com.example.bizarro.ui.screens.record_details.RecordDetailsViewModel
import com.example.bizarro.ui.screens.search.SearchViewModel
import com.example.bizarro.ui.screens.search.topRecordListMargin
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@Composable
fun UserRecordListScreen(
    navController: NavController,
    viewModel: UserRecordListViewModel = hiltViewModel(),
) {
    viewModel.appState.showBottomMenu()

    BizarroTheme(darkTheme = viewModel.appState.isDarkTheme.value)
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(Dimens.standardPadding)
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
                    Button(onClick = { viewModel.updateRecordList() }) {
                        Text(
                            text = Strings.refresh,
                            color = colors.onSurface
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
                    modifier = Modifier.align(Alignment.Center),
                    color = colors.onSurface
                )
            }

            // * * * * * * RECORD LIST * * * * * *
            if (viewModel.recordList.value.isNotEmpty() && !viewModel.isLoading.value) {
                RecordList(navController = navController)
            }

            // * * * * * * ADD BUTTON * * * * * * *
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddRecord.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 64.dp),
                contentColor = kWhite,
                backgroundColor = kBlueDark,
            ) {
                Icon(Icons.Default.Add, contentDescription = Strings.add)
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}

@Composable
fun RecordList(
    modifier: Modifier = Modifier,
    viewModel: UserRecordListViewModel = hiltViewModel(),
    navController: NavController,
) {
    LazyColumn(modifier = modifier.background(colors.background)) {
        val recordList = viewModel.recordList.value
        val itemCount = recordList.size

        items(itemCount) { index ->
            RecordBox(
                record = recordList[index],
                onClick = {
                    RecordDetailsViewModel.record = recordList[index]
                    RecordDetailsViewModel.userId = recordList[index].userId
                    navController.navigate(Screen.RecordDetails.route)
                },
            )

            if (index == recordList.size - 1)
                Spacer(modifier = Modifier.height(64.dp))
            else
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }
    }
}