package com.example.educationapp.Activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.Phonepe.PaymentRepository
import com.example.educationapp.Phonepe.PaymentState
import com.example.educationapp.Phonepe.PaymentUtils
import com.example.educationapp.Phonepe.PaymentViewModel
import com.example.educationapp.Phonepe.PaymentViewModelFactory
import com.example.educationapp.databinding.ActivityBuyLiveClassBinding

class BuyLiveClass : AppCompatActivity() {
    private fun setupPhonePe() {
        val paymentUtils = PaymentUtils()
        val paymentRepository = PaymentRepository(this, paymentUtils)
        phone_pe_viewmodel= ViewModelProvider(this, PaymentViewModelFactory(paymentRepository)).get(PaymentViewModel::class.java)

    }
    lateinit var binding: ActivityBuyLiveClassBinding
    lateinit var viewModel: ApiViewModel
    lateinit var phone_pe_viewmodel: PaymentViewModel
    private val B2B_PG_REQUEST_CODE = 777
    lateinit var Misc: Misc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupPhonePe()
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
        viewModel.liveClassRequestState.observe(this){
            if(it!=null){
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                finish()
            }
            else{
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
        binding.buynow.setOnClickListener {

            val message = "Hello sir, I am interested in the live class of id ${cid}. I would like to enroll in it for $cost rupees. Please process my request. My registered phone number is $phone."
         //   sendMessageViaWhatsApp("+919991329616", message, this)
            val c= (cost?:"0").toDouble()
            val amt: Long =(c*100).toLong()
            phone_pe_viewmodel.initiatePayment(amt,phone)

        }
        phone_pe_viewmodel.paymentState.observe(this){state->
            when(state){
                is PaymentState.Loading -> Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT)
                    .show()

                is PaymentState.Ready -> startActivityForResult(state.intent, B2B_PG_REQUEST_CODE)
                is PaymentState.Error -> {
                    Log.d("Error", state.message)
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }

                is PaymentState.Result -> {
                    if(state.success) {
                        viewModel.purchaseLiveClass(
                            phone,
                            cid.toString(),
                            cost.toString()
                        )
                            }
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()


                }
            }
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == B2B_PG_REQUEST_CODE) {
            phone_pe_viewmodel.handlePaymentResult(resultCode) // Delegate to ViewModel
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