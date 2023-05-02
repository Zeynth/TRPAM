package edu.uksw.fti.pam.taspam.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import edu.uksw.fti.pam.taspam.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow<Resource<AuthResult>> {
            emit(Resource.loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(result))
        } .catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    override fun registerUser(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow<Resource<AuthResult>> {
            emit(Resource.loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
            emit(Resource.Success(result))
        } .catch {
            emit(Resource.error(it.message.toString()))
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}