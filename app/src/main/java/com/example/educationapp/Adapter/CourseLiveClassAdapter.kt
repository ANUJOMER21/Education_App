package com.example.educationapp.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.educationapp.APi.Live_class_details
import com.example.educationapp.Activity.BuyLiveClass
import com.example.educationapp.R
import com.example.educationapp.databinding.LiveRequestViewBinding
import java.text.SimpleDateFormat
import java.util.Locale

class CourseLiveClassAdapter(val context: Context, val list: ArrayList<Live_class_details? >):
    RecyclerView.Adapter<CourseLiveClassAdapter.VH>() {
    class VH (val binding:LiveRequestViewBinding):RecyclerView.ViewHolder(binding.root){
    }
    fun formatDate(inputDate: String): String {
        // Define the input and output date formats
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())

        // Parse the input date and format it to the desired output
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view=LayoutInflater
            .from(parent.context)
            .inflate(R.layout.live_request_view,parent,false)
        return VH(LiveRequestViewBinding.bind(view))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item=list[position]
        holder.binding.status.text=item?.status
        holder.binding.Status.text=item?.classTitle
        val description=item?.classDescription?:item?.message
        holder.binding.desc.text=description
        holder.binding.Status.visibility=if(item?.classTitle.isNullOrEmpty()) View.GONE else View.VISIBLE
Log.d("courseLiveClass",item!!.liveclassId.toString())
        if(item?.status.equals("payment pending")){
            holder.binding.amtll.visibility= View.VISIBLE
            holder.binding.classscheduled.visibility=View.GONE
        //    holder.binding.payamount.text="Pay Amount"
            holder.binding.tvAmt.text="Rs.${item?.price}"
            holder.binding.payamount.setOnClickListener {
                val intent=Intent(context, BuyLiveClass::class.java)

                 intent.putExtra("id",item!!.liveclassId)
                intent.putExtra("title",item!!.classTitle)
                intent.putExtra("cost",item!!.price)
                context.startActivity(intent)

            }
        }
        else if(item?.status.equals("class scheduled")){
            holder.binding.amtll.visibility= View.GONE
            holder.binding.classscheduled.visibility=View.VISIBLE
            holder.binding.tvTime.text=if(item?.startTime.isNullOrEmpty())"" else{
                formatDate(item.startTime.toString())
            }
            holder.binding.meetlink.text=item?.meetingLink


        }


    }


}