package com.example.educationapp.APi

import com.codebyashish.autoimageslider.Models.ImageSlidesModel

interface RepositoryImpl {
    suspend fun login(username: String, password: String): Result<LoginResponse>
    suspend fun signup(username: String, password: String, phone: String, referal: String,email:String,secondphone:String): Result<LoginResponse>
    suspend fun GetSliders():Result<List<Slider>>
    suspend fun GetFeatureCourse():Result<ArrayList<Course>>
    suspend fun GetAllCourse():Result<ArrayList<Course>>
    suspend fun GetAllCategory():Result<ArrayList<Category>>
    suspend fun fetch_profile(phone: String):Result<ProfileDetails>
    suspend fun getCourseCategoryWise(categoryId:String):Result<ArrayList<Course>>
    suspend fun updateprofile(profile: UpdateProfileRequest):Result<success_response>
    suspend fun discount(courseid:String,copon:String):Result<success_response>
    suspend fun Purchase(phone: String,courseid: String,price:String):Result<success_response>
    suspend fun my_course(phone: String):Result<MyCourse>
    suspend fun my_course_content(course_id:String):Result<CourseContent>
    suspend fun course_progress(user_id: String,course_id: String,video_id:String):Result<course_progress>
    suspend fun request_live_class(user_id: String,course_id: String):Result<success_response>
    suspend fun get_live_class_details(phone: String):Result<get_live_class_details>
    suspend fun Purchase_live_class(phone: String,live_id: String,price: String):Result<success_response>
    suspend fun changepass(user_id: String,newpass:String,oldpass:String):Result<success_response>
    suspend fun fetch_wallet(phone: String):Result<Fetch_Wallet>
    suspend fun withdraw(phone: String,amount: String,upiid:String):Result<withdraw>
    suspend fun Searchet(courseName:String):Result<ArrayList<Course>>
    suspend fun is_course_purchased(phone: String,course_id: String):Result<is_course_purchased>
    suspend fun  rate_course(user_id: String,course_id: String,rating: String):Result<rate_res>
}