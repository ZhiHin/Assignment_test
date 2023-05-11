package com.example.assignment_test

import android.content.Intent
import android.icu.math.BigDecimal
import android.icu.math.MathContext
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode


class WeightSelectionActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weightselection)

        val weightDisplay = findViewById<TextView>(R.id.weightDisplay)

        // Get the progress value from gender inputName activity
        var progressValue = intent.getIntExtra("progressValue", 0)

        // Set the progress bar to the progress value
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = progressValue

        val weight = findViewById<NumberPicker>(R.id.weight_number_picker)

        weight.wrapSelectorWheel = false
        weight.minValue = 20
        weight.maxValue = 250
        weight.value = 50
        weight.displayedValues = (20..250).map { "%.1f".format(it / 1f, it / 1f + 0.1 , it / 1f + 0.2).toString() }.toTypedArray()



        //display value
        weightDisplay.text = "${weight.value}" + "kg"
        //display changed value
        weight.setOnValueChangedListener { picker, oldVal, newVal ->
            weightDisplay.text = "$newVal" + "kg"
        }

        // next button
        val nextButton = findViewById<Button>(R.id.weight_nextButton)
        nextButton.setOnClickListener {
            Log.i("button Clicked", "is Clicked")
            progressValue += 10
            //set the progress value to the progress bar
            progressBar.progress = progressValue
            val intent = Intent(this,InputEmail_PwdActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)
        }

        //back button
        val backButton =  findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            progressValue -= 10
            //set the progress value to the progress bar
            progressBar.progress = progressValue
            val intent = Intent(this,HeightSelectionActivity::class.java)
            intent.putExtra("progressValue", progressValue)
            startActivity(intent)
        }
    }
}