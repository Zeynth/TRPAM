package edu.uksw.fti.pam.taspam.screens.login

data class LoginState(
    val isloading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
