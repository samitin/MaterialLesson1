package ru.samitin.lesson1.ui.api

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*

import ru.samitin.lesson1.R

import ru.samitin.lesson1.databinding.ActivityAnimationsBinding
import ru.samitin.lesson1.databinding.ActivityAnimationsEnlargeBinding
import ru.samitin.lesson1.databinding.ActivityAnimationsExplodeBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder

class AnimationsActivity : AppCompatActivity() {

    private var isExpanded = false

    var _binding:ActivityAnimationsEnlargeBinding ?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityAnimationsEnlargeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            isExpanded= !isExpanded
            TransitionManager.beginDelayedTransition(
                binding.container,
                TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
            )
            val params:ViewGroup.LayoutParams=binding.imageView.layoutParams
            params.height=if(isExpanded)ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
            binding.imageView.layoutParams=params
            binding.imageView.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
        }
    }



}