package edu.uksw.fti.pam.jkn.models

import edu.uksw.fti.pam.jkn.repositories.UserPostRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ServiceBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.52.198:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserPostRepository::class.java)
}