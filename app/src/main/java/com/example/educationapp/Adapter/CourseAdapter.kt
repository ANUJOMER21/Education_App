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
import com.example.educationapp.R

class CourseAdapter(
    val context: Context,
    val courselist:ArrayList<Course>,val ishome:Boolean=false
    ) :RecyclerView.Adapter<CourseAdapter.Vh>(){
    class Vh(view: View): RecyclerView.ViewHolder(view){
        val courseImage=view.findViewById<ImageView>(R.id.course_image)
        val courseName=view.findViewById<TextView>(R.id.course_title)
        val courseDur=view.findViewById<TextView>(R.id.course_dur)
        val coursePrice=view.findViewById<TextView>(R.id.cost)
      //  val description=view.findViewById<TextView>(R.id.description)
        val star1:ImageView=view.findViewById(R.id.star1)
        val star2:ImageView=view.findViewById(R.id.star2)
        val star3:ImageView=view.findViewById(R.id.star3)
        val star4:ImageView=view.findViewById(R.id.star4)
        val star5:ImageView=view.findViewById(R.id.star5)
        val rating:TextView=view.findViewById(R.id.rating)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        val view = LayoutInflater.from(parent.context)
            .inflate(
            if(ishome) R.layout.course_view2 else R.layout.course_view
                , parent, false)
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
      //  holder.description.text=item.description

        val rating= item.avg_rating?.toFloat()
        holder.rating.text=(rating).toString()
        setrating(holder,rating)
        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .into(holder.courseImage)
        holder.itemView.setOnClickListener {
            val Intent= Intent(context, CourseDesc::class.java)
            Intent.putExtra("course",item)
            context.startActivity(Intent)
        }

    }

    private fun setrating(holder: CourseAdapter.Vh, rating: Float?) {
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
}