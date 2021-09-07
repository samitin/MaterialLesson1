package ru.samitin.lesson1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.samitin.lesson1.R
import ru.samitin.lesson1.view.picture.PODFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction().
                    replace(R.id.container,PODFragment.newInstance())
                .commitNow()
        }
    }
}