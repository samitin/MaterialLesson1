package ru.samitin.lesson1.utils

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

private var isExpanded=false
fun zoomImageView(view: ImageView){
    isExpanded= !isExpanded
    TransitionManager.beginDelayedTransition(
        view.parent as ViewGroup,
        TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
    )
    val params: ViewGroup.LayoutParams=view.layoutParams
    params.height=if(isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
    view.layoutParams=params
    view.scaleType = if (isExpanded) ImageView.ScaleType.CENTER_CROP else ImageView.ScaleType.FIT_CENTER
}
