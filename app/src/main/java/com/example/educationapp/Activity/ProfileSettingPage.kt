package com.example.educationapp.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.APi.UpdateProfileRequest
import com.example.educationapp.Misc
import com.example.educationapp.databinding.ActivityProfileSettingPageBinding

class ProfileSettingPage : AppCompatActivity() {
    lateinit var binding: ActivityProfileSettingPageBinding
    private lateinit var viewmodel: ApiViewModel
    private lateinit var misc: Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileSettingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressed()
        }
        val repository= MainRepository()
        viewmodel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        misc=Misc(context = this)
        val uid=misc.getid()
        var phone=""
        var referal=""
        var status=""
        viewmodel.fetchProfile(uid.toString())
        viewmodel.profileState.observe(this) { profileResponse ->
            profileResponse?.let {
                binding.etUsername.setText(it.profile?.username)
                binding.etEmail.setText(it.profile?.email)
                binding.etSecondaryPhone.setText(it.profile?.secondaryPhone)
                binding.status.text=it.profile?.status
                binding.refcode.text=it.profile?.referralCode
                binding.phone.text=it.profile?.phone
                phone=it.profile?.phone.toString()
                referal=it.profile?.referralCode.toString()
                status=it.profile?.status.toString()

                         }

    }
        binding.btnSave.setOnClickListener {
            val username=binding.etUsername.text.toString()
            val email=binding.etEmail.text.toString()
            val secphone=binding.etSecondaryPhone.text.toString()

            if(username.isEmpty()){
                misc.toast("Enter Username")
                return@setOnClickListener
            }
           else if(email.isEmpty()){
                misc.toast("Enter Email")
                return@setOnClickListener
            }
           else if(secphone.isEmpty()){
                misc.toast("Enter Parent Phone")
                return@setOnClickListener
            }
            else{
            viewmodel.updateProfile(

                UpdateProfileRequest(
                    phoneNumber = phone,
                    email = email,

                    secondaryphoneNumber = secphone,
                    referralCode =referal,
                    status = status,
                    username = username



                )
            )
            viewmodel.updateProfileState.observe(this){
                if(it!=null){
                    if(it.success=="true"){
                        misc.toast("Profile Updated Successfully")
                        finish()
                    }
                    else{
                        misc.toast("Something went wrong")
                    }
                }
                else{
                    misc.toast("Something went wrong")
                }
            }
            }

        }


}
    }