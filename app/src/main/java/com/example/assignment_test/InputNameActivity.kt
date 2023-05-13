package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class InputNameActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputname)

        // Get the progress value from gender selection activity
        var progressValue = intent.getIntExtra("progressValue", 0)

        // Set the progress bar to the progress value
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = progressValue

        //back button
        val backButton =  findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this,GenderSelectionActivity::class.java)
            startActivity(intent)
        }

        //next button
        val nextButton = findViewById<Button>(R.id.email_pwd_nextButton)
        nextButton.setOnClickListener {
            Log.i("Clicked", "Button is Clicked")
            progressValue += 20
            //set the progress value to the progress bar
            progressBar.progress = progressValue

            val intent = Intent(this, InputAgeActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)

        }
    }
}