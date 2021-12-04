package com.example.bizarro.ui.screens.user_profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.data.remote.responses.Opinion
import com.example.bizarro.data.remote.responses.Record
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.screens.search.TypeSpecificTitle
import com.example.bizarro.ui.screens.search.topRecordListMargin
import com.example.bizarro.ui.screens.user_profile.other_user_profile.OtherUserViewModel
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.CommonMethods
import com.example.bizarro.util.Constants
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings


@ExperimentalFoundationApi
@Composable
fun SeeOpinionsScreen(navController: NavController,
                      viewModel: OtherUserViewModel = hiltViewModel())
{

    BizarroTheme(
        darkTheme = Constants.isDark.value
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally) {


            HeaderSectionSeeOpinionOtherUser(navController)


            Text("Opinie o użytkowniku:",
                style = MaterialTheme.typography.caption,
                color =  MaterialTheme.colors.onSurface)

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
                    Button(onClick = { viewModel.getOtherUserProfile() }) {
                        Text(
                            text = Strings.refresh,
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
                    modifier = Modifier.align(Alignment.CenterHorizontally)
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

@Composable
fun HeaderSectionSeeOpinionOtherUser(navController: NavController)
{
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)){


        IconButton(
            onClick = {
                navController.navigate(route = Screen.OtherUserProfile.route)
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
fun UserOpinionsList(
    viewModel: OtherUserViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)

    ){

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
fun OtherOpinionBox(opinion: Opinion, modifier: Modifier = Modifier,)
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

