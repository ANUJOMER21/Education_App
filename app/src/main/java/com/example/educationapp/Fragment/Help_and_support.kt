package com.example.educationapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.databinding.FragmentHelpAndSupportBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Help_and_support.newInstance] factory method to
 * create an instance of this fragment.
 */
lateinit var viewmodel: ApiViewModel
class Help_and_support : Fragment() {
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
    lateinit var misc: Misc
    lateinit var binding: FragmentHelpAndSupportBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelpAndSupportBinding.inflate(inflater, container, false)
        misc = Misc(requireContext())
        val repository = MainRepository()
        viewmodel =
            ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        val uid = misc.getid()
        viewmodel.helpRequestState.observe(viewLifecycleOwner){
            if(it!=null){
                if(it.success==true){
                    binding.etQuery.setText("")
                    Toast.makeText(requireActivity(),"We will contact you soon, Thank you",Toast.LENGTH_SHORT).show()
                }

            }

        }
        binding.btnSend.setOnClickListener {

            val helpet=binding.etQuery.text.toString()
            if(helpet.isNotEmpty()){
                Log.d("APICALL",helpet)
                viewmodel.helpandsupport(uid.toString(),helpet)
            }
            else{
                Toast.makeText(requireActivity(),"Enter Your Query",Toast.LENGTH_SHORT).show()
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Help_and_support.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Help_and_support().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}