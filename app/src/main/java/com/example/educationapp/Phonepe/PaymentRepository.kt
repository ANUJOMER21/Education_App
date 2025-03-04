package com.example.educationapp.Phonepe



import android.content.Context
import android.util.Log
import com.phonepe.intent.sdk.api.B2BPGRequest
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder
import com.phonepe.intent.sdk.api.PhonePe
import com.phonepe.intent.sdk.api.PhonePeInitException
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment


class PaymentRepository(
    private val context: Context,
    private val paymentUtils: PaymentUtils
) {

    private val merchantId = "M225LHCXQQX45"


    init {
        try {
            PhonePe.init(context, PhonePeEnvironment.STAGE_SIMULATOR,merchantId,"")

            PhonePe.setFlowId("user_flow_${System.currentTimeMillis()}")
            val upiApps = PhonePe.getUpiApps()
            Log.d("PaymentViewModel",upiApps.toString())
        } catch (e: PhonePeInitException) {
            e.printStackTrace()
        }
    }

    fun preparePaymentRequest(amount: Long,phone:String): B2BPGRequest {
        Log.d("PaymentViewModel",amount.toString())
        val paymentRequest = PaymentRequest(
            merchantId = merchantId,
            merchantTransactionId = "TXN${System.currentTimeMillis()}",
            merchantUserId = "MUID123",
            amount = amount,
            mobileNumber = phone,
            callbackUrl = "https://api.phonepe.com/apis/hermes/pg/v1/pay",
            paymentInstrument = mapOf("type" to "PAY_PAGE"),
            deviceContext = mapOf("deviceOS" to "ANDROID")
        )
        Log.d("PaymentViewModel",paymentRequest.merchantTransactionId)

        val base64Body = paymentUtils.createPaymentRequest(paymentRequest)
        val checksum = paymentUtils.generateChecksum(base64Body)
        Log.d("PaymentViewModel_sum",checksum)

        return B2BPGRequestBuilder()
            .setData(base64Body)
            .setChecksum(checksum)
            .setUrl("/pg/v1/pay")
            .build()
    }

    fun getPaymentIntent(b2bPgRequest: B2BPGRequest) = PhonePe.getImplicitIntent(context, b2bPgRequest, "com.phonepe.app")
}