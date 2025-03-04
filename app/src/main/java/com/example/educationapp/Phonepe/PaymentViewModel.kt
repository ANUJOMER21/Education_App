package com.example.educationapp.Phonepe


import android.app.Activity.RESULT_OK
import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class PaymentViewModel(private val paymentRepository: PaymentRepository) : ViewModel() {

    private val _paymentState = MutableLiveData<PaymentState>()
    val paymentState: LiveData<PaymentState> get() = _paymentState

    fun initiatePayment(amount: Long,phone:String) {
        _paymentState.value = PaymentState.Loading
        try {
            val b2bPgRequest = paymentRepository.preparePaymentRequest(amount,phone)
            Log.d("PaymentViewModel", "Payment request: $b2bPgRequest")
            val intent = paymentRepository.getPaymentIntent(b2bPgRequest)
            Log.d("PaymentViewModel", "Payment intent: $intent")
            _paymentState.value = PaymentState.Ready(intent!!)
        } catch (e: Exception) {
            _paymentState.value = PaymentState.Error("Failed to initiate payment: ${e.message}")
        }
    }

    // New method to handle payment result
    fun handlePaymentResult(resultCode: Int) {
        when (resultCode) {
            RESULT_OK -> _paymentState.value = PaymentState.Result(true, "Payment Completed")
            RESULT_CANCELED -> _paymentState.value = PaymentState.Result(false, "Payment Cancelled")
            else -> _paymentState.value = PaymentState.Result(false, "Payment Failed")
        }
    }
}

sealed class PaymentState {
    object Loading : PaymentState()
    data class Ready(val intent: Intent) : PaymentState()
    data class Error(val message: String) : PaymentState()
    data class Result(val success: Boolean, val message: String) : PaymentState()
}