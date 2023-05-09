package com.example.assignment_test

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private var username: String? = null
    private var password: String? = null
    lateinit var etusername: EditText
    lateinit var etPassword: EditText
    private val URL :String = "http://10.0.2.2/test/login.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etusername =  findViewById(R.id.Login_username_input)
        etPassword =  findViewById(R.id.Login_password_Input)

        val loginButton: Button =  findViewById(R.id.login_button)
        loginButton.setOnClickListener {
             login()
//login function require xamp & php files to work, only uncomment this if u hv the files
//            val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }


    fun login() {
        var username = etusername.getText().toString().trim()
        var password = etPassword.getText().toString().trim()
        System.out.println(username)
        System.out.println(password)

        if (username != "" && password != "") {
            val stringRequest: StringRequest = object : StringRequest(
                Request.Method.POST, URL,
                Response.Listener {response ->
                    val jsonResponse = JSONObject(response)
                    val message = jsonResponse.getString("message")
                    Log.d("my msg", message)
                    Log.d("this is msg", response)
                    if (message == "login success") {
                        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
                        intent.putExtra("username", username)
                        saveUsername(username)
                        startActivity(intent)
                        finish()
                    } else if (response == "failure") {
                        Toast.makeText(
                            this@LoginActivity,
                            "Invalid Login Id/Password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                        this@LoginActivity,
                        error.toString().trim { it <= ' ' },
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String>? {
                    val data: MutableMap<String, String> = HashMap()
                    data["username"] = username
                    data["password"] = password
                    return data
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(stringRequest)
        } else {
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUsername(username: String) {
        val sharedPref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("username", username)
            apply()
        }
    }
}