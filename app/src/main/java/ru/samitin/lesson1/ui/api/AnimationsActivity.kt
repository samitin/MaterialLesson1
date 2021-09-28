package ru.samitin.lesson1.ui.api

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*

import ru.samitin.lesson1.R

import ru.samitin.lesson1.databinding.ActivityAnimationsBinding
import ru.samitin.lesson1.databinding.ActivityAnimationsExplodeBinding
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder

class AnimationsActivity : AppCompatActivity() {

    private var textIsVisible = false

    var _binding:ActivityAnimationsExplodeBinding ?=null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityAnimationsExplodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter=Adapter()
    }

    private fun explode(clickedView:View){
        val viewRect=Rect()
        clickedView.getGlobalVisibleRect(viewRect)
        val explode=Explode()
        explode.epicenterCallback=object : Transition.EpicenterCallback(){
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.excludeTarget(clickedView,true)
        val set=TransitionSet()
            .addTransition(explode)
            .addTransition(Fade().addTarget(clickedView)).addListener(object : TransitionListenerAdapter(){
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                     onBackPressed()
                }
            })
        explode.duration=2000
        TransitionManager.beginDelayedTransition(binding.recyclerView,set)
        binding.recyclerView.adapter=null
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_recycle_view_item,
                    parent,
                    false) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener{
                explode(it)
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}