package ru.samitin.lesson1.ui.api


import android.os.Bundle
import android.view.Gravity

import android.view.ViewGroup

import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.transition.*

import ru.samitin.lesson1.databinding.ActivityAnimationsShuffleBinding


class AnimationsActivity : AppCompatActivity() {

    private var toRightAnimation = false

    var _binding:ActivityAnimationsShuffleBinding ?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityAnimationsShuffleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titles:MutableList<String> = ArrayList()
        for (i in 0..4){
            titles.add(String.format("item %d",i+1))
        }
        createViews(binding.transitionsContainer,titles)

        binding.button.setOnClickListener {
            val changeBounds=ChangeBounds()
            changeBounds.duration=1000//Время анимации
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,changeBounds)
            titles.shuffle()//перемешать
            createViews(binding.transitionsContainer,titles)
        }
    }

    private fun createViews(layout: ViewGroup, titles: List<String>) {
        layout.removeAllViews()//Очищает контейнер
        for (title in titles){
            val textView=TextView(this)
            textView.text=title
            textView.gravity=Gravity.CENTER_HORIZONTAL
            ViewCompat.setTransitionName(textView,title)
            layout.addView(textView)
        }
    }


}