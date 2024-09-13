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
import com.example.educationapp.APi.Category
import com.example.educationapp.Activity.AllCourse
import com.example.educationapp.R

class CategoryAdapter2 (val context: Context, val categoryList: ArrayList<Category>) :
RecyclerView.Adapter<CategoryViewHolder2>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return CategoryViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder2, position: Int) {
        val item=categoryList[position];
        Glide.with(context).load(item.imageUrl).into(holder.imageview)
        holder.text.text=item.name
        holder.view.setOnClickListener {
            val intent: Intent =Intent(context, AllCourse::class.java)
            intent.putExtra("cat",item.name)
            intent.putExtra("id",item.categoryId)
            context.startActivity(intent)
        }
    }
}

class CategoryViewHolder2 (val view: View):RecyclerView.ViewHolder(view){
    val imageview=view.findViewById<ImageView>(R.id.civ)
    val text=view.findViewById<TextView>(R.id.subject)
}
