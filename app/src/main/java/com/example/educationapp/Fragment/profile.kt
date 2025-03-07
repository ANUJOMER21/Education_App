package com.example.educationapp.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Activity.ProfileSettingPage
import com.example.educationapp.Activity.Support_page
import com.example.educationapp.Activity.changepassword
import com.example.educationapp.Misc
import com.example.educationapp.databinding.FragmentProfileBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class profile : Fragment() {
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
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ApiViewModel
    private lateinit var misc: Misc
    private var phone: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        misc = Misc(requireContext())
        val repository = MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        binding.changepass.setOnClickListener {
            val intent= Intent(requireContext(),changepassword::class.java)
            startActivity(intent)
        }
        binding.profilesett.setOnClickListener {
            val intent= Intent(requireContext(), ProfileSettingPage::class.java)
            startActivity(intent)
        }
        val uid=misc.getid()
        viewModel.fetchProfile(uid.toString())
        viewModel.profileState.observe(viewLifecycleOwner) { profileResponse ->
            profileResponse?.let {
                binding.profileName.text = it.profile?.username
                binding.profileEmail.text = it.profile?.email
            }
        }

        binding.tc.setOnClickListener {
            val intent= Intent(requireContext(),Support_page::class.java)
            intent.putExtra("page_type","tc")
            startActivity(intent)
        }
        binding.policy.setOnClickListener {
            val intent= Intent(requireContext(),Support_page::class.java)
            intent.putExtra("page_type","policy")
            startActivity(intent)
        }
        binding.about.setOnClickListener {
            val intent= Intent(requireContext(),Support_page::class.java)
            intent.putExtra("page_type","about")
            startActivity(intent)
        }





        // Inflat
        // e the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}