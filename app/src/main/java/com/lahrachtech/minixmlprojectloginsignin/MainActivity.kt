package com.lahrachtech.minixmlprojectloginsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signUp.setOnClickListener{
            val mainIntent = Intent(this@MainActivity,SignUpActivity::class.java)
            startActivity(mainIntent)

        }
        loginBtn.setOnClickListener{
            val mainIntent = Intent(this@MainActivity,DashboardActivity::class.java)
            startActivity(mainIntent)
        }
    }
}