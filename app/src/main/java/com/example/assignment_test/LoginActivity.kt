package com.example.assignment_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        database = FirebaseDatabase.getInstance().getReference("Users")

        val loginButton: Button =  findViewById(R.id.login_button)



        loginButton.setOnClickListener {
            //SignUp function
//            val username: TextView = findViewById(R.id.Login_username_input)
//            val pw: TextView = findViewById(R.id.Login_password_Input)
//
//            val database = FirebaseDatabase.getInstance().getReference("Users")
//            val key = database.push().key //generate unique key for the user
//            val user = User(username.text.toString(), pw.text.toString()) //pass the unique key to the User object constructor
//            database.child(key.toString()).setValue(user).addOnSuccessListener {
//                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener {
//                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show()
//            }
//          //   Check Firebase Realtime Database connection
//            FirebaseDatabase.getInstance().getReference(".info/connected")
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        val connected = snapshot.getValue(Boolean::class.java) ?: false
//                        if (connected) {
//                            Log.d("DATABASE", "Connected to Firebase")
//                        } else {
//                            Log.d("DATABASE", "Disconnected from Firebase")
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        Log.e("DATABASE", "Connection error: $error")
//                    }
//                })



            //login func
//            signIn()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signIn() {
        val username = findViewById<EditText>(R.id.Login_username_input).text.toString()
        val password = findViewById<EditText>(R.id.Login_password_Input).text.toString()
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            return
        }

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    val user = userSnapshot.getValue(User::class.java)
                    if (user?.username == username && user.pw == password) {
                        val userID = userSnapshot.key // get the userID from the snapshot
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        intent.putExtra("userID", userID) // pass the userID to MainActivity
                        startActivity(intent)
                        finish()
                        return
                    }
                }
                Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("DB CHECK", "onCancelled", databaseError.toException())
            }
        })
    }


}