package com.example.bizarro.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kLightGray
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.util.Strings

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // RecordRow()
            val painter = rememberImagePainter("http://10.0.2.2:3000/images/record_1_0.jpg")

            Image(
                painter = painter,
                contentDescription = "sample image",
                modifier = Modifier.size(256.dp),
            )

            if(painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun RecordRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .background(kWhite)
    ) {
        Row() {
            val res = painterResource(R.drawable.test_bike_image)
            Image(
                painter = res,
                contentDescription = Strings.recordImage,
                modifier = Modifier.weight(1f),
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Czarny BMX")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Paniówki, wczoraj 14:20")
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "560zł")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizarroTheme {
        SearchScreen()
    }
}