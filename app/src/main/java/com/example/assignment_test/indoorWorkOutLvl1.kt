package com.example.assignment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class indoorWorkOutLvl1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indoor_work_out_lvl1)

        val indoorBtnStartLvl1: Button =  findViewById(R.id.indoorStartBtnLvl1)


        indoorBtnStartLvl1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.indoorLvl1Frag, indoorWorkOutLvl1_frag())
                addToBackStack(null)
                commit()
            }
            System.out.println("FRAGMENT IS CLICKED")
        }
    }
}