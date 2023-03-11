package edu.uksw.fti.pam.jkn.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.uksw.fti.pam.jkn.HomeActivity
import edu.uksw.fti.pam.jkn.SignUpActivity
import edu.uksw.fti.pam.jkn.models.UserViewModel

@Composable
fun Login(vm: UserViewModel) {
    val lContext = LocalContext.current


    var usernameInput by remember {
        mutableStateOf("")
    }

    var passwordInput by remember {
        mutableStateOf("")
    }


    LaunchedEffect(
        Unit,
        block = {
            vm.getUserList()
        }
    )

    Column() {
        if (vm.errorMessage.isEmpty()) {
            LazyColumn(modifier = Modifier.padding(18.dp)){
                items(vm.userList.size) {index ->
//                    Text(text = vm.toDoList[index].desc)
                    if(vm.userList[index].username == "Cool") {
                        Text(text = vm.userList[index].username)
                    }
                }
            }
        }
        else {
            Text(text = vm.errorMessage)
        }

        var auth = false
        for (index in vm.userList) {
            if (index.username == usernameInput && index.password == passwordInput) {
                auth = true
            }
        }

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = usernameInput,
            onValueChange = { usernameInput = it })

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = passwordInput,
            onValueChange = { passwordInput = it },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(58.dp),
            onClick = {


                if (auth) {
                    lContext.startActivity(
                        Intent(lContext, HomeActivity::class.java)
                            .apply {
                                putExtra("username", usernameInput)
                            }
                    )
                }
            }
        ) {
            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(58.dp),
            onClick = {
                lContext.startActivity(
                    Intent(lContext, SignUpActivity::class.java)
                )
            }
        ) {
            Text(
                text = "Login",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }
    }
}
