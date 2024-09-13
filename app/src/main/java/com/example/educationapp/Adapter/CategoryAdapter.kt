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
import com.example.educationapp.Adapter.CourseAdapter.Vh
import com.example.educationapp.R

class CategoryAdapter(val context: Context,val categoryList: ArrayList<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
          val item=categoryList[position];
          Glide.with(context).load(item.imageUrl).into(holder.imageview)
          holder.text.text=item.name
        holder.view.setOnClickListener {
            val intent:Intent=Intent(context, AllCourse::class.java)
            intent.putExtra("cat",item.name)
            intent.putExtra("id",item.categoryId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return categoryList.size
    }
}

class CategoryViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val imageview=view.findViewById<ImageView>(R.id.cat_image)
        val text=view.findViewById<TextView>(R.id.cat_name)

}
