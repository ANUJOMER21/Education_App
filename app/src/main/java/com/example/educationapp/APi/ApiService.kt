package com.example.educationapp.APi

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Retrofit API Service Interface
interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequests): Response<LoginResponse>

    @POST("signup")
    suspend fun signup(@Body request: SignupRequst): Response<LoginResponse>
}

// Retrofit Instance
object RetrofitInstance {
    private const val BASE_URL = "https://yourapi.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
