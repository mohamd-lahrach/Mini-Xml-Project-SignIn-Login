package com.lahrachtech.minixmlprojectloginsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_branche.*

class BrancheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branche)
        val items = arrayOf("Development digital", "gestation", "networking", "finance", "economy")

// Create an ArrayAdapter to hold the items and set it as the adapter for the AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        autoCompleteTextView.setAdapter(adapter)

    }
}