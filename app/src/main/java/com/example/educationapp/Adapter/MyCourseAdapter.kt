package com.example.educationapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.educationapp.APi.CourseContent
import com.example.educationapp.APi.Data
import com.example.educationapp.Activity.CoursePage
import com.example.educationapp.R

class MyCourseAdapter(val context: Context,val list:ArrayList<Data>) : RecyclerView.Adapter<MyCourseAdapter.MyCourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyCourseAdapter.MyCourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mycourseview, parent, false)
        return MyCourseViewHolder(view)
    }

    class MyCourseViewHolder (val view:View):RecyclerView.ViewHolder(view){
        val imageview=view.findViewById<ImageView>(R.id.course_image)
        val text=view.findViewById<TextView>(R.id.course_title)


    }

    override fun onBindViewHolder(holder: MyCourseAdapter.MyCourseViewHolder, position: Int) {
        val item = list[position]
        Log.d("mYcoues",item.toString())
       holder.text.text=item.title
        Glide.with(context).load(item.imageUrl).into(holder.imageview)
        holder.view.setOnClickListener {
           val intent= Intent(context, CoursePage::class.java)
            intent.putExtra("courseid",item.courseId)
            intent.putExtra("coursetitle",item.title)
            intent.putExtra("image",item.imageUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
     return   list.size
    }
}