package com.example.bizarro.ui.screens.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings
import com.example.bizarro.util.models.TopBarAction

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
                .padding(Dimens.standardPadding)
                .fillMaxSize()
        ) {

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
        )
    }
}
