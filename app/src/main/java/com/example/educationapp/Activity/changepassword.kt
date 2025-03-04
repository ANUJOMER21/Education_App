package com.example.educationapp.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.databinding.ActivityChangepasswordBinding

class changepassword : AppCompatActivity() {
    lateinit var binding:ActivityChangepasswordBinding
    lateinit var viewmodel:ApiViewModel
    lateinit var misc:Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        misc=Misc(this)
        val uid=misc.getid()
        val repository= MainRepository()
        viewmodel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        binding=ActivityChangepasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { onBackPressed() }
        binding.changePasswordButton.setOnClickListener {
            val newpassword=binding.newPasswordEditText.text.toString()
            val oldpassword=binding.oldPasswordEditText.text.toString()
            val confpassword=binding.confirmPasswordEditText.text.toString()

            if(newpassword.isEmpty())misc.toast("Please Enter New Password")
            else if(oldpassword.isEmpty())misc.toast("Please Enter Old Password")
            else if(confpassword.isEmpty())misc.toast("Please Enter Confirm Password")
            else if(newpassword!=confpassword)misc.toast("Password Doesn't Match")
            else{
                viewmodel.changepassword(uid.toString(),newpassword,oldpassword)
                viewmodel.changepass.observe(this){
                    if(it!=null){
                        it.message?.let { it1 -> misc.toast(it1) }
                        finish()
                    }
                    else{
                        misc.toast("Something Went Wrong")
                    }

                }
            }
        }


        }
    }
