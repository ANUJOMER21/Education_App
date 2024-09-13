package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
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
import com.example.educationapp.Fragment.Mycourses
import com.example.educationapp.Fragment.ReferEarn
import com.example.educationapp.Fragment.profile
import com.example.educationapp.Misc
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityHomePageBinding
import com.example.educationapp.databinding.HeaderBinding
import com.google.android.material.navigation.NavigationView

class HomePage : AppCompatActivity() {
    private fun displaySelectedScreen(itemId: Int) {
        val fragment = when (itemId) {
            R.id.nav_home -> HomeFragment()
            R.id.nav_profile -> profile()
            R.id.nav_mycourse -> Mycourses()

            R.id.nav_live-> LiveFragment()
            R.id.nav_refer->ReferEarn()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerLayout=binding.drawerLayout
        navigationView=binding.navView
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
    }
}