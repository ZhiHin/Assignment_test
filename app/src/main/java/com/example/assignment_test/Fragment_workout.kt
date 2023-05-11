package com.example.assignment_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil.setContentView

class Fragment_workout : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        val indoorworkoutFragment = indoorWorkout_fragment()
        val outdoorworkoutFragment = outdoorWorkout_fragment()
        val indoorBtn: Button = view.findViewById(R.id.indoor)
        val outdoorBtn: Button = view.findViewById(R.id.outdoor)

        childFragmentManager.beginTransaction().apply {
            replace(R.id.workout, indoorworkoutFragment)
            commit()
        }

        indoorBtn.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                replace(R.id.workout, indoorworkoutFragment)
                addToBackStack(indoorworkoutFragment.tag)
                commit()
            }
        }

        outdoorBtn.setOnClickListener {
            childFragmentManager.beginTransaction().apply {
                replace(R.id.workout, outdoorworkoutFragment)
                addToBackStack(outdoorworkoutFragment.tag)
                commit()
            }
        }

        return view
    }
}