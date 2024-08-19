package com.example.educationapp.APi

import retrofit2.Response

// Main Repository Implementation
class MainRepository(private val apiService: ApiService) : RepositoryImpl {

    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return safeApiCall { apiService.login(LoginRequests(username, password)) }
    }


    override suspend fun signup(username: String, password: String, phone: String, referal: String): Result<LoginResponse> {
      return safeApiCall { apiService.signup(SignupRequst(
          name = username,
          password = password,
          phone = phone,
          referral =referal
      )
      ) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): Result<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}