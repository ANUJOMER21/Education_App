package com.example.educationapp.APi

import com.google.android.exoplayer2.Rating
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

// Retrofit API Service Interface
interface ApiService {
    @POST("login.php")
    suspend fun login(@Body request: LoginRequests): Response<LoginResponse>

    @POST("signup.php")
    suspend fun signup(@Body request: SignupRequst): Response<LoginResponse>
    @GET("getSliders.php")
    suspend fun getslider():Response<ArrayList<Slider>>
    @POST("getFeatureCourse.php")
    suspend fun getfeaturecourse():Response<ArrayList<Course>>
    @POST("getAllCourse.php")
    suspend fun getallcourse():Response<ArrayList<Course>>
    @POST("getAllCategory.php")
    suspend fun getallcategory():Response<ArrayList<Category>>
    @POST("fetch_profile.php")
    suspend fun profile(@Body USERID: USERID):Response<ProfileDetails>
    @GET("getCourseCategoryWise.php")
    suspend fun getcoursecategorywise(@Query("categoryId") categoryId: String):Response<ArrayList<Course>>
    @POST("updateprofile.php")
    suspend fun updateprofile(@Body profile: UpdateProfileRequest):Response<success_response>
    @POST("discount.php")
    @FormUrlEncoded
    suspend fun discount(@Field("course_id") courseId: String, @Field("coupon_code") coupon: String):Response<disc_success_response>
    @POST("Purchase.php")
    @FormUrlEncoded
    suspend fun purchase(@Field("phone") phone:String,@Field("cost") price:String,@Field("course_id") course_id:String):Response<success_repponse_purchase>
    @POST("my_course.php")
    @FormUrlEncoded
    suspend fun my_course(@Field("phone") phone:String):Response<MyCourse>
    @POST("my_course_content.php")
    @FormUrlEncoded
    suspend fun my_course_content(@Field("course_id") course_id:String):Response<CourseContent>
    @POST("course_progress.php")
    suspend fun course_progress(@Body user_id:String,course_id:String,video_id:String):Response<course_progress>
    @POST("request_live_class.php")
    @FormUrlEncoded
    suspend fun request_live_class(@Field("user_id") user_id:String,@Field("course_id")course_id:String):Response<success_response_Liveclass>

    @POST("get_live_class_details.php")
    @FormUrlEncoded
    suspend fun get_live_class_details(@Field("course_id") course_id: String,@Field("user_id")user_id: String):Response<get_live_class_details>

    @POST("Purchase_live_class.php")
    @FormUrlEncoded
    suspend fun purchase_live_class(@Field("phone") phone: String,@Field("liveclass_id") course_id: String,@Field("cost") price: String):Response<success_response_Liveclass>

    @POST("changepassword.php")
    @FormUrlEncoded
    suspend fun changepass(@Field("userId")user_id: String,@Field("newPassword")newpass:String,@Field("oldPassword")oldpass:String):Response<success_response>

    @POST("Fetch_Wallet.php")
    @FormUrlEncoded
    suspend fun Fetch_Wallet(@Field("phone") phone: String):Response<Fetch_Wallet>
    @POST("withdraw.php")
    @FormUrlEncoded
    suspend fun Withdraw_Request(@Field("phone") phone: String,@Field("amount") amount: String,@Field("upiId") upiId: String):Response<withdraw>
    @GET("searchcourse.php")
    suspend fun GetSearch(@Query("courseName") courseName: String):Response<ArrayList<Course>>
    @POST("purchased_course.php")
    @FormUrlEncoded
    suspend fun is_course_purchased(@Field("phone") phone: String,@Field("course_id") course_id: String):Response<is_course_purchased>
    @POST("rate_course.php")
    @FormUrlEncoded
    suspend fun rate_course(@Field("user_id") user_id: String,@Field("course_id") course_id: String,@Field("rating") rating: String):Response<rate_res>
    @POST("my_live_class.php")
@FormUrlEncoded
suspend fun my_live_class(@Field("phone") user_id: String):Response<get_live_class_details>
    @POST("message.php")
    suspend fun helpsupport(@Body help:help_request):Response<help_supp>

}
 const val BASE_URL ="https://lifelearningeducation.in/api/"


// Retrofit Instance
object RetrofitInstance {

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
