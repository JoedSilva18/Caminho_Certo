package com.core.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import com.core.R
import com.core.extensions.compoundDrawableLeft
import com.core.extensions.getDimen
import com.google.android.material.R as MaterialR
import com.google.android.material.textfield.TextInputLayout as MaterialTextInputLayout

open class TextInputLayout : MaterialTextInputLayout {

    private var errorDrawable: Drawable? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.textInputStyle)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextInputLayout, defStyleAttr, 0)
        errorDrawable = typedArray.getDrawable(R.styleable.TextInputLayout_errorIcon)
        typedArray.recycle()
    }

    /* This isn't the best solution for many reasons, but produced better results */
    /* Issue: https://issuetracker.google.com/issues/130786164*/
    override fun setErrorEnabled(enabled: Boolean) {
        super.setErrorEnabled(enabled)

        if (!enabled) return

        val errorView = findViewById<TextView>(MaterialR.id.textinput_error)
        if (errorView != null && errorDrawable != null && errorView.compoundDrawables[0] == null) {
            with(errorView) {
                gravity = Gravity.CENTER_VERTICAL
                compoundDrawableLeft = errorDrawable
                compoundDrawablePadding = getDimen(R.dimen.text_input_layout_error_text_drawable_padding)
                setPadding(0, getDimen(R.dimen.text_input_layout_error_text_padding_top), 0, 0)
            }
        }
    }

    override fun setHelperTextEnabled(enabled: Boolean) {
        super.setHelperTextEnabled(enabled)

        if (!enabled) return

        val helperView = findViewById<TextView>(MaterialR.id.textinput_helper_text)
        if (helperView != null) {
            with(helperView) {
                setPadding(0, getDimen(R.dimen.text_input_layout_helper_text_padding_top), 0, 0)
            }
        }
    }
}
