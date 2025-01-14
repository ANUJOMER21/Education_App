package com.example.educationapp.APi

import com.google.gson.annotations.SerializedName

data class LoginRequests(val phone:String, val password:String)
data class SignupRequst( @SerializedName("username"        ) var username       : String? = null,
                         @SerializedName("email"           ) var email          : String? = null,
                         @SerializedName("password"        ) var password       : String? = null,
                         @SerializedName("phoneNumber"     ) var phoneNumber    : String? = null,
                         @SerializedName("referralCode"    ) var referralCode   : String? = null,
                         @SerializedName("secondary_phone" ) var secondaryPhone : String? = null)
data class USERID( @SerializedName("userid" ) var userid : String? = null)