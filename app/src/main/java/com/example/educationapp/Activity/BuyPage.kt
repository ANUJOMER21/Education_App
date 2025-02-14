package com.example.educationapp.Activity

import android.content.Context
import android.content.Intent
import android.net.Uri
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
                if(it.success_bol==true){
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                 finish()

                }
                else{
                    Misc(this).toast(it.message!!)

                }
            }
        }
        val mobilenumber="+919991329616"
        binding.buynow.setOnClickListener{
            val message = "Hello sir, I am interested in the course ${course?.courseName}. I would like to purchase it for $price rupees. Please process my request. My registered phone number is $phone."
            //sendMessageViaWhatsApp(mobilenumber, message, this)

              viewModel.purchaseCourse(phone,course!!.courseId!!,price.toString())
        }

    }

    fun sendMessageViaWhatsApp(phoneNumber: String, message: String, context: Context) {
        try {
            // Format phone number with country code, e.g., "1234567890" -> "+1234567890"
            val formattedNumber = if (phoneNumber.startsWith("+")) phoneNumber else "+$phoneNumber"

            // Create the intent to send the message
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/$formattedNumber?text=${Uri.encode(message)}")

            // Start the WhatsApp activity
            context.startActivity(intent)
        } catch (e: Exception) {
            // Show an error if WhatsApp is not installed
            Toast.makeText(context, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
    }

}