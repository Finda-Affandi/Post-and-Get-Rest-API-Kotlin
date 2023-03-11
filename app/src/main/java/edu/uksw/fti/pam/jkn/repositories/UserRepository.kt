package edu.uksw.fti.pam.jkn.repositories

import edu.uksw.fti.pam.jkn.models.UserModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserRepository {
    @GET("user")
    suspend fun getUser(): List<UserModel>

    companion object{
        var _apiClient: UserRepository? = null

        fun getClient(): UserRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://192.168.52.198:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserRepository::class.java)
            }
            return _apiClient!!
        }
    }
}