package com.example.assignment_test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        videoView = findViewById(R.id.videoView)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.start_video)
        videoView.setVideoURI(videoUri)
        videoView.start()

        val signupButton : Button = findViewById(R.id.signUp_button)
        signupButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}