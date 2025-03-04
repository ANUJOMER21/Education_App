package com.example.educationapp.Phonepe



import android.util.Base64

import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class PaymentUtils {

    private val saltKey = "fb57eca1-6de4-431b-80fe-8c11c6fed677"
    private val saltIndex = 1

    fun createPaymentRequest(paymentRequest: PaymentRequest): String {
        val jsonData = JSONObject().apply {
            put("merchantId", paymentRequest.merchantId)
            put("merchantTransactionId", paymentRequest.merchantTransactionId)
            put("merchantUserId", paymentRequest.merchantUserId)
            put("amount", paymentRequest.amount)
            put("mobileNumber", paymentRequest.mobileNumber)
            put("callbackUrl", paymentRequest.callbackUrl)
            put("paymentInstrument", JSONObject(paymentRequest.paymentInstrument))
           // put("deviceContext", JSONObject(paymentRequest.deviceContext))
        }.toString()
        return Base64.encodeToString(jsonData.toByteArray(StandardCharsets.UTF_8), Base64.NO_WRAP)
    }

    fun generateChecksum(base64Body: String, apiEndpoint: String = "/pg/v1/pay"): String {
        val data = base64Body + apiEndpoint + saltKey
        val sha256 = MessageDigest.getInstance("SHA-256")
        val hash = sha256.digest(data.toByteArray(StandardCharsets.UTF_8))
        val checksum = hash.joinToString("") { "%02x".format(it) }
        return "$checksum###$saltIndex"
    }
}