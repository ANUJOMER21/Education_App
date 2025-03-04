package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ApiViewModel
    private lateinit var misc: Misc
    private lateinit var  preferenceHelper:PreferenceHelper
    private fun setupViewModel() {
        val repository = MainRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ApiViewModel::class.java)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceHelper = PreferenceHelper(this@MainActivity);
        setupViewModel()
        observeLiveData()


        setContentView(R.layout.activity_main)
         CoroutineScope(Dispatchers.Main).launch {
             delay(2000)

             if(preferenceHelper.getUserId()!=-1){
                  viewModel.fetchProfile(preferenceHelper.getUserId().toString())

             }
             else {
                 val intent = Intent(this@MainActivity, LoginActivity::class.java)
                 startActivity(intent)
                 finish()
             }
         }

         }

    private fun observeLiveData() {
        viewModel.profileState.observe(this){
            profileState->
            if(profileState==null){
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return@observe
            }
            else {
                val profile = profileState?.profile
                if (profile != null) {
                    val status = profile.status
                    if (status != null) {
                        if (status.equals("active", true)) {
                            val intent = Intent(this@MainActivity, HomePage::class.java)
                            intent.putExtra("userid", preferenceHelper.getUserId())
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Your account is not active", Toast.LENGTH_SHORT)
                                .show()
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }


        }
    }
}
