package edu.uksw.fti.pam.taspam.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uksw.fti.pam.taspam.repositories.AuthRepository
import edu.uksw.fti.pam.taspam.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel(){

    val _signupState = Channel<SignUpState>()
    val signupState = _signupState.receiveAsFlow()


    fun registeruser(name: String, email:String, password:String) = viewModelScope.launch {
        repository.registerUser(name, email, password).collect{result->
            when(result)
            {
                is Resource.Success ->{
                    _signupState.send(SignUpState(isSuccess = "Sign Up Success!"))
                }
                is Resource.loading ->{
                    _signupState.send(SignUpState(isloading = true))
                }
                is Resource.error ->{
                    _signupState.send(SignUpState(isError = result.message))
                }
            }
        }
    }

    fun changepassword(name: String, email:String, password:String) = viewModelScope.launch {
        repository.registerUser(name, email, password).collect{result->
            when(result)
            {
                is Resource.Success ->{
                    _signupState.send(SignUpState(isSuccess = "Sign Up Success!"))
                }
                is Resource.loading ->{
                    _signupState.send(SignUpState(isloading = true))
                }
                is Resource.error ->{
                    _signupState.send(SignUpState(isError = result.message))
                }
            }
        }
    }
}