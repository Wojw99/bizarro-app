package com.example.bizarro.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bizarro.ui.screens.authenticate.SignInScreen
import com.example.bizarro.ui.screens.authenticate.SignUpScreen
import com.example.bizarro.ui.screens.compare.CompareScreen
import com.example.bizarro.ui.screens.filter.FilterScreen
import com.example.bizarro.ui.screens.home.HomeScreen
import com.example.bizarro.ui.screens.record_details.RecordDetailsScreen
import com.example.bizarro.ui.screens.search.SearchScreen
import com.example.bizarro.ui.screens.user_profile.*
import com.example.bizarro.ui.screens.user_record_list.UserRecordListScreen

@ExperimentalComposeUiApi
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
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
            SearchScreen(navController = navController)
        }
        composable(route = Screen.Filter.route){
            FilterScreen(navController = navController)
        }
        composable(route = Screen.UserProfile.route){
            UserProfileScreen(navController)
        }
        composable(route = Screen.EditProfile.route){
            EditProfileScreen(navController)
        }
        composable(route = Screen.UserRecordList.route){
            UserRecordListScreen(navController)
        }
        composable(route = Screen.Settings.route){
            SettingsScreen(navController)
        }
        composable(route = Screen.OtherUserProfile.route){
            OtherUserProfileScreen(navController)
        }
        composable(route = Screen.AddOpinion.route){
            AddOpinionScreen(navController)
        }

        composable(route = Screen.SeeOpinionOtherUser.route){
            SeeOpinionsScreen(navController)
        }

        composable(route = Screen.SeeYourOpinionsScreen.route){
            SeeYourOpinionsScreen(navController)
        }
    }
}

sealed class Screen(val route: String, val name: String){
    object SignIn: Screen(route = "signin_screen", name = "Logowanie")
    object SignUp: Screen(route = "signup_acreen", name = "Rejestracja")
    object Settings: Screen(route = "settings_screen", name = "Ustawienia")
    object Home: Screen(route = "home_screen", name = "Główna")
    object Compare: Screen(route = "compare_screen", name = "Porównaj")
    object RecordDetails: Screen(route = "record_details_screen", name = "Ogłoszenie")
    object Search: Screen(route = "search_screen", name = "Szukaj")
    object Filter: Screen(route = "filter_screen", name = "Filtruj")
    object UserProfile: Screen(route = "user_profile_screen", name = "Profil")
    object EditProfile: Screen(route = "user_edit_profile_screen", name = "Edytuj swój profil")
    object OtherUserProfile: Screen(route = "other_user_profile_screen", name = "Profil innego użytkownika")
    object AddOpinion: Screen(route = "add_opinion_screen", name = "Dodaj opinię")
    object UserRecordList: Screen(route = "user_record_list_screen", name = "Ogłoszenia")
    object SeeOpinionOtherUser: Screen(route = "user_other_see_opinion_screen", name = "Zobacz opinię o innym użytkowniku")
    object SeeYourOpinionsScreen: Screen(route = "user_see_ypur_opinion_screen", name = "Zobacz opinie o swoim profilu")
}
