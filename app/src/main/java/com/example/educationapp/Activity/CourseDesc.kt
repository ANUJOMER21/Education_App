package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.Course
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.lessonadadapter
import com.example.educationapp.Misc
import com.example.educationapp.databinding.ActivityCourseDescBinding

class CourseDesc : AppCompatActivity() {
    private lateinit var binding: ActivityCourseDescBinding
    private lateinit var viewmodel:ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCourseDescBinding.inflate(layoutInflater)
        val repository= MainRepository()
        viewmodel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        setContentView(binding.root)
        binding.lesson.text="0 Lessons"
        binding.back.setOnClickListener {
            onBackPressed()
        }
        val course=intent.getSerializableExtra("course") as? Course
        if(course!=null) {
            Glide.with(this).load(course.imageUrl).into(binding.banner)
            binding.coursename.text = course.courseName
            binding.duartion.text = course.duration
            binding.cost.text="Rs. "+course.price
            binding.description.text=course.description
        }
        else{
            Toast.makeText(this,"Course not found",Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.gtc.setOnClickListener {
            val intent=Intent(this,CoursePage::class.java)
            intent.putExtra("courseid",course!!.courseId)
            intent.putExtra("coursetitle",course!!.courseName)
            intent.putExtra("image",course!!.imageUrl)
            startActivity(intent)

        }
        binding.buyNow.setOnClickListener {
             val Intent= Intent(this,BuyPage::class.java)
            Intent.putExtra("course",course)
            startActivity(Intent)
        }
        if(course!=null){
            iscoursepurchsaed(course)

        viewmodel.fetchCourseContent(course.courseId!!)
            val lcrv=binding.lessonrv
            lcrv.layoutManager=LinearLayoutManager(this)
            viewmodel.courseContentState.observe(this){
                if(it!=null) {
                    lcrv.adapter = lessonadadapter(it.data,this,0){

                    }
                    val size=it.data.size
                    binding.lesson.text="$size Lessons"
                    lcrv.adapter!!.notifyDataSetChanged()
                }
            }

        }
    }

    private fun iscoursepurchsaed(course: Course) {
       val uid=Misc(this).getid()
        if(uid!=null){
            viewmodel.fetchProfile(uid.toString())
            viewmodel.profileState.observe(this){
                if(it!=null){
                    val phone=it.profile!!.phone
                    viewmodel.iscoursepurchased(phone!!,course!!.courseId!!)
                    viewmodel.iscoursepur.observe(this){
                        if(it!=null) {
                            if (it.alreadyPurchased == true) {
                                binding.rlbuy.visibility = android.view.View.GONE
                                binding.Rlgtc.visibility = android.view.View.VISIBLE
                            }
                            else{
                                binding.rlbuy.visibility = android.view.View.VISIBLE
                                binding.Rlgtc.visibility = android.view.View.GONE
                            }
                        }
                    }
                }
            }
        }

    }
}