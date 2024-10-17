package com.example.educationapp.APi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(val success:String,val id:Int)

 data class Course(
                    @SerializedName("courseId"   ) var courseId   : String? = null,
                    @SerializedName("imageUrl"   ) var imageUrl   : String? = null,
                    @SerializedName("courseName" ) var courseName : String? = null,
                    @SerializedName("price"      ) var  price:String?=null,
                    @SerializedName("description") var description: String?=null,
                    @SerializedName("avg_rating") var avg_rating: String?=null,
                    @SerializedName("duration"   ) var duration   : String? = null):Serializable

 data class Category(
                      @SerializedName("categoryId" ) var categoryId : String? = null,
                      @SerializedName("name"       ) var name       : String? = null,
                      @SerializedName("imageUrl"   ) var imageUrl   : String? = null)

data class Slider(
                  @SerializedName("imageUrl" ) var imageUrl : String? = null,
                  @SerializedName("title"    ) var title    : String? = null)

data class ProfileDetails(
                    @SerializedName("success" ) var success : String?  = null,
                    @SerializedName("profile" ) var profile : Profile? = Profile()

)
data class Profile(
    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("email"           ) var email          : String? = null,
    @SerializedName("phone"           ) var phone          : String? = null,
    @SerializedName("secondary_phone" ) var secondaryPhone : String? = null,
    @SerializedName("referral_code"   ) var referralCode   : String? = null,
    @SerializedName("status"          ) var status         : String? = null,
    @SerializedName("username"          ) var username         : String? = null


)
data class UpdateProfileRequest(
    val phoneNumber: String,
    val email: String,
    val secondaryphoneNumber: String,
    val referralCode: String,
    val status: String,
    val username: String
)
data class Fetch_Wallet(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("amount"  ) var amount  : String?  = null
)
data class disc_success_response(
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("discounted_price" ) var discounted_price : String? = null,
    @SerializedName("success" ) var successbool : Boolean? = null,
)
data class success_response(
    @SerializedName("success" ) var success : String? = null,

    @SerializedName("status") var status : String? = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("discounted_price" ) var discounted_price : String? = null,
)
data class rate_res(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null
)
data class withdraw(
    @SerializedName("success"          ) var success         : Boolean? = null,
    @SerializedName("message"          ) var message         : String?  = null,
    @SerializedName("withdrawn_amount" ) var withdrawnAmount : String?  = null,
    @SerializedName("remaining_amount" ) var remainingAmount : String?  = null

)
data class MyCourse(
    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf()
)
data class Data (

    @SerializedName("phone"       ) var phone       : String? = null,
    @SerializedName("cost"        ) var cost        : String? = null,
    @SerializedName("course_id"   ) var courseId    : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("avg_rating") var avg_rating: String?=null,
    @SerializedName("course_image" ) var imageUrl : String? = null


)
data class is_course_purchased(

    @SerializedName("success"           ) var success          : Boolean? = null,
    @SerializedName("message"           ) var message          : String?  = null,
    @SerializedName("already_purchased" ) var alreadyPurchased : Boolean? = null

)
data class CourseContent(
    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<CourseContentData> = arrayListOf()
)
data class CourseContentData(
    @SerializedName("video_file_name"   ) var videoFileName    : String? = null,
    @SerializedName("video_title"       ) var videoTitle       : String? = null,
    @SerializedName("video_description" ) var videoDescription : String? = null

)
data class course_progress(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : course_progress_data?    = course_progress_data()
)
data class course_progress_data(
    @SerializedName("progress"              ) var progress             : ArrayList<Progress> = arrayListOf(),
    @SerializedName("total_videos"          ) var totalVideos          : String?             = null,
    @SerializedName("completed_videos"      ) var completedVideos      : String?             = null,
    @SerializedName("completion_percentage" ) var completionPercentage : String?             = null

)
data class Progress(
    @SerializedName("video_id"   ) var videoId   : String? = null,
    @SerializedName("watched_at" ) var watchedAt : String? = null
)
data class get_live_class_details(
    @SerializedName("success"  ) var success  : Boolean? = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : ArrayList<Live_class_details? > = arrayListOf(),

)
data class Live_class_details(
    @SerializedName("liveclass_id"      ) var liveclassId      : String? = null,
    @SerializedName("class_title"       ) var classTitle       : String? = null,
    @SerializedName("software_name"     ) var softwareName     : String? = null,
    @SerializedName("price"             ) var price            : String? = null,
    @SerializedName("class_description" ) var classDescription : String? = null,
    @SerializedName("meeting_link"      ) var meetingLink      : String? = null,
    @SerializedName("start_time"        ) var startTime        : String? = null,
    @SerializedName("status"            ) var status           : String? = null

)

