package com.lahrachtech.minixmlprojectloginsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        loginRequest.setOnClickListener {
            val mainIntent = Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(mainIntent)
        }
        signupBtn.setOnClickListener{
            val mainIntent = Intent(this@SignUpActivity, DashboardActivity::class.java)
            startActivity(mainIntent)
        }
    }
}