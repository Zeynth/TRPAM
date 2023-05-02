package edu.uksw.fti.pam.taspam.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.taspam.ButtonNavItem
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.data.UserData
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.signup.SignUpViewModel
import kotlinx.coroutines.launch

@Composable
fun ChangePasswordScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    saveViewModel: SharedViewModel,
)
{
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }
    var name by rememberSaveable{ mutableStateOf("") }
    var confirmPassword by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signupState.collectAsState(initial = null)
    val user = Firebase.auth.currentUser!!

//    val getEmailFromSignUpForm = rememberLauncherForActivityResult(
//        contract = SignUpContract(),
//        onResult = { email = it!!}
//    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(text = stringResource(id = R.string.changepass1), fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(text = stringResource(id = R.string.changehere), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextField(value = user.email!!, onValueChange ={
            email = it
        }, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = stringResource(id = R.string.email))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = stringResource(id = R.string.password))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = confirmPassword, onValueChange = {
            confirmPassword = it
        }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = stringResource(id = R.string.confirmpassword))
            }
        )

        Button(
            onClick = {
                scope.launch {
                    if(password.equals(confirmPassword)) {
                        user.updatePassword(password)
                        Toast.makeText(context, R.string.changepasswordsucess, Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(context, R.string.changepasswordfail, Toast.LENGTH_LONG).show()
                    }
                }
                navController.navigate(route = ButtonNavItem.Profile.screen_route) {
                    popUpTo(route = ButtonNavItem.Profile.screen_route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(id = R.string.savechange), color = Color.White, modifier = Modifier.padding(7.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isloading == true)
            {
                CircularProgressIndicator()
            }
        }

//        TextButton(
//            onClick = {
//                navController.navigate(route = Screens.LoginScreen.route)
//            },
//            modifier = Modifier
//                .align(Alignment.End)
//                .padding(top = 10.dp)
//        ) {
//            Text(
//                text = stringResource(id = R.string.loginstead),
//                fontWeight = FontWeight.Bold,
//                color = Color.Black
//            )
//        }

        LaunchedEffect(key1 = state.value?.isSuccess)
        {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty()==true)
                {
                    val success = state.value?.isSuccess
                    val userData = UserData(
                        email = email,
                        name = name,
                    )
                    saveViewModel.saveData(userData = userData, context = context)
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    navController.navigate(route = Screens.LoginScreen.route)
                }
            }
        }

        LaunchedEffect(key1 = state.value?.isError)
        {
            scope.launch {
                if (state.value?.isError?.isNotEmpty()==true)
                {
                    val error = state.value?.isError
                    Toast.makeText(context, "${error}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}