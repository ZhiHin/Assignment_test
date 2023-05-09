package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HeightSelectionActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.heightselection)

        val heightDisplay = findViewById<TextView>(R.id.weightDisplay)

        // Get the progress value from gender inputName activity
        var progressValue = intent.getIntExtra("progressValue", 0)

        // Set the progress bar to the progress value
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = progressValue

        val height = findViewById<NumberPicker>(R.id.weight_number_picker)
        height.wrapSelectorWheel = false
        height.minValue = 140
        height.maxValue = 220
        height.value = 160
        //display value
        heightDisplay.text = "${height.value}" + "cm"
        //display changed value
        height.setOnValueChangedListener { picker, oldVal, newVal ->
            heightDisplay.text = "$newVal" + "cm"
        }

        // next button
        val nextButton = findViewById<Button>(R.id.weight_nextButton)
        nextButton.setOnClickListener {
            Log.i("button Clicked", "is Clicked")
            progressValue += 10
            //set the progress value to the progress bar
            progressBar.progress = progressValue

            val intent = Intent(this, WeightSelectionActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)
        }

        //back button
        val backButton =  findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            progressValue -= 20
            //set the progress value to the progress bar
            progressBar.progress = progressValue
            val intent = Intent(this,InputAgeActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)
        }
    }
}