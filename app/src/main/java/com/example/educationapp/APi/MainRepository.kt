package com.example.educationapp.APi


import android.util.Log
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import retrofit2.Response

// Main Repository Implementation
class MainRepository : RepositoryImpl {
    private val apiService = RetrofitInstance.apiService

    override suspend fun login(username: String, password: String): Result<LoginResponse> {
        return safeApiCall { apiService.login(LoginRequests(username, password)) }
    }

    override suspend fun signup(
        username: String,
        password: String,
        phone: String,
        referral: String,
        email:String,
        secondphone: String,

    ): Result<LoginResponse> {
        return safeApiCall {
            apiService.signup(
                SignupRequst(
                    username = username,
                    password = password,
                    phoneNumber = phone,
                    referralCode = referral,
                    email = email,
                    secondaryPhone =secondphone
                )
            )
        }
    }

    override suspend fun GetSliders(): Result<ArrayList<Slider>> {
        return safeApiCall {
              val response = apiService.getslider()
            Log.d("slider",response.toString())
                response

            }
    }


    override suspend fun GetFeatureCourse(): Result<ArrayList<Course>> {
        return safeApiCall {
            val response = apiService.getfeaturecourse()
            response
        }
    }

    override suspend fun GetAllCourse(): Result<ArrayList<Course>> {
        return safeApiCall {
            val response = apiService.getallcourse()
            response
        }
    }

    override suspend fun GetAllCategory(): Result<ArrayList<Category>> {
        return safeApiCall {
            val response = apiService.getallcategory()
            response
        }
    }

    override suspend fun fetch_profile(phone: String): Result<ProfileDetails> {
        return safeApiCall {
            val response=apiService.profile(USERID(phone))
            response
        }
    }

    override suspend fun getCourseCategoryWise(categoryId: String): Result<ArrayList<Course>> {
        return safeApiCall {
            val response=apiService.getcoursecategorywise(categoryId)
            response
        }
    }

    override suspend fun updateprofile(profile: UpdateProfileRequest): Result<success_response> {
        return safeApiCall {  apiService.updateprofile(profile) }

    }

    override suspend fun discount(courseid: String, copon: String): Result<disc_success_response> {
        return safeApiCall {
            apiService.discount(courseid,copon)
        }
    }

    override suspend fun Purchase(
        phone: String,
        courseid: String,
        price: String
    ): Result<success_repponse_purchase> {
      return  safeApiCall {
          Log.d("Purchase","$phone $courseid, $price")
          apiService.purchase(phone,price,courseid)
      }
    }

    override suspend fun my_course(phone: String): Result<MyCourse> {
    return safeApiCall {
        apiService.my_course(phone)
    }
    }

    override suspend fun my_course_content(course_id: String): Result<CourseContent> {
        return safeApiCall {
        apiService.my_course_content(course_id)
        }
    }

    override suspend fun course_progress(
        user_id: String,
        course_id: String,
        video_id: String
    ): Result<course_progress> {
        return safeApiCall {
            apiService.course_progress(user_id, course_id, video_id)
        }
    }

    override suspend fun request_live_class(
        user_id: String,
        course_id: String
    ): Result<success_response_Liveclass> {
        return safeApiCall {
            apiService.request_live_class(user_id, course_id)
        }
    }

    override suspend fun get_live_class_details(
        course_id: String,user_id: String


    ): Result<get_live_class_details> {
        return safeApiCall {
            apiService.get_live_class_details(course_id,user_id)
        }
    }

    override suspend fun Purchase_live_class(
        phone: String,
        live_id: String,
        price: String
    ): Result<success_response_Liveclass> {
        return safeApiCall {
            apiService.purchase_live_class(phone,live_id,price)
        }
    }

    override suspend fun changepass(
        user_id: String,
        newpass: String,
        oldpass: String
    ): Result<success_response> {
        return safeApiCall {
            apiService.changepass(user_id,newpass,oldpass)
        }
    }

    override suspend fun fetch_wallet(phone: String): Result<Fetch_Wallet> {
        return safeApiCall {

            apiService.Fetch_Wallet(phone)
        }
    }

    override suspend fun withdraw(phone: String, amount: String,upid:String): Result<withdraw> {
return safeApiCall {
    apiService.Withdraw_Request(phone,amount,upid)
}
    }

    override suspend fun Searchet(courseName: String): Result<ArrayList<Course>> {
        return safeApiCall {
            apiService.GetSearch(courseName)
        }
    }

    override suspend fun is_course_purchased(
        phone: String,
        course_id: String
    ): Result<is_course_purchased> {
        return safeApiCall {
            apiService.is_course_purchased(phone, course_id)
        }
    }

    override suspend fun rate_course(
        user_id: String,
        course_id: String,
        rating: String
    ): Result<rate_res> {
        return safeApiCall {
            apiService.rate_course(user_id,course_id,rating)
        }
    }

    override suspend fun myliveclass(phone: String): Result<get_live_class_details> {
        return safeApiCall {
            Log.d("PHONE",phone)
            apiService.my_live_class(phone)
        }
    }

    override suspend fun helpsupport(id: String, message: String): Result<help_supp> {
        return safeApiCall {
            apiService.helpsupport(help_request(id,message))
        }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): Result<T> {
        return try {
            val response = apiCall()
            Log.d("response",response.toString())
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("response",body.toString())
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()} and message: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}


