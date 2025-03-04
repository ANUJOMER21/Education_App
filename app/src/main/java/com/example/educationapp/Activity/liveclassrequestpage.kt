package com.example.educationapp.Activity

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.educationapp.databinding.ActivityLiveclassrequestpageBinding

class liveclassrequestpage : AppCompatActivity() {
    lateinit var binding: ActivityLiveclassrequestpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLiveclassrequestpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val softname = intent.getStringExtra("softname")
        val desc = intent.getStringExtra("desc")
        val meetlink = intent.getStringExtra("meetlink")
        val starttime = intent.getStringExtra("starttime")
        binding.courseTitle.text=title
        binding.desc.text=desc
        binding.courseFee.text=starttime
        binding.totalfee.text=meetlink
        binding.back.setOnClickListener {
            finish()
        }
        if (!meetlink.isNullOrEmpty()) {

            binding.totalfee.paintFlags = binding.courseFee.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            binding.totalfee.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meetlink))
                startActivity(browserIntent)
            }
        }



    }
}