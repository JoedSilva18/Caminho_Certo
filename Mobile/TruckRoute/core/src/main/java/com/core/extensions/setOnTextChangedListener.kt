package com.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText

fun EditText.setOnTextChangedListener(
    beforeTextChangeBlock: (String) -> Unit = {},
    afterTextChangeBlock: (String) -> Unit = {},
    textChangedBlock: (String) -> Unit
): TextWatcher {
    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChangeBlock(s.toString())
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            textChangedBlock(s.toString())
        }

        override fun afterTextChanged(s: Editable) {
            afterTextChangeBlock(s.toString())
        }
    }

    addTextChangedListener(watcher)
    return watcher
}

fun TextInputEditText.setTextKeepingCursorAtTheEnd(text: String, textWatcher: TextWatcher) {
    removeTextChangedListener(textWatcher)
    setText(text)
    setSelection(text.length)
    addTextChangedListener(textWatcher)
}

fun EditText.setSequentialHandler(
    previous: EditText? = null,
    next: EditText? = null,
    maxLength: Int,
    onTextChanged: (String) -> Unit
): TextWatcher {
    val verifyText = { text: String, textChanged: Boolean ->
        when {
            textChanged && text.length >= maxLength -> {
                next?.also {
                    it.requestFocus()
                    it.setSelection(0)
                }
            }
            selectionEnd == 0 && (text.isEmpty() || !textChanged) -> {
                previous?.also {
                    it.requestFocus()
                    it.setSelection(it.text.length)
                }
            }
            else -> {
            }
        }
    }

    setOnKeyListener { _, keyCode, event ->
        val isDelete = keyCode == KeyEvent.KEYCODE_DEL || keyCode == KeyEvent.KEYCODE_FORWARD_DEL

        if (event.action == KeyEvent.ACTION_UP && isDelete && selectionEnd == 0) {
            previous?.also {
                it.requestFocus()
                it.setSelection(it.text.length)
            }
        }

        false
    }

    return setOnTextChangedListener { content ->
        verifyText(content, true)

        if (maxLength > 0) {
            when {
                selectionEnd <= 1 && content.length > maxLength -> {
                    setText(content.substring(1, text.length))
                    setSelection(0)
                }

                selectionEnd >= maxLength -> {
                    setText(content.substring(0, maxLength))
                    setSelection(maxLength)
                }
            }
        }

        onTextChanged(text.toString())
    }
}

fun List<EditText>.setSequentialTextHandler(
    maxLength: Int,
    separator: String = "",
    onTextChanged: (String) -> Unit
): List<TextWatcher> {
    return map { it to maxLength }.setSequentialTextHandler(separator, onTextChanged)
}

fun List<Pair<EditText, Int>>.setSequentialTextHandler(
    separator: String = "",
    onTextChanged: (String) -> Unit
): List<TextWatcher> {
    val handleTextChanged = {
        val result =
            map { (editText) -> editText.text.toString() }
                .toMutableList()
                .apply { add("") }
                .reduce { acc, text -> "$acc$separator$text" }

        onTextChanged(result)
    }

    return mapIndexed { index, item ->
        val (editText, maxLength) = item

        editText.setSequentialHandler(
            previous = getOrNull(index - 1)?.first,
            next = getOrNull(index + 1)?.first,
            maxLength = maxLength,
            onTextChanged = { handleTextChanged() }
        )
    }
}

fun List<EditText>.setOnEditorActionListener(listener: (actionId: Int) -> Boolean) {
    forEach {
        it.setOnEditorActionListener { _, actionId, _ ->
            listener(actionId)
        }
    }
}

fun EditText.setHintColor(@ColorRes resColor: Int) {
    setHintTextColor(
        ContextCompat.getColor(
            this.context,
            resColor
        )
    )
}