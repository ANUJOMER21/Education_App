package com.example.educationapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.LoginResponse
import com.example.educationapp.APi.LoginUiState
import com.example.educationapp.APi.TestRepository
import com.example.educationapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var Misc:Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Misc= Misc(applicationContext)
        val repository= TestRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        viewModel.loginState.observe(this) {state->
            when(state){
                is LoginUiState.Success->{
                    handleLoginSuccess(state.response)
                }
                is LoginUiState.Loading->{
                    showprogress()
                }
                is LoginUiState.Error->{
                    showerror(state.message)

                }
            }
        }
        binding.signin.setOnClickListener {
            val email = binding.emailet.text.toString()
            val password = binding.password.text.toString()
            if(email.isEmpty()){
                Misc.toast("Please Enter Mobile")
            }
            else if(password.isEmpty()){
                Misc.toast("Please Enter Password")
            }
            else if(email.length<10){
                Misc.toast("Please Enter Valid Mobile")
            }
            else {

                viewModel.login(email, password)
            }
        }
        binding.Signup.setOnClickListener {
            val Intent =Intent(this,SignUpActivity::class.java)
            startActivity(Intent)
        }


    }

    private fun showerror(message: String) {
       Misc.toast(message)
        binding.progress.visibility= View.GONE
        binding.signin.visibility=View.VISIBLE
    }

    private fun showprogress() {
        binding.progress.visibility= View.VISIBLE
        binding.signin.visibility=View.GONE
    }

    private fun handleLoginSuccess(response: LoginResponse) {
        if(response.success.equals("true")){
            Misc.toast("Login Success")
            val Intent =Intent(this,HomePage::class.java)
            Intent.putExtra("userid",response.id)
            startActivity(Intent)
            finish()
        }

    }
}