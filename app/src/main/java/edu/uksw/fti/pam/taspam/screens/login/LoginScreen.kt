package edu.uksw.fti.pam.taspam.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import edu.uksw.fti.pam.taspam.ButtonNavItem
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.data.UserData
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.login.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    saveViewModel: SharedViewModel,
    navController: NavHostController = rememberNavController()
)
{
    var email by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.loginState.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(top = 50.dp, start = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.onoroso),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = stringResource(R.string.email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)

        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
            ),textStyle = LocalTextStyle.current.copy(color = Color.Black)
        )

        TextButton(
            onClick = {
                navController.navigate(route = Screens.ForgetScreen.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.forgetpassword),
                style = TextStyle(color = Color.Gray)
            )
        }



        Button(
            onClick = {
                scope.launch {
                    viewModel.loginUser(email,password)

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 5.dp, end = 5.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80)),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(id = R.string.login), color = Color.White, modifier = Modifier.padding(10.dp))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isloading == true)
            {
                CircularProgressIndicator()
            }
        }

        TextButton(
            onClick = {
                navController.navigate(route = Screens.SignUpScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(R.string.noacc),
                style = TextStyle(color = Color.Gray)
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(text = stringResource(R.string.signup),
                fontWeight = FontWeight.Bold,
                style = TextStyle(color = Color.Black)
            )
        }

        LaunchedEffect(key1 = state.value?.isSuccess)
        {
            scope.launch {
                if (state.value?.isSuccess?.isNotEmpty()==true)
                {
                    val loginData = UserData(email = email)
                    saveViewModel.addPerson(loginData)

                    val success = state.value?.isSuccess
                    Toast.makeText(context, "${success}", Toast.LENGTH_LONG).show()
                    navController.navigate(route = Screens.BottomNavScreen.route)
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