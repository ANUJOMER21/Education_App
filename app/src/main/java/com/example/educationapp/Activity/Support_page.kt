package com.example.educationapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivitySupportPageBinding

class Support_page : AppCompatActivity() {
    private lateinit var binding: ActivitySupportPageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySupportPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener { onBackPressed() }
        val page_type=intent.getStringExtra("page_type")
        setContent(page_type,binding)

    }

    private fun setContent(pageType: String?, binding: ActivitySupportPageBinding) {
        if(pageType=="about"){
            binding.supp.text="About Us"
            binding.content.text=getString(R.string.about_us)

        }
        else if(pageType=="tc"){
            binding.supp.text="Terms and Conditions"
            binding.content.text=getString(R.string.tc)
        }
        else{
            binding.supp.text="Privacy Policy"
            binding.content.text=getString(R.string.pp)

        }

    }
}