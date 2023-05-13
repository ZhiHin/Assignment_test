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

        val childFragment1 = indoorWorkout_fragment()
        val transaction1 = childFragmentManager.beginTransaction()
        transaction1.add(R.id.frame_layout1, childFragment1)
        transaction1.commit()

        val childFragment2 = outdoorWorkout_fragment()
        val transaction2 = childFragmentManager.beginTransaction()
        transaction2.add(R.id.frame_layout2, childFragment2)
        transaction2.commit()

        return view
    }

}