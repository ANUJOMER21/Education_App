package com.example.educationapp.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.lessonadadapter
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityCoursePageBinding
import com.google.android.material.button.MaterialButton

class CoursePage : AppCompatActivity() {
    private lateinit var Misc:Misc
    private lateinit var ViewModel:ApiViewModel
    private lateinit var Binding: ActivityCoursePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Misc=Misc(this)
        val userid=Misc.getid()
        val repository= MainRepository()
        ViewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        Binding= ActivityCoursePageBinding.inflate(layoutInflater)
        setContentView(Binding.root)

        val courseId=intent.getStringExtra("courseid")
        val coursetitle=intent.getStringExtra("coursetitle")
        val image=intent.getStringExtra("image")
        Binding.courseTitle.text=coursetitle
        Binding.back.setOnClickListener {
            finish()
        }
        val imageview=Binding.videoView
        Glide.with(this).load(image).into(imageview)

        val rv=Binding.scv
        rv.layoutManager=LinearLayoutManager(this)
        val lessonadadapter=lessonadadapter(ArrayList(),this,1){
            val intent=Intent(this,VideoActivity::class.java)
            intent.putExtra("videourl",it)
            startActivity(intent)
        }
        rv.adapter=lessonadadapter
        ViewModel.fetchCourseContent(courseId!!)
        ViewModel.courseContentState.observe(this){
            if (it != null) {
                if(it.success == true){
                    val list=it.data
                    lessonadadapter.data=list;
                    lessonadadapter.notifyDataSetChanged()
                }
            }
        }
        Binding.rlc
            .setOnClickListener { showRequestLiveClassDialog(userid,courseId) }


        }
    private fun showRequestLiveClassDialog(userid: Int?, courseId: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_request_live_class)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // Initialize buttons
        val yesButton = dialog.findViewById<MaterialButton>(R.id.btnYes)
        val noButton = dialog.findViewById<MaterialButton>(R.id.btnNo)

        // Yes button click listener
        yesButton.setOnClickListener {
            // Handle the logic to request live class here
            sendLiveClassRequest(userid,courseId)
            dialog.dismiss()
        }

        // No button click listener
        noButton.setOnClickListener {
            dialog.dismiss()
        }

        // Show the dialog
        dialog.show()
    }

    private fun sendLiveClassRequest(userid: Int?, courseId: String) {
        // Example: Logic to request live class, such as an API call

        ViewModel.requestLiveClass(userid!!.toString(),courseId)
        ViewModel.liveClassRequestState.observe(this){
            if (it != null) {
                if(it.status == "success"){
                    Misc.toast("Live class requested!")
                }
                else{
                    Misc.toast(it.message!!)
                }
            }
            else{
                Misc.toast("Something went wrong")
            }
        }
    }
    }
