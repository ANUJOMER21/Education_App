package com.example.educationapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.APi.Live_class_details
import com.example.educationapp.Activity.BuyLiveClass
import com.example.educationapp.Activity.liveclassrequestpage
import com.example.educationapp.R
import com.example.educationapp.databinding.LessonviewBinding

class LiveClassAdapter(val context: Context, val list: ArrayList<Live_class_details?>):RecyclerView.Adapter<LiveClassAdapter.VH>() {
class VH(val binding:LessonviewBinding):RecyclerView.ViewHolder(binding.root){

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveClassAdapter.VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lessonview, parent, false)
        return VH(LessonviewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: LiveClassAdapter.VH, position: Int) {
       holder.binding.tvSubjectName.text=list[position]?.classTitle
        val status=list[position]?.status
        holder.binding.message.text=list[position]?.status
        if(status.equals("waiting for response")){
            holder.binding.rlDate.visibility= View.GONE

        }
        else if(status.equals("payment pending")){


            holder.binding.rlDate.visibility= View.GONE
            holder.binding.payment.visibility=View.VISIBLE
            holder.binding.payment.setOnClickListener {
                val intent= Intent(context, BuyLiveClass::class.java)
                intent.putExtra("id",list[position]?.liveclassId)
                intent.putExtra("title",list[position]?.classTitle)
                intent.putExtra("cost",list[position]?.price)
                context.startActivity(intent)
            }

        }
        else{
            holder.binding.rlDate.visibility= View.VISIBLE
            holder.binding.tvDate.text=list[position]?.startTime
            holder.binding.liveclass.visibility=View.VISIBLE

            holder.binding.liveclass.setOnClickListener {
                val intent= Intent(context, liveclassrequestpage::class.java)
                intent.putExtra("id",list[position]?.liveclassId)
                intent.putExtra("title",list[position]?.classTitle)
                intent.putExtra("softname",list[position]?.softwareName)
                intent.putExtra("desc",list[position]?.classDescription)
                intent.putExtra("meetlink",list[position]?.meetingLink)
                intent.putExtra("starttime",list[position]?.startTime)
                context.startActivity(intent)
            }
        }


    }

    override fun getItemCount(): Int {
       return list.size
    }
}