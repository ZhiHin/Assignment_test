package com.example.assignment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class indoorWorkOutLvl1_list_frag : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment", "indoorWorkOutLvl1_frag created")
        setContentView(R.layout.activity_indoor_work_out_lvl1_list_frag)

        val indoorBtnStartLvl1: Button =  findViewById(R.id.indoorStartBtnLvl1)


        indoorBtnStartLvl1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.indoorLvl1Frag, indoorWorkOutLvl1_frag())
                addToBackStack("indoorWorkOutLvl1_list_frag")
                commit()
            }
            System.out.println("FRAGMENT IS CLICKED")
        }
    }
}