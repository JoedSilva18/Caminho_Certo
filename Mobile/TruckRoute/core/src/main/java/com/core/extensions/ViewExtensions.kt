package com.core.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.DimenRes

fun View.getDimen(@DimenRes dimenId: Int) = resources.getDimensionPixelSize(dimenId)

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

var TextView.compoundDrawableLeft: Drawable?
    get() = compoundDrawables[0]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(
        value,
        null,
        null,
        null
    )

var TextView.compoundDrawableRight: Drawable?
    get() = compoundDrawables[0]
    set(value) = setCompoundDrawablesRelativeWithIntrinsicBounds(
        null,
        null,
        value,
        null
    )

var View.present: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.hideSoftInput() {
    (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
        windowToken, 0)
}