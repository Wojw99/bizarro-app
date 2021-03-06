package com.example.bizarro.ui.screens.user_profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.components.TopBar
import com.example.bizarro.ui.theme.*
import com.example.bizarro.utils.Constants
import com.example.bizarro.utils.Dimens
import com.example.bizarro.utils.Strings
import timber.log.Timber
import java.io.ByteArrayOutputStream

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel(),
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

            // * * * * * * ERROR TEXT * * * * * *
            if (viewModel.loadError.value.isNotEmpty() && !viewModel.isLoading.value) {
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
                    Button(onClick = { viewModel.getUserProfile() }) {
                        Text(
                            text = Strings.refresh,
                            color = kWhite
                        )
                    }
                }
            }


            // * * * * * * USER PROFILE EDIT SECTION * * * * * *
            if (!viewModel.isLoading.value) {

                EditFieldsSection()

                Spacer(modifier = Modifier.height(50.dp))

            }

            // * * * * * * PROGRESS BAR * * * * * *
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

        }
    }


}

@Composable
fun ImagePicker(
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    var imageUrl by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUrl = uri
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(kWhite),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Pick Gallery Image",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            imageUrl?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { bitmap ->
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Gallery Image",
                        modifier = Modifier.size(400.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = {
                    launcher.launch("image/*")
                }
            ) {
                Text(
                    text = "Click Image",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Composable
fun EditFieldsSection(viewModel: UserProfileViewModel = hiltViewModel()) {

    var imageUrl by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUrl = uri
    }

    imageUrl?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }

        bitmap.value?.let { bitmap ->

            Image(

                bitmap = bitmap.asImageBitmap(),
                contentDescription = "User Image to edit",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(110.dp)
                    .clip(RoundedCornerShape(10))
                    .border(3.dp, kBlueDark, RoundedCornerShape(10))
            )
        }
    }


    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            launcher.launch("image/*")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = kBlueDark),
    ) {
        Text(
            text = "Kliknij aby edytowa?? zdj??cie",
            color = kWhite,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(30.dp))


    TextField(
        value = viewModel.nameUser,
        onValueChange = {
            viewModel.nameUser = it
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person, contentDescription = "PersonIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        )

    )


    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.emailUser,
        onValueChange = {
            viewModel.emailUser = it
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email, contentDescription = "EmailIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        )

    )

    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.phoneUser,
        onValueChange = {
            viewModel.phoneUser = it
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone, contentDescription = "PhoneIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        )

    )

    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.userDescription,
        onValueChange = {
            viewModel.userDescription = it
        },

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Info, contentDescription = "InfoIcon",
                tint = MaterialTheme.colors.onSurface
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            unfocusedBorderColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface
        ),
        modifier = Modifier.width(300.dp)

    )

    Spacer(modifier = Modifier.height(40.dp))

    Button(
        onClick = {
            Timber.d("UserName: ${viewModel.nameUser}")
            //viewModel.updateName(editDataName.text)

            if (bitmap.value != null) {

                val bytes = ByteArrayOutputStream()
                bitmap.value!!.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    bitmap.value!!,
                    "UserAddedPhoto",
                    null
                )
                val resultPath = Uri.parse(path.toString())

                Timber.d("Photo: $resultPath")

                // Timber.d("Photo: ${getImageUriFromBitmap(context,bitmap.value!!)}")
            }

        },
        Modifier.size(width = 250.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onSurface),

        ) {
        Text(
            text = "Zapisz",
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.background,

            )
    }
}


