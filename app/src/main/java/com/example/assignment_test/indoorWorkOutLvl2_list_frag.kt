package com.example.assignment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class indoorWorkOutLvl2_list_frag : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_indoor_work_out_lvl2_list_frag)

        val indoorBtnStartLvl2: Button =  findViewById(R.id.indoorStartBtnLvl2)


        indoorBtnStartLvl2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.indoorLvl2Frag, indoorWorkOutLvl2_frag())
                addToBackStack(null)
                commit()
            }
            System.out.println("FRAGMENT IS CLICKED")
        }
    }
}