package com.example.educationapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.educationapp.APi.Course
import com.example.educationapp.Activity.CourseDesc
import com.example.educationapp.Activity.CoursePage
import com.example.educationapp.R

class CourseAdapter(
    val context: Context,
    val courselist:ArrayList<Course>
    ) :RecyclerView.Adapter<CourseAdapter.Vh>(){
    class Vh(view: View): RecyclerView.ViewHolder(view){
        val courseImage=view.findViewById<ImageView>(R.id.course_image)
        val courseName=view.findViewById<TextView>(R.id.course_title)
        val courseDur=view.findViewById<TextView>(R.id.course_dur)
        val coursePrice=view.findViewById<TextView>(R.id.cost)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_view, parent, false)
        return Vh(view)
    }

    override fun getItemCount(): Int {
      return courselist.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = courselist[position]
        holder.courseName.text=item.courseName
        holder.courseDur.text=if(item.duration.isNullOrEmpty())  "0h 0min" else item.duration
        holder.coursePrice.text="Rs. "+item.price
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .into(holder.courseImage)
        holder.itemView.setOnClickListener {
            val Intent= Intent(context, CourseDesc::class.java)
            Intent.putExtra("course",item)
            context.startActivity(Intent)
        }
    }
}