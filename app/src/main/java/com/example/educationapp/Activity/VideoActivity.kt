package com.example.educationapp.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityVideoBinding
import com.potyvideo.library.globalEnums.EnumScreenMode

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var videoUrl=intent.getStringExtra("videourl")
        Log.d("VIDEO URL",videoUrl.toString())
       // videoUrl="https://api.dyntube.com/v1/apps/hls/PD2RDNwkgkee0yWGORdbLQ.m3u8"
        binding.videoView.setSource(videoUrl!!)
        binding.videoView.setScreenMode(EnumScreenMode.FULLSCREEN)


    }
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel() // Cancel the previous toast if it's still visible
            super.onBackPressed() // Exit the app
            return
        } else {
            toast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT)
            toast.show() // Show the confirmation message
        }
        backPressedTime = System.currentTimeMillis() // Update the last back pressed time
    }
}