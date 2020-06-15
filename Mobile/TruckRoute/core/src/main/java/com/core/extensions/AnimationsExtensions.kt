package com.core.extensions

import android.view.View
import android.view.animation.AlphaAnimation

fun View.fadeIn(durationMillis: Long = 1000) {
    this.visibility = View.VISIBLE
    this.startAnimation(AlphaAnimation(0F, 1F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

fun View.fadeOut(durationMillis: Long = 1000) {
    this.visibility = View.GONE
    this.startAnimation(AlphaAnimation(1F, 0F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

fun View.fadeOutInvisible(durationMillis: Long = 1000) {
    this.visibility = View.INVISIBLE
    this.startAnimation(AlphaAnimation(1F, 0F).apply {
        duration = durationMillis
        fillAfter = true
    })
}

fun View.fadeCondition(condition: Boolean, isSetInvisible: Boolean = false) {
    if (condition) {
        this.fadeIn()
    } else {
        if (isSetInvisible)
            this.fadeOutInvisible()
        else
            this.fadeOut()
    }
}