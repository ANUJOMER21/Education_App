package com.example.educationapp.Phonepe

import com.google.gson.annotations.SerializedName


data class PaymentRequest(
    @SerializedName("merchantId") val merchantId: String,
    @SerializedName("merchantTransactionId")  val merchantTransactionId: String,
    @SerializedName("merchantUserId")  val merchantUserId: String,
    @SerializedName("amount")val amount: Long,
    @SerializedName("mobileNumber")val mobileNumber: String,
    @SerializedName("callbackUrl") val callbackUrl: String,
    @SerializedName("paymentInstrument")val paymentInstrument: Map<String, String>,
   // @SerializedName("merchantId")val deviceContext: Map<String, String>
)