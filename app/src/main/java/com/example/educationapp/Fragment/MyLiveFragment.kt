package com.example.educationapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.CourseLiveClassAdapter
import com.example.educationapp.Adapter.LiveClassAdapter
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import com.example.educationapp.databinding.FragmentMyLiveBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyLiveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyLiveFragment : Fragment() {

    private var _binding: FragmentMyLiveBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ApiViewModel
    private lateinit var misc: Misc
    private var hasFetchedLiveClass = false  // Flag to prevent multiple calls

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyLiveBinding.inflate(inflater, container, false)
        misc = Misc(requireContext())

        setupViewModel()
        setupRecyclerView()
        observeLiveData()

        return binding.root
    }

    private fun setupViewModel() {
        val repository = MainRepository()
        val factory = AuthViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ApiViewModel::class.java)

        val uid = misc.getid()
        val phone=PreferenceHelper(requireContext()).getPhoneNumber()
        Log.d("phone",phone.toString())
        if (phone != null) {
            viewModel.myLive_class(phone)
        }
    }

    private fun setupRecyclerView() {
        binding.mycrv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeLiveData() {


        viewModel.liveClassDetailsState.observe(viewLifecycleOwner) { liveClassDetailsState ->
            liveClassDetailsState?.takeIf { it.success == true }?.data?.let { liveClassDetails ->
                Log.d("liveClassDetails", liveClassDetails.toString())
                binding.mycrv.adapter = CourseLiveClassAdapter(requireContext(), liveClassDetails).apply {
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Prevent memory leaks
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            MyLiveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

