package com.example.educationapp.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.CourseAdapter
import com.example.educationapp.Misc
import com.example.educationapp.databinding.ActivityAllCourseBinding

class AllCourse : AppCompatActivity() {
    lateinit var binding: ActivityAllCourseBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var Misc: Misc
    private fun setFeatureCourse() {
        val courseRecyclerView=binding.rv
        courseRecyclerView.layoutManager=LinearLayoutManager(this)
        viewModel.getAllCourse()
        viewModel.allCourseState.observe(this){
            val adapter= CourseAdapter(this,it)
            courseRecyclerView.adapter=adapter
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding=ActivityAllCourseBinding.inflate(layoutInflater)
        val repository= MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        val text=intent.getStringExtra("cat")
        val id=intent.getStringExtra("id")
        Log.d("ID",id!!)
        binding.category.text=text
        binding.back.setOnClickListener {
            finish()

        }
        setContentView(binding.root)
        if(id.equals("0")){
            setFeatureCourse()
        }
        else{
            viewModel.getCourseCategoryWise(id.toString())
            viewModel.courseCategoryState.observe(this){
                if(it.isNullOrEmpty()){

                }
                Log.d("EMPTY",it!!.size.toString())
                val adapter= CourseAdapter(this,it)
                val courseRecyclerView=binding.rv
                courseRecyclerView.layoutManager=LinearLayoutManager(this)
                 courseRecyclerView.adapter=adapter
            }
        }

    }
}