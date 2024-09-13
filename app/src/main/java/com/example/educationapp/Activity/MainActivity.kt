package com.example.educationapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.educationapp.PreferenceHelper
import com.example.educationapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
         CoroutineScope(Dispatchers.Main).launch {
             delay(5000)
             val preferenceHelper= PreferenceHelper(this@MainActivity);
             if(preferenceHelper.getUserId()!=-1){
                 val intent = Intent(this@MainActivity, HomePage::class.java)
                 intent.putExtra("userid",preferenceHelper.getUserId())
                 startActivity(intent)
                 finish()
             }
             else {
                 val intent = Intent(this@MainActivity, LoginActivity::class.java)
                 startActivity(intent)
                 finish()
             }
         }

         }
    }
