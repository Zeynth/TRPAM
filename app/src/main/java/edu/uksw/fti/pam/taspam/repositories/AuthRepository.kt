package edu.uksw.fti.pam.taspam.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import edu.uksw.fti.pam.taspam.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(name: String, email: String, password: String): Flow<Resource<AuthResult>>
    fun logout()
}