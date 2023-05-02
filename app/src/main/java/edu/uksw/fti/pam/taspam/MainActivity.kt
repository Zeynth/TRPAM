package edu.uksw.fti.pam.taspam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.uksw.fti.pam.taspam.model.SharedViewModel
import edu.uksw.fti.pam.taspam.screens.BottomNavigationMainScreenView
import edu.uksw.fti.pam.taspam.screens.NavigationGraph
import edu.uksw.fti.pam.taspam.screens.login.LoginViewModel
import edu.uksw.fti.pam.taspam.ui.theme.TASPAMTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val saveViewModel: SaveViewModel by viewModels()
//    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TASPAMTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val sharedViewModel: SharedViewModel = viewModel()
                    edu.uksw.fti.pam.taspam.navigation.NavigationGraph()
//                    BottomNavigationMainScreenView(sharedViewModel = sharedViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TASPAMTheme {
        Greeting("Android")
    }
}