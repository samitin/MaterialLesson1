package ru.samitin.lesson1.ui.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.samitin.lesson1.R
import ru.samitin.lesson1.databinding.ActivityApiBinding

class ApiActivity : AppCompatActivity() {

    private lateinit var bilder:ActivityApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bilder= ActivityApiBinding.inflate(layoutInflater)
        setContentView(bilder.root)

        bilder.viewPager.adapter=ViewPagerAdapter(supportFragmentManager)
        bilder.tabLayout.setupWithViewPager(bilder.viewPager)
        bilder.tabLayout.apply {
            getTabAt(0)?.setIcon(R.drawable.ic_earth)
            getTabAt(1)?.setIcon(R.drawable.ic_mars)
            getTabAt(2)?.setIcon(R.drawable.ic_system)
        }
    }
}