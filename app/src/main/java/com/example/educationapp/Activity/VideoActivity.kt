package com.example.educationapp.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.educationapp.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource

class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoUrl = intent.getStringExtra("videourl")
        if (videoUrl.isNullOrEmpty()) {
            Log.e("VIDEO URL", "No video URL provided.")
            finish()
            return
        }
        Log.d("VIDEO URL", videoUrl)

        // Create a media item using the provided video URL.
        val mediaItem = MediaItem.fromUri(videoUrl)

        // Build an HLS media source.
        val hlsMediaSource = HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(mediaItem)

        // Initialize ExoPlayer.
        player = ExoPlayer.Builder(this).build().apply {
            setMediaSource(hlsMediaSource)
            prepare()
            playWhenReady = true
        }

        // Bind the player to the PlayerView.
        // Make sure your layout's video view is a PlayerView (or similar) with an id like playerView.
        binding.videoView.player = player
    }

    override fun onStop() {
        super.onStop()
        // Release the player when the activity is stopped.
        player.release()
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