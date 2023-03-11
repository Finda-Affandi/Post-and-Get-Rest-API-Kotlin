package edu.uksw.fti.pam.jkn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import edu.uksw.fti.pam.jkn.models.UserViewModel
import edu.uksw.fti.pam.jkn.screens.SignUp
import edu.uksw.fti.pam.jkn.ui.theme.JKNTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = UserViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            JKNTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SignUp(vm)
                }
            }
        }
    }
}

