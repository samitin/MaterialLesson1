package ru.samitin.lesson1.ui.api


import android.os.Bundle
import android.view.Gravity

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*

import ru.samitin.lesson1.databinding.ActivityAnimationsEnlargeBinding
import ru.samitin.lesson1.databinding.ActivityAnimationsPathTransitionsBinding


class AnimationsActivity : AppCompatActivity() {

    private var toRightAnimation = false

    var _binding:ActivityAnimationsPathTransitionsBinding ?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityAnimationsPathTransitionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val changeBounds=ChangeBounds()
            changeBounds.setPathMotion(ArcMotion())
            changeBounds.duration=500
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,changeBounds)
            toRightAnimation= !toRightAnimation
            val params=binding.button.layoutParams as FrameLayout.LayoutParams
            params.gravity= if(toRightAnimation)Gravity.RIGHT or Gravity.BOTTOM else Gravity.START or Gravity.TOP
            binding.button.layoutParams=params
        }
    }



}