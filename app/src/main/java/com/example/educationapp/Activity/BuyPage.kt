package com.example.educationapp.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.Course
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityBuyPageBinding

class BuyPage : AppCompatActivity() {
    lateinit var  binding: ActivityBuyPageBinding
    lateinit var viewModel: ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBuyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository= MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        var phone="";
        var price=0.0
        binding.back.setOnClickListener {
            onBackPressed()
        }
        val user=PreferenceHelper(context = this).getUserId()
        viewModel.fetchProfile(user.toString())
        viewModel.profileState.observe(this){

            if(it!=null){
                if(it.success.equals("true")) {
                    val p = it.profile
                    if (p != null) {

                        phone=p.phone.toString()
                    }
                }
            }
            else{
                Toast.makeText(this@BuyPage,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
        val course=intent.getSerializableExtra("course") as? Course
        if(course!=null){
        binding.coursename.text=course.courseName

            binding.courseFee.text="Rs. "+course.price
            binding.fee.text="Rs. 0"
            binding.totalfee.text="Rs. "+course.price
            price=course.price!!.toDouble()
            }
        else{
            finish()
        }
        viewModel.discountState.observe(this){
            if(it==null){
                Toast.makeText(this@BuyPage,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
            else {
                if (it!!.successbool==true) {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.fee.text = "Rs. sdsss" + it.discounted_price
                    binding.totalfee.text =
                        "Rs. " + (course!!.price!!.toDouble() - it.discounted_price!!.toDouble())
                    price=(course!!.price!!.toDouble() - it.discounted_price!!.toDouble())
                } else {
                    Misc(this).toast(it!!.message!!)
                }
            }
        }
        binding.apply.setOnClickListener{
            val referal=binding.referral.text.toString()
            if(referal.isNotEmpty()){
                viewModel.applyDiscount(course!!.courseId!!,referal)
            }
            else{
                Misc(this).toast("Please enter referral code")
            }
        }
        viewModel.purchaseState.observe(this){
            if(it==null){
                Toast.makeText(this@BuyPage,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
            else{
                if(it.success.equals("true")){
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                 finish()

                }
                else{
                    Misc(this).toast(it.message!!)

                }
            }
        }
        binding.buynow.setOnClickListener{
            viewModel.purchaseCourse(phone,course!!.courseId!!,price.toString())
        }

    }
}