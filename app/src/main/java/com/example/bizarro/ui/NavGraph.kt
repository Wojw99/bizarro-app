package com.example.bizarro.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bizarro.ui.screens.add_record.AddRecordScreen
import com.example.bizarro.ui.screens.authenticate.SignInScreen
import com.example.bizarro.ui.screens.authenticate.SignUpScreen
import com.example.bizarro.ui.screens.compare.CompareScreen
import com.example.bizarro.ui.screens.filter.FilterScreen
import com.example.bizarro.ui.screens.home.HomeScreen
import com.example.bizarro.ui.screens.password_reset.PasswordResetScreen
import com.example.bizarro.ui.screens.password_reset.ResetPasswordBody
import com.example.bizarro.ui.screens.record_details.RecordDetailsScreen
import com.example.bizarro.ui.screens.search.SearchScreen
import com.example.bizarro.ui.screens.update_user_profile.UpdateUserProfileScreen
import com.example.bizarro.ui.screens.user_profile.*
import com.example.bizarro.ui.screens.user_profile.other_user_profile.AddOpinionScreen
import com.example.bizarro.ui.screens.user_profile.settings.AboutAppScreen
import com.example.bizarro.ui.screens.user_profile.settings.HelpScreen
import com.example.bizarro.ui.screens.user_profile.settings.PrivacyPolicyScreen
import com.example.bizarro.ui.screens.user_profile.settings.SettingsScreen
import com.example.bizarro.ui.screens.user_record_list.UserRecordListScreen

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
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
            CompareScreen(navController = navController)
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
        composable(route = Screen.PrivacyPolicyScreen.route){
            PrivacyPolicyScreen(navController)
        }
        composable(route = Screen.AboutAppScreen.route){
            AboutAppScreen(navController)
        }
        composable(route = Screen.HelpScreen.route){
            HelpScreen(navController)
        }
        composable(route = Screen.AddRecord.route){
            AddRecordScreen(navController = navController)
        }
        composable(route = Screen.UpdateUserProfile.route){
            UpdateUserProfileScreen(navController = navController)
        }
        composable(route = Screen.PasswordResetScreen.route){
            PasswordResetScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String, val name: String){
    object SignIn: Screen(route = "signin_screen", name = "Logowanie")
    object SignUp: Screen(route = "signup_acreen", name = "Rejestracja")
    object Settings: Screen(route = "settings_screen", name = "Ustawienia")
    object Home: Screen(route = "home_screen", name = "G????wna")
    object Compare: Screen(route = "compare_screen", name = "Por??wnaj")
    object RecordDetails: Screen(route = "record_details_screen", name = "Og??oszenie")
    object AddRecord: Screen(route = "record_add_screen", name = "Dodaj og??oszenie")
    object Search: Screen(route = "search_screen", name = "Szukaj")
    object Filter: Screen(route = "filter_screen", name = "Filtruj")
    object UserProfile: Screen(route = "user_profile_screen", name = "Profil")
    object EditProfile: Screen(route = "user_edit_profile_screen", name = "Edytuj sw??j profil")
    object OtherUserProfile: Screen(route = "other_user_profile_screen", name = "Profil innego u??ytkownika")
    object AddOpinion: Screen(route = "add_opinion_screen", name = "Dodaj opini??")
    object UserRecordList: Screen(route = "user_record_list_screen", name = "Twoje og??oszenia")
    object SeeOpinionOtherUser: Screen(route = "user_other_see_opinion_screen", name = "Zobacz opini?? o innym u??ytkowniku")
    object SeeYourOpinionsScreen: Screen(route = "user_see_ypur_opinion_screen", name = "Zobacz opinie o swoim profilu")
    object PrivacyPolicyScreen: Screen(route = "user_privacy_policy_screen", name = "Polityka prywatno??ci")
    object AboutAppScreen: Screen(route = "user_about_app_screen", name = "O aplikacji")
    object HelpScreen: Screen(route = "user_help_screen", name = "Pomoc")
    object UpdateUserProfile: Screen(route = "update_user_profile_screen", name = "Edytuj profil")
    object PasswordResetScreen: Screen(route = "reset_password_screen", name = "Resetuj has??o")
}
