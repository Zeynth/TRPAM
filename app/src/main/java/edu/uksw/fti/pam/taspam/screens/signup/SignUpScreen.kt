package edu.uksw.fti.pam.taspam.screens.signup

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.data.UserData
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.Screens
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
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

//    val getEmailFromSignUpForm = rememberLauncherForActivityResult(
//        contract = SignUpContract(),
//        onResult = { email = it!!}
//    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(text = "Hello!", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Row() {
                Text(text = "Sign up ", fontSize = 35.sp, fontWeight = FontWeight.Bold, color = Color(231,79,80))
                Text(text = "to ", fontSize = 35.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "get  started", fontSize = 35.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(76.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = stringResource(R.string.confirmpassword)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        Button(
            onClick = {
                scope.launch {
                    if(password.equals(confirmPassword)) {
                        viewModel.registeruser(name, email, password)
                    }
                    else {
                        Toast.makeText(context, "Password and Confirmation Password is not matching", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Sign Up", color = Color.White, modifier = Modifier.padding(7.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isloading == true)
            {
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
                text = "already have an account?",
                style = TextStyle(color = Color.Black)
            )
            Text(
                text = "sign in",
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