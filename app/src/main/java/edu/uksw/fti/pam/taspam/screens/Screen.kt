package edu.uksw.fti.pam.taspam.screens

sealed class Screens(val route: String) {
    object LoginScreen : Screens(route = "Login_Screen")
    object SignUpScreen : Screens(route = "SignUp_Screen")
    object BottomNavScreen : Screens(route = "Bottom_Nav_Screen")
    object NavGraphScreen : Screens(route = "Nav_Graph_Screen")
    object ChangePasswordScreen : Screens(route = "Change_Password_Screen")
    object NTBScreen : Screens(route = "NTB_Screen")
    object JatengScreen : Screens(route = "Jateng_Screen")
    object SumbarScreen : Screens(route = "Sumbar_Screen")
    object ForgetScreen : Screens(route = "Forget_Screen")
}
