package com.example.educationapp.APi
import kotlinx.coroutines.delay
class TestRepository : RepositoryImpl {
    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        delay(5000)
        if(username.equals("1234567890")&&password.equals("test"))
          return Result.success(LoginResponse(success = "true", id = "1"))
        else return  Result.failure(Throwable("Invalid Credentials"))
    }

    override suspend fun signup(username: String, password: String, phoneNumber: String, referralCode: String): Result<LoginResponse> {
        delay(5000)
        return Result.success(LoginResponse(success = "true", id = "1"))
    }
}