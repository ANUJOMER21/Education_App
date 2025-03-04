package com.example.educationapp.Phonepe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.databinding.ActivityTestPhonepeBinding

class test_phonepe : AppCompatActivity() {
    private lateinit var binding: ActivityTestPhonepeBinding
    private lateinit var viewModel: PaymentViewModel
    private val B2B_PG_REQUEST_CODE = 777

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestPhonepeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Manually instantiate dependencies
        val paymentUtils = PaymentUtils()
        val paymentRepository = PaymentRepository(this, paymentUtils)
        viewModel = ViewModelProvider(this, PaymentViewModelFactory(paymentRepository)).get(PaymentViewModel::class.java)

        // Set up button click
        binding.payButton.setOnClickListener {
            viewModel.initiatePayment(10000,"1234543234") // ₹100 in paise
        }
        // Observe payment state
        viewModel.paymentState.observe(this) { state ->
            when (state) {
                is PaymentState.Loading -> Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT)
                    .show()

                is PaymentState.Ready -> startActivityForResult(

                    state.intent, B2B_PG_REQUEST_CODE)
                is PaymentState.Error -> {
                    Log.d("Error", state.message)
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }

                is PaymentState.Result -> {

                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()


            }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == B2B_PG_REQUEST_CODE) {
            viewModel.handlePaymentResult(resultCode) // Delegate to ViewModel
        }
    }
}
