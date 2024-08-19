package com.example.educationapp

import android.content.Context
import android.widget.Toast

class Misc (val context: Context){
    fun toast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    fun startActivity(java: Class<SignUpActivity>) {

    }
}