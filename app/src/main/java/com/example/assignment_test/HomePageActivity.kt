package com.example.assignment_test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.abs

class HomePageActivity : AppCompatActivity() {

    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: ImageSlider

    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_page)
        init()
        setUpTransformer()

        val usernameTextView = findViewById<TextView>(R.id.username)
        val username = getUsername()
        System.out.println("this is my username")
        System.out.println(username)
        usernameTextView.text = username


        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.home

        setUpBottomNavigationView()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 4000)
            }
        })
    }


    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.slider1)
        imageList.add(R.drawable.slider2)
        imageList.add(R.drawable.slider3)
        imageList.add(R.drawable.slider4)



        adapter = ImageSlider(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }
    private fun setUpBottomNavigationView() {

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.workout -> {
                    // navigate to home activity
                    val intent = Intent(this, WorkOutPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.report -> {
                    // navigate to profile activity
                    val intent = Intent(this, WorkOutPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.home -> {
                    // navigate to settings activity
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                    true


                }
                else -> false
            }
        }


    }


    private fun getUsername(): String? {
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("username", null)
    }



}