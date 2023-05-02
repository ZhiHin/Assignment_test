package com.example.assignment_test

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton


class indoorWorkout_fragment : Fragment(R.layout.fragment_indoor_workout_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_indoor_workout_fragment, container, false)

        val indoorLvl1: ImageButton = view.findViewById(R.id.imageButton1)
        val indoorLvl2: ImageButton = view.findViewById(R.id.imageButton2)
        val indoorLvl3: ImageButton = view.findViewById(R.id.imageButton3)
        // Set onClickListener for the button here



        indoorLvl1.setOnClickListener{
            println("IMAGE ONE IS CLICKED")

        }

        indoorLvl2.setOnClickListener{
            println("IMAGE TWO IS CLICKED")

        }

        indoorLvl3.setOnClickListener{
            println("IMAGE THREE IS CLICKED")

        }
        return view
    }

    }



