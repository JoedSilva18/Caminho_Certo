package com.core.extensions

import android.util.TypedValue
import android.view.View


inline fun <T1, R> safeLet(
    p1: T1?,
    block: (T1) -> R
): R? {
    return p1?.let { safeP1 ->
        block(safeP1)
    }
}

inline fun <T1, T2, R> safeLet(
    p1: T1?,
    p2: T2?,
    block: (T1, T2) -> R
): R? {
    return p1?.let { safeP1 ->
        p2?.let { safeP2 ->
            block(safeP1, safeP2)
        }
    }
}

fun View.dpToPixels(dpSize: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpSize, resources.displayMetrics)
