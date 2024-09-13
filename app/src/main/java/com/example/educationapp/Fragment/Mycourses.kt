package com.example.educationapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Adapter.MyCourseAdapter
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.FragmentHomeBinding
import com.example.educationapp.databinding.FragmentMycoursesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Mycourses.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var binding: FragmentMycoursesBinding
private lateinit var viewModel: ApiViewModel
private lateinit var misc: Misc
private var phone: String? = null
class  Mycourses : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMycoursesBinding.inflate(inflater, container, false)

        // Initialize Misc and ViewModel
        misc = Misc(requireContext())
        val repository = MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)

        // Fetch user ID and profile
        val uid = misc.getid()
        fetchUserProfile(uid.toString())

        return binding.root
    }
    private fun fetchUserProfile(uid: String) {
        viewModel.fetchProfile(uid)
        viewModel.profileState.observe(viewLifecycleOwner) { profileResponse ->
            profileResponse?.let {
                // Fetch the phone number once the profile is retrieved
                phone = it.profile?.phone?.toString()
                if (!phone.isNullOrEmpty()) {
                    // Phone number is available, proceed to fetch courses
                    fetchMyCourses(phone!!)
                } else {
                    // Handle case when phone number is not available
                    Log.e("MyCoursesFragment", "Phone number is null or empty")
                }
            }
        }

    }
    private fun fetchMyCourses(phone: String) {
        val rv = binding.mycrv
        rv.layoutManager = LinearLayoutManager(requireContext())

        // Fetch My Courses
        viewModel.fetchMyCourse(phone)
        viewModel.myCourseState.observe(viewLifecycleOwner) { courseResponse ->
            courseResponse?.let {
                // Set adapter only when data is available
                val adapter = MyCourseAdapter(requireActivity(), it.data)
                rv.adapter = adapter
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Mycourses.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Mycourses().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}