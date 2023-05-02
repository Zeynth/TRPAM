package edu.uksw.fti.pam.taspam.screens.signup

data class SignUpState(
    val isloading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)
