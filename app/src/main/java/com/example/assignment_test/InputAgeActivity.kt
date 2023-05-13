package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class InputAgeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inputage)

        // Get the progress value from inputName activity
        var progressValue = intent.getIntExtra("progressValue", 0)

        // Set the progress bar to the progress value
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = progressValue

        //back button
        val backButton =  findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            progressValue -= 20
            //set the progress value to the progress bar
            progressBar.progress = progressValue
            val intent = Intent(this,InputNameActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)
        }

       //next button
        val nextButton = findViewById<Button>(R.id.email_pwd_nextButton)
        nextButton.setOnClickListener {
            Log.i("Clicked", "Button is Clicked")
            progressValue += 20
            //set the progress value to the progress bar
            progressBar.progress = progressValue

            val intent = Intent(this, HeightSelectionActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)

        }
    }
}