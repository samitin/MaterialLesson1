package ru.samitin.lesson1.ui.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.samitin.lesson1.R

class ApiBottomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_bottom)

        val bnView : BottomNavigationView= findViewById(R.id.bottom_navigation_view)
        bnView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_view_earth -> true
                R.id.bottom_view_mars -> true
                R.id.bottom_view_weather -> true
                else -> false
            }
        }
    }
}





