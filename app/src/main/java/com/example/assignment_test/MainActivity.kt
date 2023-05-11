package com.example.assignment_test

import MineFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.assignment_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.selectedItemId=R.id.home
        replaceFragment(Fragment_home_page())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workout->replaceFragment(Fragment_workout())
                R.id.settings->replaceFragment(MineFragment())
                R.id.home->replaceFragment(Fragment_home_page())
                else->{

                }
            }
            true
        }


    }

    private fun replaceFragment(fragment : Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}