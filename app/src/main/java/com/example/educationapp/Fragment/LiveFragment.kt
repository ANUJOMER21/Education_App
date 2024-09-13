package com.example.educationapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.LiveClassAdapter
import com.example.educationapp.Misc
import com.example.educationapp.databinding.FragmentLiveBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LiveFragment : Fragment() {
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
lateinit var binding :FragmentLiveBinding
lateinit var viewmodel:ApiViewModel
lateinit var misc: Misc
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLiveBinding.inflate(inflater,container,false)
        misc= Misc(requireContext())
        val repository= MainRepository()
        viewmodel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        val uid=misc.getid()
        binding.mycrv.layoutManager=LinearLayoutManager(requireContext())
        var phone=""

        viewmodel.fetchProfile(uid.toString())
        viewmodel.profileState.observe(viewLifecycleOwner) { profileState ->
            profileState?.profile?.phone?.let { phoneNumber ->
                phone = phoneNumber
                viewmodel.fetchLiveClassDetails(phone)
                viewmodel.liveClassDetailsState.observe(viewLifecycleOwner) { liveClassDetails ->
                    if (liveClassDetails != null) {
                        if (liveClassDetails.success == true) {
                            val liveClassDetailsList = liveClassDetails.data
                            val adapter = LiveClassAdapter(requireContext(), liveClassDetailsList)
                            binding.mycrv.adapter = adapter
                        }


                    }
                }

            }
        }









        return binding.root
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LiveFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LiveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}