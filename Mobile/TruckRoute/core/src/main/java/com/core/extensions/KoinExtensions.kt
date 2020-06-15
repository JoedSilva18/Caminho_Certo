package com.core.extensions

import android.os.Bundle
import org.koin.core.parameter.Parameters
import org.koin.dsl.context.ParameterHolder
import org.koin.dsl.context.ParameterProvider

const val KOIN_EXTRAS = "extras"


fun ParameterProvider.getBundleExtras(): Bundle {
    return getOrNUll<Bundle>(KOIN_EXTRAS) ?: Bundle()
}

val Parameters.bundle
    get() = (this()[KOIN_EXTRAS] as? Bundle) ?: Bundle()

val Bundle.koinParameters: ParameterProvider
    get() = ParameterHolder { mapOf<String, Any>(KOIN_EXTRAS to this) }