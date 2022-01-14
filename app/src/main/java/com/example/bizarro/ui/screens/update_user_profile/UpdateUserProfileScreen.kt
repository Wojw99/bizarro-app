package com.example.bizarro.ui.screens.update_user_profile

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bizarro.R
import com.example.bizarro.ui.components.ConfirmAlertDialog
import com.example.bizarro.ui.components.CustomOutlinedTextField
import com.example.bizarro.ui.components.LoadingBox
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.screens.add_record.textFieldModifier
import com.example.bizarro.ui.theme.BizarroTheme
import com.example.bizarro.ui.theme.kBlueDark
import com.example.bizarro.ui.theme.kWhite
import com.example.bizarro.utils.CommonMethods
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings


@ExperimentalComposeUiApi
@Composable
fun UpdateUserProfileScreen(
    navController: NavController,
    viewModel: UpdateUserProfileViewModel = hiltViewModel(),
) {
    viewModel.appState.hideBottomMenu()

    BizarroTheme(
        darkTheme = viewModel.appState.isDarkTheme.value
    ) {
        Box {
            // * * * * * BODY * * * * *
            UpdateProfileScreenBody(navController)

            // * * * * * TOP BAR * * * * *
            TopBar(
                navController = navController,
                title = Strings.profileEdit,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .align(Alignment.TopCenter)
            )

            // * * * * * * ERROR DIALOG * * * * * *
            if (viewModel.loadError.value.isNotEmpty()) {
                ConfirmAlertDialog(
                    onDismiss = { viewModel.clearError() },
                    title = Strings.error,
                    body = viewModel.loadError.value,
                )
            }

            // * * * * * * SUCCESS * * * * * *
            if (viewModel.successfullyUpdate.value) {
                ConfirmAlertDialog(
                    onDismiss = {
                        viewModel.successfullyUpdate.value = false
                        navController.popBackStack()
                    },
                    title = Strings.success2,
                    body = Strings.success,
                )
            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                LoadingBox()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun UpdateProfileScreenBody(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UpdateUserProfileViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri == null) return@rememberLauncherForActivityResult

        viewModel.imageUri.value = uri

        if (Build.VERSION.SDK_INT < 28) {
            viewModel.imageBitmap.value =
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            viewModel.imageBitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = Dimens.standardPadding * 2),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val spacerHeight = 16.dp

        Spacer(modifier = Modifier.height(Dimens.topBarHeight + spacerHeight))

        // * * * * * * IMAGE * * * * * *
        var painter: Painter? = null

        if (viewModel.imageUri.value != null) {
            painter = rememberImagePainter(viewModel.imageUri.value)
        } else if(viewModel.url.value != null) {
            painter = rememberImagePainter(CommonMethods.getUrlForImage(viewModel.url.value!!))
        } else {
            painter = painterResource(id = R.drawable.user_default)
        }

        Image(
            painter = painter,
            contentDescription = "user image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(110.dp)
                .clip(RoundedCornerShape(10))
                .border(3.dp, kBlueDark, RoundedCornerShape(10))
                .clickable {
                    launcher.launch("image/*")
                }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * E-MAIL * * * * * *
        CustomOutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            labelText = Strings.email,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = "email icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * FIRST NAME * * * * * *
        CustomOutlinedTextField(
            value = viewModel.firstName.value,
            onValueChange = { viewModel.firstName.value = it },
            labelText = Strings.firstname,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = "person icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * LAST NAME * * * * * *
        CustomOutlinedTextField(
            value = viewModel.lastName.value,
            onValueChange = { viewModel.lastName.value = it },
            labelText = Strings.lastname,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = "person icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * PHONE * * * * * *
        CustomOutlinedTextField(
            value = viewModel.phone.value,
            onValueChange = { viewModel.phone.value = it },
            labelText = Strings.phoneNumber,
            keyboardType = KeyboardType.Number,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Phone, contentDescription = "phone icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * DESCRIPTION * * * * * *
        CustomOutlinedTextField(
            value = viewModel.description.value,
            onValueChange = { viewModel.description.value = it },
            labelText = Strings.profileDescription,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = "info icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        // * * * * * * ADDRESS STREET * * * * * *
        CustomOutlinedTextField(
            value = viewModel.addressStreet.value,
            onValueChange = { viewModel.addressStreet.value = it },
            labelText = Strings.address,
            keyboardType = KeyboardType.Text,
            modifier = textFieldModifier,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Place, contentDescription = "place icon",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight * 2))

        // * * * * * ACCEPT BUTTON * * * * *
        Button(
            onClick = {
                viewModel.updateProfile(context)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = kBlueDark),
        ) {
            Text(text = Strings.confirm, color = kWhite)
        }

        Spacer(modifier = Modifier.height(spacerHeight))
    }
}