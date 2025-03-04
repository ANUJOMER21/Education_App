package com.example.educationapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Activity.AllCourse
import com.example.educationapp.Activity.SeeAllCategories
import com.example.educationapp.Adapter.CategoryAdapter
import com.example.educationapp.Adapter.CourseAdapter
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ApiViewModel
    private lateinit var Misc: Misc
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        Misc= Misc(requireContext())
        val repository= MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        setSlider()
       /* viewModel.profileState.observe(viewLifecycleOwner){
            if(it!=null){
                binding.name.text=it.profile!!.username
            }

        }*/
        binding.seallcat.setOnClickListener{
            val intent=Intent(requireContext(),SeeAllCategories::class.java)
         startActivity(intent)
        }
        binding.seallFeature.setOnClickListener {
            val intent=Intent(requireContext(),AllCourse::class.java)
            intent.putExtra("cat","All Courses")
            intent.putExtra("id","0")
            startActivity(intent)
        }
        binding.seall.setOnClickListener {
            val intent=Intent(requireContext(),AllCourse::class.java)
            intent.putExtra("cat","All Courses")
            intent.putExtra("id","0")
            startActivity(intent)
        }
        setFeatureCourse()
        setFeatureCourse2()
        setCategory()
        val id=PreferenceHelper(requireContext()).getUserId()
        viewModel.fetchProfile(id.toString())



        return binding.root
    }

    private fun setFeatureCourse2() {
        val courseRecyclerView=binding.featureCourseRv2
        courseRecyclerView.layoutManager= LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.getAllCategory()
        viewModel.featureCourseState.observe(viewLifecycleOwner){
            val adapter=CourseAdapter(requireActivity(),it,true)
            courseRecyclerView.adapter=adapter
        }

    }

    private fun setCategory(){
        val categoryRecyclerView=binding.categoryRv
        categoryRecyclerView.layoutManager= LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
       viewModel.getAllCategory()
        viewModel.allCategoryState.observe(viewLifecycleOwner){
            val adapter= CategoryAdapter(requireActivity(),it)
            categoryRecyclerView.adapter=adapter
        }
    }
    private fun setFeatureCourse() {
        val courseRecyclerView=binding.featureCourseRv
        courseRecyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.getFeatureCourse()
        viewModel.featureCourseState.observe(viewLifecycleOwner){
            val adapter=CourseAdapter(requireActivity(),it,true)
            courseRecyclerView.adapter=adapter
        }
    }

    private fun setSlider(){
       viewModel.getSlider()
        val autoImageSlider=binding.autoImageSlider

        viewModel.sliderState.observe(viewLifecycleOwner){
            Log.d("autoImageslideder",it.get(0).toString())
            autoImageSlider.setImageList(it, ImageScaleType.FIT)
        }
        autoImageSlider.setDefaultAnimation()


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}