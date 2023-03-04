package com.lahrachtech.minixmlprojectloginsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to your splash screen layout
        setContentView(R.layout.activity_intro)

        // Add any necessary code to initialize your app here

        // Start your main activity after a delay of a few seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000) // Change 3000 to the desired delay time in milliseconds
    }
}
