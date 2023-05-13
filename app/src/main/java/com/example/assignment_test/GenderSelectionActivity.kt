package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class GenderSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genderselection)


        //set back button onclick
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        //set initial progress value
        var currentProgress = 0
        progressBar.progress = currentProgress


        //create val for imageView
        val male = findViewById<ImageView>(R.id.maleImage)
        val female = findViewById<ImageView>(R.id.femaleImage)

        //set male image onclick
        male.setOnClickListener {
            currentProgress += 20
            //set the progress value to the progress bar
            progressBar.progress = currentProgress
            val intent = Intent(this,InputNameActivity::class.java)
            intent.putExtra("progressValue", currentProgress)
            startActivity(intent)


        }
        //set female image onclick
        female.setOnClickListener {
            currentProgress += 20
            //set the progress value to the progress bar
            progressBar.progress = currentProgress
            val intent = Intent(this,InputNameActivity::class.java)
            intent.putExtra("progressValue", currentProgress)
            startActivity(intent)

        }

    }
}