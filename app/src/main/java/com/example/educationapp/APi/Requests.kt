package com.example.educationapp.APi

data class LoginRequests(val mobile:String, val password:String)
data class SignupRequst(val name:String,val password: String,val phone:String,val referral:String)

