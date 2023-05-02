package edu.uksw.fti.pam.taspam.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uksw.fti.pam.taspam.repositories.AuthRepository
import edu.uksw.fti.pam.taspam.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel(){

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    val _loginState = Channel<LoginState>()
    val loginState = _loginState.receiveAsFlow()

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }


    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{result->
            when(result)
            {
                is Resource.Success ->{
                    _loginState.send(LoginState(isSuccess = "Login Success!"))
                }
                is Resource.loading ->{
                    _loginState.send(LoginState(isloading = true))
                }
                is Resource.error ->{
                    _loginState.send(LoginState(isError = result.message))
                }
            }
        }
    }
}