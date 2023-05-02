package edu.uksw.fti.pam.taspam.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import edu.uksw.fti.pam.taspam.ButtonNavItem
import edu.uksw.fti.pam.taspam.MainActivity
import edu.uksw.fti.pam.taspam.R
import edu.uksw.fti.pam.taspam.model.LogoutViewModel
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.signup.SignUpViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    logoutViewModel: LogoutViewModel = hiltViewModel(),
    loadViewModel: SharedViewModel,
){

    var name by rememberSaveable{ mutableStateOf("") }
    var email by rememberSaveable{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val setUserInformation = loadViewModel.person

    val firebase = FirebaseAuth.getInstance()

    loadViewModel.retrieveData(
        userID = setUserInformation!!.email,
        context = context
    ){ data ->
        name = data.name
        email = data.email
    }


    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        TopAppBar(
            modifier=Modifier.height(270.dp),
            backgroundColor = Color(231,79,80),
            contentColor = Color.Black,
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.profilelindan) ,
                    contentDescription = "L.P",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                )
            }
        }
        Spacer(modifier = Modifier .height(40.dp))
        Button(
            onClick = {
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80))

        ) {
            Text(
                text = "$name" , fontSize = 18.sp, fontWeight = FontWeight.SemiBold,

            )
        }


        Spacer(modifier = Modifier .height(5.dp))

        Button(
            onClick = {
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding( start = 20.dp, end = 20.dp)
                .fillMaxWidth(),colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80))

        ) {
            Text(
                text = "$email" , fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(modifier = Modifier .height(5.dp))
        Button(
            onClick = {
                navController.navigate(route = Screens.ChangePasswordScreen.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80))
        ) {
            Text(text = stringResource(id = R.string.changepassword))
        }

        Spacer(modifier = Modifier .height(5.dp))

        Button(
            onClick = {
//                logoutViewModel?.logout()
                firebase.signOut()
                context.startActivity(Intent(context, MainActivity::class.java))
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
            ,colors = ButtonDefaults.buttonColors(backgroundColor = Color(231,79,80))

        ) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = ""
            )
            Text(
                text = stringResource(id = R.string.logout),
            )
        }
    }
}