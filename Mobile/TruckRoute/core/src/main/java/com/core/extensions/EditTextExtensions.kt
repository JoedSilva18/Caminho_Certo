package com.core.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.editorActionListener(
    targetAction: Int,
    hideKeyboard: Boolean = false,
    onTargetActionInvoked: () -> Unit
) {
    setOnEditorActionListener { _, action, _ ->
        if (targetAction == action) {
            if (hideKeyboard) {
                hideSoftInput()
            }
            onTargetActionInvoked()
        }
        true
    }
}
