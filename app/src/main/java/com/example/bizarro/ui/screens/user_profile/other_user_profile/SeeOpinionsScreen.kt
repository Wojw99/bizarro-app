package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.api.models.Opinion
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.search.topRecordListMargin
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings


@ExperimentalFoundationApi
@Composable
fun SeeOpinionsScreen(
    navController: NavController,
    viewModel: OtherUserViewModel = hiltViewModel()
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
        ) {


            TopBar(
                navController = navController,
                title = Strings.empty,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                "Opinie o użytkowniku ${viewModel.nameUser}!",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontFamily = FontFamily.Serif,
                ),
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(horizontal = 24.dp),
            )

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
                        color = MaterialTheme.colors.onSurface
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = { viewModel.getOtherUserProfile() }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }

            // * * * * * * EMPTY TEXT * * * * * *
            if (viewModel.userOtherOpinionList.value.isEmpty()
                && !viewModel.isLoading.value
                && viewModel.loadError.value.isEmpty()
            ) {
                Text(
                    text = Strings.listIsEmpty,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colors.onSurface
                )
            }

            // * * * * * * RECORD LIST * * * * * *
            if (viewModel.userOtherOpinionList.value.isNotEmpty() && !viewModel.isLoading.value) {
                UserOpinionsList()
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }


        }
    }

}


@ExperimentalFoundationApi
@Composable
fun UserOpinionsList(
    viewModel: OtherUserViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondaryVariant)

    ) {

        val otherUserOpinionList = viewModel.userOtherOpinionList.value
        val otherUserOpinionListCount = otherUserOpinionList.size


        items(otherUserOpinionListCount) { index ->
            if (index == 0)
                Spacer(modifier = Modifier.height(topRecordListMargin))

            OtherOpinionBox(opinion = otherUserOpinionList[index])

            if (index == otherUserOpinionList.size - 1)
                Spacer(modifier = Modifier.height(64.dp))
            else
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }

    }
}

@Composable
fun OtherOpinionBox(opinion: Opinion, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
            .clip(RoundedCornerShape(Dimens.cornerRadius))
            .background(kWhite)
            .fillMaxHeight()
            .fillMaxWidth()
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
                    text = "Ocena: ${opinion.rating}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = kBlack,
                    )
                )

                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "Data: ${opinion.creationDate}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = kBlack,
                    )
                )

            }

            Text(
                text = opinion.content,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = kBlack,
                )
            )

        }

    }
}

