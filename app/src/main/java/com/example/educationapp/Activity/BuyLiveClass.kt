package com.example.educationapp.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityBuyLiveClassBinding

class BuyLiveClass : AppCompatActivity() {
    lateinit var binding: ActivityBuyLiveClassBinding
    lateinit var viewModel: ApiViewModel
    lateinit var Misc: Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBuyLiveClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Misc=Misc(this)
        val repository= MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        val uid=Misc.getid();
        var phone=""
        viewModel.fetchProfile(uid.toString())
        viewModel.profileState.observe(this){
            if(it!=null){
                if(it.success.equals("true")) {
                    val p = it.profile
                    if (p != null) {

                        phone= p.phone.toString()
            }
        }
                }
            else{
                Misc(this).toast("Something went wrong")
            }
        }

        val cid=intent.getStringExtra("id")
        Log.d("BuyLiveClass_id",cid.toString())
        val title=intent.getStringExtra("title")
        val cost=intent.getStringExtra("cost")
        binding.coursename.text=title
        binding.courseFee.text="Rs. "+cost

        binding.back.setOnClickListener {
            finish()
        }
        binding.totalfee.text="Rs. "+cost
        binding.buynow.setOnClickListener {
           viewModel.purchaseLiveClass(
               phone,
               cid.toString(),
               cost.toString()
           )
            viewModel.liveClassRequestState.observe(this){
                if(it!=null){
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}