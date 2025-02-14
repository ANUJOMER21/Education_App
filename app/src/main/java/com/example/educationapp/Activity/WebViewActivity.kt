package com.example.educationapp.Activity

import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.educationapp.APi.ApiViewModel
import com.example.educationapp.APi.AuthViewModelFactory
import com.example.educationapp.APi.MainRepository
import com.example.educationapp.Misc
import com.example.educationapp.R
import com.example.educationapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebViewBinding
    lateinit var viewModel: ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWebViewBinding.inflate(layoutInflater)
        val repository = MainRepository()
        viewModel = ViewModelProvider(this, AuthViewModelFactory(repository)).get(ApiViewModel::class.java)
        setContentView(binding.root)
        val uid=Misc(this).getid()
        viewModel.fetchProfile(uid.toString())
        viewModel.profileState.observe(this){
            val number= it?.profile?.phone
            var videoUrl=intent.getStringExtra("videourl")
            videoUrl=videoUrl+"&phone=$number"
            videoUrl="https://api.dyntube.com/v1/live/videos/m0bYurR3iEbLxgunTAy5w.m3u8"
            Log.d("video_url",videoUrl)
            val webView=binding.webview
            val webSettings: WebSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://api.dyntube.com/v1/live/videos/m0bYurR3iEbLxgunTAy5w.m3u8")

           // webView.loadUrl(videoUrl)
        }






        }





}