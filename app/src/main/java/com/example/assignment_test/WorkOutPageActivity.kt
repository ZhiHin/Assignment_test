package com.example.assignment_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class WorkOutPageActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_page)

        val indoorworkoutFragment = indoorWorkout_fragment()
        val outdoorworkoutFragment = outdoorWorkout_fragment()
        val indoorBtn: Button =  findViewById(R.id.indoor)
        val outdoorBtn: Button =  findViewById(R.id.outdoor)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.workout
        setUpBottomNavigationView()

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

    private fun setUpBottomNavigationView() {

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.workout -> {
                    // navigate to home activity
                    val intent = Intent(this, WorkOutPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.report -> {
                    // navigate to profile activity
                    val intent = Intent(this, WorkOutPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.home -> {
                    // navigate to settings activity
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    true


                }
                else -> false
            }
        }


    }
}