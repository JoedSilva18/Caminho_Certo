package com.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
): LiveData<T> {
    observe(owner, Observer<T> { result -> result?.let { observer(result) } })
    return this
}