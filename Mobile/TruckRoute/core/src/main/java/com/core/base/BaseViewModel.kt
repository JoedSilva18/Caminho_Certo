package com.core.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.core.extensions.getBundleExtras
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.context.ParameterProvider
import org.koin.standalone.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent, CoroutineScope {
    private val job = Job()

    override val coroutineContext = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

inline fun <reified T : BaseActivity<*>> BaseViewModel.navigateTo(
    parameterProvider: ParameterProvider,
    context: Context
) =
    context.startActivity(Intent(context, T::class.java).apply {
        putExtras(parameterProvider.getBundleExtras())
    })

inline fun <reified T : BaseActivity<*>> BaseViewModel.navigateTo(
    parameterProvider: ParameterProvider,
    context: Context,
    transition: Bundle?
) =
    context.startActivity(Intent(context, T::class.java).apply {
        putExtras(parameterProvider.getBundleExtras())
    }, transition)

inline fun <reified T : BaseActivity<*>> BaseViewModel.navigateTo(context: Context) =
    context.startActivity(Intent(context, T::class.java))

inline fun <reified T : BaseActivity<*>> BaseViewModel.navigateTo(context: Context, intent: Intent) =
    context.startActivity(intent)

fun BaseViewModel.navigateToForResult(activity: Activity, intent: Intent, code: Int) =
    activity.startActivityForResult(intent, code)