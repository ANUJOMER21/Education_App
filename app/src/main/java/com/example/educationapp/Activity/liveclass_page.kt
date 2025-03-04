package com.example.educationapp.Activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.CourseLiveClassAdapter
import com.example.educationapp.Misc
import com.example.educationapp.R

class liveclass_page : AppCompatActivity() {
    private lateinit var viewmodel: ApiViewModel
    private lateinit var courseid:String
    private lateinit var misc:Misc
    override fun onResume() {
        super.onResume()
        viewmodel.fetchLiveClassDetails(courseid,misc.getid().toString())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liveclass_page)
        val back=findViewById<ImageView>(R.id.back)
        back.setOnClickListener { onBackPressed() }
         courseid=intent.getStringExtra("courseid")!!
         misc=Misc(this)
        val rv=findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rlv1)
        val  repository=MainRepository()
        viewmodel= ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        rv.layoutManager=androidx.recyclerview.widget.LinearLayoutManager(this)

        viewmodel.fetchLiveClassDetails(courseid,misc.getid().toString())

        viewmodel.liveClassDetailsState.observe(this){
            get_live_class_details->
            val list=get_live_class_details!!.data
            val adapter=CourseLiveClassAdapter(this,list)
            rv.adapter=adapter
            adapter.notifyDataSetChanged()
        }
    }
}