package edu.uksw.fti.pam.taspam.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.login.LoginViewModel
import edu.uksw.fti.pam.taspam.screens.signup.SignUpScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.uksw.fti.pam.taspam.model.LogoutViewModel
import edu.uksw.fti.pam.taspam.screens.*

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    val sharedViewModel: SharedViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(route = Screens.ForgetScreen.route) {
            ForgetScreen(saveViewModel = sharedViewModel, navController = navController)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController, saveViewModel = sharedViewModel)
        }
        composable(route = Screens.SignUpScreen.route) {
            SignUpScreen( navController = navController, saveViewModel = sharedViewModel)
        }
        composable(route = Screens.BottomNavScreen.route) {
            BottomNavigationMainScreenView(sharedViewModel = sharedViewModel)
        }

    }

}