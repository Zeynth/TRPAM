package edu.uksw.fti.pam.taspam.model

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.taspam.data.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel() : ViewModel() {

    fun saveData(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userData.email)

        try {
            fireStoreRef.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        userID: String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    // for getting single or particular document
                    if (it.exists()) {
                        val userData = it.toObject<UserData>()!!
                        data(userData)
                    } else {
                        Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    var person by mutableStateOf<UserData?>(null)
        private set
    fun addPerson(loginData: UserData)
    {
        person = loginData
    }


}
