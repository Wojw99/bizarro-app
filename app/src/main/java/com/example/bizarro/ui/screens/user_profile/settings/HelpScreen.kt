package com.example.bizarro.ui.screens.user_profile.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.search.topRecordListMargin
import com.example.bizarro.ui.screens.user_profile.OtherOpinionBox
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings

@Composable
fun HelpScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    viewModel.appState.bottomMenuVisible.value = false

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            TopBar(
                navController = navController,
                title = Strings.empty,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                "Pomoc",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface
            )


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Główne pomocne zagadnienia:",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface
            )


            HelpList()


        }
    }

}

@Composable
fun HelpList(
    viewModel: SettingsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)

    ) {

        val helpList = viewModel.helpList.value
        val helpListCount = helpList.size


        items(helpListCount) { index ->
            if (index == 0)
                Spacer(modifier = Modifier.height(topRecordListMargin))

            OtherOpinionBox(ask = helpList[index])

            if (index == helpList.size - 1)
                Spacer(modifier = Modifier.height(64.dp))
            else
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }

    }
}

@Composable
fun OtherOpinionBox(ask: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .background(kWhite)
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(12.dp)
    )
    {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .background(kWhite)

            )
            {


                Text(
                    ask,
                    style = MaterialTheme.typography.h6,
                    color = kBlack
                )

            }


        }

    }
}

