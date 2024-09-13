package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.APi.Profile
import com.example.educationapp.APi.UpdateProfileRequest
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityIntialprofileBinding

class intialprofile : AppCompatActivity() {
    private lateinit var binding: ActivityIntialprofileBinding
    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntialprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra("userid", -1)
        val phone = intent.getStringExtra("phone")

        if (userId == -1) {
            showToast("Sorry, User can't be registered")
            navigateToLogin()
            return
        }

        val repository = MainRepository()
        val viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)

        setupObservers(viewModel, userId)
        setupSubmitButton(viewModel)

        viewModel.fetchProfile(userId.toString())
    }

    private fun setupObservers(viewModel: ApiViewModel, userId: Int) {
        viewModel.profileState.observe(this) { profileResponse ->
            profileResponse?.let {
                profile = it.profile
            } ?: showToast("Failed to load profile")
        }

        viewModel.updateProfileState.observe(this) { updateResponse ->
            updateResponse?.let {
                if (it.success == "true") {
                    it.message?.let { it1 -> showToast(it1) }
                    navigateToMain(userId)
                } else {
                    it.message?.let { it1 -> showToast(it1) }
                }
            } ?: showToast("Something went wrong")
        }
    }

    private fun setupSubmitButton(viewModel: ApiViewModel) {
        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val secondaryPhone = binding.secPhone.text.toString()

            if (validateInput(email, secondaryPhone)) {
                profile?.let { userProfile ->
                    val updateProfileRequest = UpdateProfileRequest(
                        email = email,
                        phoneNumber = userProfile.phone ?: "",
                        secondaryphoneNumber = secondaryPhone,
                        referralCode = userProfile.referralCode ?: "",
                        status = userProfile.status ?: "",
                        username = userProfile.username ?: ""
                    )
                    viewModel.updateProfile(updateProfileRequest)
                    showToast("Profile Updated")
                } ?: showToast("Profile data is missing")
            }
        }
    }

    private fun validateInput(email: String, secondaryPhone: String): Boolean {
        return when {
            email.isEmpty() -> {
                showToast("Please Enter Email")
                false
            }
            secondaryPhone.isEmpty() -> {
                showToast("Please Enter Parent Phone")
                false
            }
            secondaryPhone.length < 10 -> {
                showToast("Please Enter Valid Parent Phone")
                false
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun navigateToMain(userId: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userid", userId)
        startActivity(intent)
        finish()
    }
}
