package com.example.educationapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.CategoryAdapter
import com.example.educationapp.Adapter.CategoryAdapter2
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivitySeeAllCategoriesBinding

class SeeAllCategories : AppCompatActivity() {
    private lateinit var binding: ActivitySeeAllCategoriesBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var Misc: Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySeeAllCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Misc= Misc(this)
        val repository= MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)

        binding.back.setOnClickListener { onBackPressed() }
        setCategory()


    }

    private fun setCategory() {
        val categoryRecyclerView=binding.crv
        categoryRecyclerView.layoutManager= GridLayoutManager(this,3)
        viewModel.getAllCategory()
        viewModel.allCategoryState.observe(this){
            val adapter= CategoryAdapter2(this,it)
            categoryRecyclerView.adapter=adapter
        }
    }
}