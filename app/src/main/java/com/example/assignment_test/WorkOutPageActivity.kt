package com.example.assignment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WorkOutPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_page)

        val indoorworkoutFragment = indoorWorkout_fragment()
        val outdoorworkoutFragment = outdoorWorkout_fragment()
        val indoorBtn: Button =  findViewById(R.id.indoor)
        val outdoorBtn: Button =  findViewById(R.id.outdoor)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.workout, indoorworkoutFragment)
            commit()
        }

        indoorBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.workout, indoorworkoutFragment)
                addToBackStack(null)
                commit()
            }
        }

        outdoorBtn.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.workout, outdoorworkoutFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}