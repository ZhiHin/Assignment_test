package com.example.assignment_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton


class outdoorWorkout_fragment : Fragment(R.layout.fragment_outdoor_workout_fragment) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_outdoor_workout_fragment, container, false)

        val outdoorLvl2: ImageButton = view.findViewById(R.id.outdoor_lvl2)

        // Set onClickListener for the button here





        outdoorLvl2.setOnClickListener{
            println("OUTDOOR IS CLICKED")

        }
        return view
    }


}