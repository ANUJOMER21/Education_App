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
        val courseImage=view.findViewById<ImageView>(R.id.course_image)
        val courseName=view.findViewById<TextView>(R.id.course_title)


        val description=view.findViewById<TextView>(R.id.description)
        val star1:ImageView=view.findViewById(R.id.star1)
        val star2:ImageView=view.findViewById(R.id.star2)
        val star3:ImageView=view.findViewById(R.id.star3)
        val star4:ImageView=view.findViewById(R.id.star4)
        val star5:ImageView=view.findViewById(R.id.star5)
        val rating:TextView=view.findViewById(R.id.rating)

    }

    override fun onBindViewHolder(holder: MyCourseAdapter.MyCourseViewHolder, position: Int) {
        val item = list[position]
        Log.d("mYcoues",item.toString())
       holder.courseName.text=item.title
        holder.description.text=item.description

        val rating=item.avg_rating?.toFloat()
        setrating(holder,rating)
        holder.rating.text=rating.toString()
        Glide.with(context).load(item.imageUrl).into(holder.courseImage)
        holder.view.setOnClickListener {
           val intent= Intent(context, CoursePage::class.java)
            intent.putExtra("courseid",item.courseId)
            intent.putExtra("coursetitle",item.title)
            intent.putExtra("image",item.imageUrl)
            context.startActivity(intent)
        }
    }
    private fun setrating(holder: MyCourseAdapter.MyCourseViewHolder, rating: Float?) {
        if(rating!=null){
            if(rating>=1){
                holder.star1.setImageResource(R.drawable.star_svgrepo_com_2)
            }
            if(rating>=2){
                holder.star2.setImageResource(R.drawable.star_svgrepo_com_2)
            }
            if(rating>=3){
                holder.star3.setImageResource(R.drawable.star_svgrepo_com_2)
            }
            if(rating>=4){
                holder.star4.setImageResource(R.drawable.star_svgrepo_com_2)
            }
            if(rating>=4.5){
                holder.star5.setImageResource(R.drawable.star_svgrepo_com_2)
            }



        }

    }
    override fun getItemCount(): Int {
     return   list.size
    }
}