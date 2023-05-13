package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            val intent = Intent(requireActivity(), indoorWorkOutLvl1_list_frag::class.java)
            startActivity(intent)
        }

        indoorLvl2.setOnClickListener{
            val intent = Intent(requireActivity(), indoorWorkOutLvl2_list_frag::class.java)
            startActivity(intent)

        }

        indoorLvl3.setOnClickListener{
            val intent = Intent(requireActivity(), indoorWorkOutLvl3_list_frag::class.java)
            startActivity(intent)

        }
        return view
    }

}



