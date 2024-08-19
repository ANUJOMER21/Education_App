package com.example.educationapp.APi

interface RepositoryImpl {
    suspend fun login(username: String, password: String): Result<LoginResponse>
    suspend fun signup(username: String, password: String, phone: String, referal: String): Result<LoginResponse>
}