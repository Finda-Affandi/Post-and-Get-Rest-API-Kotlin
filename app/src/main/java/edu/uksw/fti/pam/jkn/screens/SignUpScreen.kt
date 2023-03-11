package edu.uksw.fti.pam.jkn.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import edu.uksw.fti.pam.jkn.MainActivity
import edu.uksw.fti.pam.jkn.models.ServiceBuilder
import edu.uksw.fti.pam.jkn.models.UserModel
import edu.uksw.fti.pam.jkn.models.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignUp(vm: UserViewModel){
    val lContext = LocalContext.current

    var id : Int
    id = 0

    var userId : Int

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var firstname by remember {
        mutableStateOf("")
    }

    var lastname by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        Unit,
        block = {
            vm.getUserList()
        }
    )

    for (index in vm.userList) {
        id = id + 1
    }

    id = id + 1

    userId = id


    Column() {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = username,
            onValueChange = { username = it })

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation()
        )

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = firstname,
            onValueChange = { firstname = it })

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth(),
            value = lastname,
            onValueChange = { lastname = it })

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .size(58.dp),
            onClick = {

                val newUser = UserModel(id, userId, username, password, firstname, lastname)

                ServiceBuilder.api.addUser(newUser).enqueue(object : Callback<UserModel> {
                    override fun onResponse(
                        call: Call<UserModel>,
                        response: Response<UserModel>
                    ) {
                        val addedUser = response.body()
                        Log.d("POST_SUCCESS", "User ${addedUser?.username} has been posted.")
                        lContext.startActivity(
                            Intent(lContext, MainActivity::class.java)
                        )
                    }

                    override fun onFailure(call: Call<UserModel>, t: Throwable) {
                        Log.e("POST_FAILURE", "Error add user: ${t.message}")
                    }
                })
            }
        ) {
            Text(
                text = "SignUp",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }
    }
}