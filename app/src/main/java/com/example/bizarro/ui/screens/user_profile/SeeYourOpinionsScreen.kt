package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.search.RecordBox
import com.example.bizarro.ui.screens.search.RecordList
import com.example.bizarro.ui.screens.search.topRecordListMargin
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kBlack
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings


@ExperimentalFoundationApi
@Composable
fun SeeYourOpinionsScreen(navController: NavController,
                          viewModel: UserProfileViewModel = hiltViewModel()
) {

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally) {

            HeaderSectionSeeOpinionUserProfile(navController)

            Text("Opinie o Tobie:",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onSurface)


            Spacer(modifier = Modifier.height(30.dp))

            if (viewModel.loadError.value.isNotEmpty()
                && !viewModel.isLoading.value) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = viewModel.loadError.value,
                    )
                    Spacer(modifier = Modifier.height(Dimens.standardPadding))
                    Button(onClick = { viewModel.getUserProfile() }) {
                        Text(
                            text = Strings.refresh,
                        )
                    }
                }
            }

            // * * * * * * EMPTY TEXT * * * * * *
            if (viewModel.userLoggedOpinionList.value.isEmpty()
                && !viewModel.isLoading.value
                && viewModel.loadError.value.isEmpty()
            ) {
                Text(
                    text = Strings.listIsEmpty,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            // * * * * * * RECORD LIST * * * * * *
            if (viewModel.userLoggedOpinionList.value.isNotEmpty() && !viewModel.isLoading.value) {
                LoggedUserOpinionsList()
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }


            Spacer(modifier = Modifier.height(30.dp))


        }
    }




}

@Composable
fun HeaderSectionSeeOpinionUserProfile(navController: NavController)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){
        IconButton(
            onClick = {
                navController.navigate(route = Screen.UserProfile.route)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to user profile ",
                Modifier.size(30.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun LoggedUserOpinionsList(
    viewModel: UserProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ){

        val opinionList = viewModel.userLoggedOpinionList.value
        val opinionListCount = opinionList.size


        items(opinionListCount) { index ->
            if (index == 0)
                Spacer(modifier = Modifier.height(topRecordListMargin))

            OpinionBox(opinion = opinionList[index])

            if (index == opinionList.size - 1)
                Spacer(modifier = Modifier.height(64.dp))
            else
                Spacer(modifier = Modifier.height(Dimens.standardPadding))
        }

    }
}

@Composable
fun OpinionBox(opinion: Opinion, modifier: Modifier = Modifier,)
{
    Box(modifier = modifier
        .shadow(5.dp, RoundedCornerShape(Dimens.cornerRadius))
        .clip(RoundedCornerShape(Dimens.cornerRadius))
        .background(kWhite)
        .fillMaxHeight()
        .fillMaxWidth())
    {

        Column(horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize(),)
        {
            Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(kWhite))
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
