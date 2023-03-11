package edu.uksw.fti.pam.jkn.repositories

import edu.uksw.fti.pam.jkn.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserPostRepository {
    @POST("user")
    fun addUser(@Body userData : UserModel): Call<UserModel>
}