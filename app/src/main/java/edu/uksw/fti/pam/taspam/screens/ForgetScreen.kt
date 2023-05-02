package edu.uksw.fti.pam.taspam.screens

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.data.UserData
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.signup.SignUpViewModel
import kotlinx.coroutines.launch

@Composable
fun ForgetScreen(
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
            .padding(top = 50.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "FORGET YOUR PASSWORD?", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.forgetpass),
            contentDescription = "forget logo",
            modifier = Modifier
                .size(width = 220.dp, height = 220.dp),
        )}
    Spacer(modifier = Modifier.height(36.dp))

    Column(
        modifier = Modifier
            .padding(top = 380.dp, start = 30.dp, end = 30.dp)

    ) {
        Column(verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.pwresetinfo))
            Text(text = stringResource(R.string.pwinteruction))
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.LightGray,
                cursorColor = Color.Black,
                disabledLabelColor = Color.Gray,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp), singleLine = true, placeholder = {
                Text(text = "Email")
            }

        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Send Reset Link", color = Color.White, modifier = Modifier.padding(7.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isloading == true) {
                CircularProgressIndicator()
            }
        }

        TextButton(
            onClick = {
                navController.navigate(route = Screens.LoginScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(
                text = "Remember Your Password?",
                style = TextStyle(color = Color.Black)
            )
            Text(
                text = " Login",
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black)
            )
        }
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