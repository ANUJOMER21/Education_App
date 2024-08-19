package com.example.educationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.LoginResponse
import com.example.educationapp.APi.LoginUiState
import com.example.educationapp.APi.SignupUiState
import com.example.educationapp.APi.TestRepository
import com.example.educationapp.databinding.ActivityLoginBinding
import com.example.educationapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var Misc:Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        Misc= Misc(applicationContext)
        val repository= TestRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        viewModel.signupState.observe(this) {state->
            when(state){
                is SignupUiState.Success->{
                    handleLoginSuccess(state.response)
                }
                is SignupUiState.Loading->{
                    showprogress()
                }
                is SignupUiState.Error->{
                    showerror(state.message)

                }
            }
        }
        binding.signup.setOnClickListener {
            val email = binding.mobile.text.toString()
            val password = binding.password.text.toString()
            val confpass=binding.confirmPassword.text.toString()
            val name=binding.name.text.toString()
            val Referal=binding.referral.text.toString()
            if(email.isEmpty()){
                Misc.toast("Please Enter Mobile")
            }
            else if(name.isEmpty()){
                Misc.toast("Please Enter Name")
            }
            else if(password.isEmpty()){
                Misc.toast("Please Enter Password")
            }
            else if(email.length<10){
                Misc.toast("Please Enter Valid Mobile")
            }
            else if(password.length<6){
                Misc.toast("Password must be greater than 6")
            }
            else if(confpass.isEmpty()) {
                Misc.toast("Please Enter Confirm Password")
            }
            else {

                viewModel.signup(username = name, phone = email, password =  password, referal = Referal)
            }
        }
        binding.alreadyHaveAccount.setOnClickListener {
            val Intent = Intent(this,LoginActivity::class.java)
            startActivity(Intent)
        }

    }
    private fun showerror(message: String) {
        Misc.toast(message)
        binding.progress.visibility= View.GONE
        binding.signup.visibility= View.VISIBLE
    }

    private fun showprogress() {
        binding.progress.visibility= View.VISIBLE
        binding.signup.visibility= View.GONE
    }

    private fun handleLoginSuccess(response: LoginResponse) {
        if(response.success.equals("true")){
            Misc.toast("Login Success")
            val Intent = Intent(this,HomePage::class.java)
            Intent.putExtra("userid",response.id)
            startActivity(Intent)
            finish()
        }

    }
}