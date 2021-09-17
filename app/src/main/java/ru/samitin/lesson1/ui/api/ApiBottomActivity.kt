package ru.samitin.lesson1.ui.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.samitin.lesson1.R

class ApiBottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_bottom)
        val bottomNV: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNV.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarchFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, WeatherFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
        bottomNV.selectedItemId = R.id.bottom_view_earth

    }

}



