package edu.uksw.fti.pam.jkn.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.jkn.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private var _userList = mutableStateListOf<UserModel>()

    var errorMessage: String by mutableStateOf("")
    val userList: List<UserModel>
        get() = _userList

    fun getUserList() {
        viewModelScope.launch {
            val apiClient = UserRepository.getClient()
            try {
                _userList.clear()
                _userList.addAll(apiClient.getUser())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}