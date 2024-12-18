package com.example.educationapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.educationapp.APi.Course
import com.example.educationapp.Activity.BuyLiveClass
import com.example.educationapp.Activity.liveclass_page
import com.example.educationapp.Activity.liveclassrequestpage
import com.example.educationapp.R
import com.example.educationapp.databinding.LessonviewBinding

class LiveClassAdapter(val context: Context, val list: ArrayList<Course>):RecyclerView.Adapter<LiveClassAdapter.VH>() {
class VH(val binding:LessonviewBinding):RecyclerView.ViewHolder(binding.root){

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveClassAdapter.VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lessonview, parent, false)
        return VH(LessonviewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: LiveClassAdapter.VH, position: Int) {
       val item=list[position]
        Glide.with(context).load(item.imageUrl).into(holder.binding.courseImage)
        holder.binding.tvSubjectName.text=item.courseName
        holder.binding.message.text="${item.live_classs_no} Live Lesson Requests"
        holder.binding.mcv1.setOnClickListener {
            val intent=Intent(context,liveclass_page::class.java)
            intent.putExtra("courseid",item.courseId.toString())
            context.startActivity(intent)


        }


    }

    override fun getItemCount(): Int {
       return list.size
    }
}