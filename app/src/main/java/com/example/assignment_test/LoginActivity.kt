package com.example.assignment_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton: Button =  findViewById(R.id.login_button)
        loginButton.setOnClickListener {
//            val intent = Intent(this, HomePageActivity::class.java)
//            startActivity(intent)

            val intent = Intent(this, PlantingTreeActivity::class.java)
            startActivity(intent)
        }
    }
}