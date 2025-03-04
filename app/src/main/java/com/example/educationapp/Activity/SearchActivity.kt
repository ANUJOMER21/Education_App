package com.example.educationapp.Activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.CourseAdapter
import com.example.educationapp.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)
        val searchet=findViewById<EditText>(R.id.searchet)
        val search=findViewById<ImageView>(R.id.search)
        val notify:TextView=findViewById(R.id.notify)
        val rv:RecyclerView=findViewById(R.id.rv)
        rv.layoutManager=LinearLayoutManager(this)

        val repository= MainRepository()
        val viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        viewModel.searchState.observe(this){
            if(it!=null){
                notify.visibility= View.GONE
                rv.visibility=View.VISIBLE
                rv.adapter=CourseAdapter(this,it)
            }
            else{
                notify.visibility= View.VISIBLE
                rv.visibility=View.GONE
            }

        }
        search.setOnClickListener {
            viewModel.search(searchet.text.toString())
        }

    }
}