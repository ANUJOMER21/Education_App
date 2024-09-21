package com.example.educationapp.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiService
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.BASE_URL
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.FragmentProfileBinding
import com.example.educationapp.databinding.FragmentReferEarnBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReferEarn.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReferEarn : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var binding: FragmentReferEarnBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var misc: Misc
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentReferEarnBinding.inflate(inflater, container, false)
        misc = Misc(requireContext())
        val repository = MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        val uid=misc.getid()
        var referal=""
        var phone=""
        var amount:String?=""
        viewModel.fetchProfile(uid.toString())
        viewModel.profileState.observe(viewLifecycleOwner) { profileResponse ->
            profileResponse?.let {
                binding.referal.text = "Referral Code: "+it.profile?.referralCode
                referal= it.profile?.referralCode.toString()
                phone=it.profile?.phone.toString()
                viewModel.fetchwallet(phone)
            }
            }
        binding.share.setOnClickListener {
              shareContent(referal, BASE_URL)
        }
        viewModel.walletState.observe(viewLifecycleOwner){
if(it!=null){
    if(it.success==true) {
        binding.wallet.text = "Rs." + it.amount.toString()
        amount= it.amount
    }
}


        }
        viewModel.withdrawState.observe(viewLifecycleOwner){
            if(it!=null){
                if(it.success==true){
                    binding.wallet.text="Rs. "+it.remainingAmount
                }
                else{
                    misc.toast(it.message!!.toString())
                }
            }
            else{
                misc.toast("Something went wrong")
            }
        }

    binding.withdraw.setOnClickListener {
        val amt=
            if(amount==null || amount!!.isEmpty())0.0 else amount.toString().toDouble()
        if(amt>=15){
           showdialog(phone)
        }
        else{
            misc.toast("Minimum amount to withdraw is 1500")
        }

    }



        // Inflate the layout for this fragment
        return binding.root
    }

    private fun showdialog(phone: String) {

    showWithdrawDialog(phone,requireContext())
    }
    fun showWithdrawDialog(phone: String,context: Context) {
        // Inflate the custom dialog layout
        val dialogView = LayoutInflater.from(context).inflate(R.layout.withdraw_dialog, null)

        val etWithdrawAmount = dialogView.findViewById<TextInputEditText>(R.id.etWithdrawAmount)
        val etUpiId = dialogView.findViewById<TextInputEditText>(R.id.etUpiId)

        // Create a Material Alert Dialog
        val dialog = MaterialAlertDialogBuilder(context)
            .setView(dialogView)
            .setCancelable(false) // Disable dismiss on outside touch
            .create()

        // Handle No button click (dismiss the dialog)
        dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnNo).setOnClickListener {
            dialog.dismiss() // Dismiss the dialog on 'No' click
        }

        // Handle Yes button click
        dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnYes).setOnClickListener {
            val amount = etWithdrawAmount.text.toString()
            val upiid=etUpiId.text.toString()

            // Check if amount is greater than 1500
            if (amount.isEmpty()) {
                Toast.makeText(context, "Please enter amount", Toast.LENGTH_SHORT).show()

            }
            else if(upiid.isEmpty()){
                Toast.makeText(context, "Please enter Upi ID", Toast.LENGTH_SHORT).show()
            }
            else if( amount.toDouble() < 1500){
                Toast.makeText(context, "Amount should be greater than 1500", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.withdraw(phone,amount,upiid)
            }
            dialog.dismiss()
        }

        // Show the dialog

        dialog.show()
    }

    fun message(referralCode: String, appUrl: String)="Check out this fabulous educational app! 📚 Use my referral code $referralCode to get up to 50% off. Download now: $appUrl"
    private fun shareContent(referralCode: String, appUrl: String) {
        val shareMessage = message(referralCode,appUrl)

        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(intent, "Share via"))
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReferEarn.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReferEarn().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}