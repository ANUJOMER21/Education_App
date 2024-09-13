package com.example.educationapp.APi

import com.google.gson.annotations.SerializedName

data class LoginRequests(val phone:String, val password:String)
data class SignupRequst(val username:String,val password: String,val phoneNumber:String,val referralCode:String,val email:String,val secondaryphoneNumber:String)
data class Discount(val course_id:String,val coupon_code:String)
data class Purchase(val phone:String,val cost:String,val course_id:String)
data class USERID( @SerializedName("userid" ) var userid : String? = null)