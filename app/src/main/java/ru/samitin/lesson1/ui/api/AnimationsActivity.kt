package ru.samitin.lesson1.ui.api


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle

import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import ru.samitin.lesson1.databinding.ActivityAnimationsFabBinding



class AnimationsActivity : AppCompatActivity() {

    private var isExpanded = false

    var _binding:ActivityAnimationsFabBinding ?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityAnimationsFabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFAB()
    }

    private fun setFAB() {
        setInitialState()
        binding.fab.setOnClickListener {
            if (isExpanded)
                collapseFAB()
            else
                expendFAB()
        }
    }

    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha=0f
        }
        binding.optionOneContainer.apply {
            alpha=0f
            isClickable=false
        }
        binding.optionTwoContainer.apply {
            alpha=0f
            isClickable=false
        }
    }
    private fun expendFAB() {//развернуть
        isExpanded = true
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, 225f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", -130f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", -250f).start()

        binding.optionTwoContainer.animate()
            .alpha(1f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 2", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        Toast.makeText(this@AnimationsActivity, "Option 1", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFAB() {//свернуть
        isExpanded = false
        ObjectAnimator.ofFloat(binding.plusImageview, "rotation", 0f, -180f).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, "translationY", 0f).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, "translationY", 0f).start()

        binding.optionTwoContainer.animate()
            .alpha(0f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)//непрозрачность
            .setDuration(300)//продолжительность анимации
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }

}