package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Fragment.HomeFragment
import com.example.educationapp.Fragment.LiveFragment
import com.example.educationapp.Fragment.MyLiveFragment
import com.example.educationapp.Fragment.Mycourses
import com.example.educationapp.Fragment.ReferEarn
import com.example.educationapp.Fragment.profile
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityHomePageBinding
import com.example.educationapp.databinding.HeaderBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView

class HomePage : AppCompatActivity() {
    private fun getusername(userid:Int,name:(String)->Unit){
        val repository= MainRepository()
        val  viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
         viewModel.fetchProfile(userid.toString())
        viewModel.profileState.observe(this) {
            if (it != null) {
                it.profile!!.username?.let { it1 -> name(it1) }
            }
        }
    }
    private fun displaySelectedScreen(itemId: Int) {
        val fragment = when (itemId) {
            R.id.nav_home -> {
                namell.visibility=LinearLayout.VISIBLE
                fragname.visibility=LinearLayout.GONE
                getusername(PreferenceHelper(this).getUserId()?:0){
                    studentname.text=it

                }

                search_cv.visibility=View.VISIBLE
                HomeFragment()
            }
            R.id.nav_profile -> {
                namell.visibility=LinearLayout.GONE
                fragname.visibility=LinearLayout.VISIBLE
                pagename.text="Profile"
                search_cv.visibility=View.GONE
                profile()
            }
            R.id.nav_mycourse ->{
                namell.visibility=LinearLayout.GONE
                fragname.visibility=LinearLayout.VISIBLE
                pagename.text="My Courses"
                search_cv.visibility=View.GONE
                Mycourses()
            }

            R.id.nav_live-> {
                namell.visibility=LinearLayout.GONE
                fragname.visibility=LinearLayout.VISIBLE
                pagename.text="Live Class"
                search_cv.visibility=View.GONE


                LiveFragment()
            }
            R.id.nav_my_live_class->{
                namell.visibility=LinearLayout.GONE
                fragname.visibility=LinearLayout.VISIBLE
                pagename.text="My Live Class"
                search_cv.visibility=View.GONE
                MyLiveFragment()
            }
            R.id.nav_refer->{
                namell.visibility=LinearLayout.GONE
                fragname.visibility=LinearLayout.VISIBLE
                pagename.text="Refer & Earn"
                search_cv.visibility=View.GONE
                ReferEarn()
            }
            R.id.nav_logout-> {
                misc.showLogoutDialog {
                    finish()
                }

                null
            }
            else -> null
        }

        fragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.content_frame, it).commit()
        }
    }

    lateinit var   binding:ActivityHomePageBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var misc:Misc
    private lateinit var fragname:LinearLayout
    private lateinit var namell:LinearLayout
    private lateinit var studentname:TextView
    private lateinit var pagename:TextView
    private lateinit var search_cv: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout=binding.drawerLayout
        navigationView=binding.navView
        fragname=binding.fragname
        namell=binding.namell
        search_cv=binding.searchCard
        studentname=binding.studentname
        pagename=binding.pagename
        namell.visibility=LinearLayout.VISIBLE
        fragname.visibility=LinearLayout.GONE
        getusername(PreferenceHelper(this).getUserId()?:0){
            studentname.text=it

        }

        search_cv.visibility=View.VISIBLE
        val headerView = binding.navView.getHeaderView(0)
        val headerBinding = HeaderBinding.bind(headerView)
        misc=Misc(this)
        val repository= MainRepository()
        val  viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        viewModel.profileState.observe(this) {
            if (it != null) {
                headerBinding.name.text=it.profile!!.username
            }
        }
        val id= PreferenceHelper(this).getUserId()
        viewModel.fetchProfile(id.toString())
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, HomeFragment()).commit()
        // Handle NavigationView item clicks
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            displaySelectedScreen(menuItem.itemId)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        binding.menu.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }


       binding.notify.setOnClickListener {
          supportFragmentManager.beginTransaction().replace(R.id.content_frame,profile()).commit()
                 }
        binding.searchCard.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}