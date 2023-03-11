package edu.uksw.fti.pam.jkn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import edu.uksw.fti.pam.jkn.models.ServiceBuilder.api
import edu.uksw.fti.pam.jkn.models.UserModel
import edu.uksw.fti.pam.jkn.models.UserViewModel
import edu.uksw.fti.pam.jkn.screens.Login
import edu.uksw.fti.pam.jkn.screens.SignUp
import edu.uksw.fti.pam.jkn.ui.theme.JKNTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
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

//                    val newUser = UserModel(5, 7, "fann", "123", "fafa", "affandi")

//                    api.addUser(newUser).enqueue(object : Callback<UserModel>{
//                        override fun onResponse(
//                            call: Call<UserModel>,
//                            response: Response<UserModel>
//                        ) {
//                            val addedUser = response.body()
//                            Log.d("POST_SUCCESS", "User ${addedUser?.username} has been posted.")
//                        }
//
//                        override fun onFailure(call: Call<UserModel>, t: Throwable) {
//                            Log.e("POST_FAILURE", "Error add user: ${t.message}")
//                        }
//                    })
                }
            }
        }
    }
}

@Composable
fun MainScreenView(
    vm: UserViewModel
) {

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
    }
}

