package com.example.assignment_test

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.assignment_test.databinding.FragmentMineBinding


class MineFragment : Fragment() {

    private lateinit var binding : FragmentMineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMineBinding.inflate(layoutInflater)

        val buttonSettings= binding.buttonSettings
        val profilePicture=binding.profileImage

        buttonSettings.setOnClickListener{
            buttonClicked()
        }

        profilePicture.setOnClickListener(){
            val intent= Intent(activity,ProfileActivity::class.java)
            startActivity(intent)
        }

    }

    private fun buttonClicked(){
        Log.i("Button", "Button Clicked")

        val intent = Intent(activity,SettingsActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


}