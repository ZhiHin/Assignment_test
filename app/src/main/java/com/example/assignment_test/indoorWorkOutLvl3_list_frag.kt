package com.example.assignment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class indoorWorkOutLvl3_list_frag : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_indoor_work_out_lvl3_list_frag)

        val indoorBtnStartLvl3: Button =  findViewById(R.id.indoorStartBtnLvl3)


        indoorBtnStartLvl3.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.indoorLvl3Frag, indoorWorkOutLvl3_frag())
                addToBackStack(null)
                commit()
            }
            System.out.println("FRAGMENT IS CLICKED")
        }
    }
}