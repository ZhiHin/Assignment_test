package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment

class SignUpSub1_fragment : Fragment(R.layout.signup_sub1_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.signup_sub1_fragment, container, false)
        //val intent = Intent(activity,LoginActivity::class.java)
        //startActivity(intent)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            parentFragmentManager.beginTransaction().remove(this).commit()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)

        }, 3000)

        return view
    }
}

