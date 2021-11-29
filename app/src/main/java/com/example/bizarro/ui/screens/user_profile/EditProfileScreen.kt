package com.example.bizarro.ui.screens.user_profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.example.bizarro.ui.Screen
import com.example.bizarro.ui.theme.*
import com.example.bizarro.util.Dimens
import com.example.bizarro.util.Strings
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun EditProfileScreen(navController: NavController,
                      viewModel: UserProfileViewModel = hiltViewModel(),)
{
    //val context = LocalContext.current
    //val scope = rememberCoroutineScope()
    //var imageToEdit = viewModel.userImage


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(kLightGray),
        horizontalAlignment = Alignment.CenterHorizontally) {

        HeaderEditProfileScreen(navController)

//        Text("Edytuj informacje:",
//            style = MaterialTheme.typography.caption
//        )

        //Spacer(modifier = Modifier.height(40.dp))


        // * * * * * * ERROR TEXT * * * * * *
        if(viewModel.loadError.value.isNotEmpty() && !viewModel.isLoading.value)
        {
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
//        if (viewModel.recordList.value.isEmpty()
//            && !viewModel.isLoading.value
//            && viewModel.loadError.value.isEmpty()
//        ) {
//            Text(
//                text = Strings.listIsEmpty,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }

        // * * * * * * USER PROFILE EDIT SECTION * * * * * *
        if (!viewModel.isLoading.value) {

            EditFieldsSection()

            Spacer(modifier = Modifier.height(50.dp))

        }

        // * * * * * * PROGRESS BAR * * * * * *
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

       // ImagePicker()

    }

}

@Composable
fun ImagePicker(
    viewModel: UserProfileViewModel = hiltViewModel()
)
{



    var imageUrl by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
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
fun HeaderEditProfileScreen(navController: NavController)
{
    Box(

        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)){

        IconButton(
            onClick = {
                navController.navigate(route = Screen.UserProfile.route)
            },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back to user profile ",
                Modifier.size(30.dp)
            )
        }

    }
}


@Composable
fun EditFieldsSection(viewModel: UserProfileViewModel = hiltViewModel())
{
    //        var editDataName by remember {
//            mutableStateOf(TextFieldValue(viewModel.nameUser))
//        }
    //var editEmail by remember { mutableStateOf(TextFieldValue(viewModel.emailUser)) }
    //var editPhoneNumber by remember { mutableStateOf(TextFieldValue(viewModel.phoneUser)) }

    //var editUserDescription by remember { mutableStateOf(TextFieldValue(viewModel.userDescription)) }

    Image(
        //painter = painterResource(id = R.drawable.ic_baseline_person_24),
        painter = rememberImagePainter(viewModel.userImage),
        //val painter = rememberImagePainter(
        //record.imagePaths?.first() ?: Constants.RECORD_DEFAULT_IMG_URL
        contentDescription = "User Image to edit",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(110.dp)
            .clip(RoundedCornerShape(10))
            .border(3.dp, blueColor, RoundedCornerShape(10))
    )

    Spacer(modifier = Modifier.height(10.dp))

    Button(
        onClick = {
            //launcher.launch("image/*")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = kBlueDark),
    ) {
        Text(
            text = "Edycja",
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
            Icon(imageVector = Icons.Default.Person, contentDescription = "PersonIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )


    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.emailUser,
        onValueChange = {
            viewModel.emailUser = it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = "EmailIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )

    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.phoneUser,
        onValueChange = {
            viewModel.phoneUser = it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Phone, contentDescription = "PhoneIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor)

    )

    Spacer(modifier = Modifier.height(20.dp))

    TextField(
        value = viewModel.userDescription,
        onValueChange = {
            viewModel.userDescription= it
        },

        leadingIcon = {
            Icon(imageVector = Icons.Default.Info, contentDescription = "InfoIcon" )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkColor,
            unfocusedBorderColor = darkColor),
        modifier = Modifier.width(300.dp)

    )

    Spacer(modifier = Modifier.height(40.dp))

    Button(
        onClick ={
                Timber.d("UserName: ${viewModel.nameUser}")
            //viewModel.updateName(editDataName.text)

//                navController.navigate(
//                    route = Screen.UserProfile.route

//                    navArgument("editName",){
//                        type = NavType.StringType,
//                        nullable = true
//                    }
            //)

        },
        Modifier.size(width = 250.dp, height = 50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = kBlack),

        ) {
        Text(text = "Zapisz",
            style = MaterialTheme.typography.button
        )
    }
}