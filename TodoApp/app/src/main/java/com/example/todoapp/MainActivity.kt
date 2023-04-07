package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TodoApp)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation=0f
        supportActionBar?.setLogo(R.drawable.icon_light)
    }
}