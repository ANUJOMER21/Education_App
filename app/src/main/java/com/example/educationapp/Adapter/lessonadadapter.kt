package com.example.educationapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.APi.CourseContentData
import com.example.educationapp.R
import com.google.android.exoplayer2.util.Log

class lessonadadapter(var data    : ArrayList<CourseContentData>,val context: Context,val type:Int,val runlesson:(lessonid:String)->Unit):RecyclerView.Adapter<lessonadadapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): lessonadadapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lesson, parent, false)
        return lessonadadapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: lessonadadapter.ViewHolder, position: Int) {
       val item=data[position]
        holder.lessonname.text=item.videoTitle
        holder.play.setOnClickListener {
            if(type==0){
                Toast.makeText(context,"Please buy course",Toast.LENGTH_SHORT).show()
            }
            else{
                Log.d("lessonid",item!!.toString())
                runlesson(item.videoFileName!!)
            }

        }
    }
 class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val play=itemView.findViewById<ImageView>(R.id.play)
     val lessonname=itemView.findViewById<TextView>(R.id.lessonname)
 }
    override fun getItemCount(): Int {
     return data.size
    }
}