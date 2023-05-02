package edu.uksw.fti.pam.taspam

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.taspam.screens.BottomNavigationMainScreenView
import edu.uksw.fti.pam.taspam.screens.login.LoginViewModel
import edu.uksw.fti.pam.taspam.ui.theme.TASPAMTheme

class HomeActivity : AppCompatActivity() {
//    private val saveViewModel: SaveViewModel by viewModels()
//    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TASPAMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val email = getIntent().getStringExtra("email") ?: ""
                }
            }
        }
    }
}