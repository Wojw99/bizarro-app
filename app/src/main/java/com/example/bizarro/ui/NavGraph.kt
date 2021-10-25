package com.example.bizarro.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bizarro.ui.screens.authenticate.SignInScreen
import com.example.bizarro.ui.screens.authenticate.SignUpScreen
import com.example.bizarro.ui.screens.compare.CompareScreen
import com.example.bizarro.ui.screens.home.HomeScreen
import com.example.bizarro.ui.screens.record_details.RecordDetailsScreen
import com.example.bizarro.ui.screens.search.SearchScreen
import com.example.bizarro.ui.screens.user_profile.UserProfileScreen
import com.example.bizarro.ui.screens.user_record_list.UserRecordListScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {

        composable(route = Screen.SignIn.route){
            SignInScreen(navController)
        }
        composable(route = Screen.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.Compare.route){
            CompareScreen()
        }
        composable(route = Screen.RecordDetails.route){
            RecordDetailsScreen(navController)
        }
        composable(route = Screen.Search.route){
            SearchScreen()
        }
        composable(route = Screen.UserProfile.route){
            UserProfileScreen(navController)
        }
        composable(route = Screen.UserRecordList.route){
            UserRecordListScreen()
        }
    }
}

sealed class Screen(val route: String, val name: String){
    object SignIn: Screen(route = "signin_screen", name = "Logowanie")
    object SignUp: Screen(route = "signup_acreen", name = "Rejestracja")

    object Home: Screen(route = "home_screen", name = "Główna")
    object Compare: Screen(route = "compare_screen", name = "Porównaj")
    object RecordDetails: Screen(route = "record_details_screen", name = "Ogłoszenie")
    object Search: Screen(route = "search_screen", name = "Szukaj")
    object UserProfile: Screen(route = "user_profile_screen", name = "Profil")
    object UserRecordList: Screen(route = "user_record_list_screen", name = "Ogłoszenia")
}
