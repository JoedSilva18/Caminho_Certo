package com.core.base

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import android.location.LocationManager
import android.location.Location
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: ViewModel
    private val onDestroyLiveDataBag = mutableListOf<LiveData<*>>()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        with(onDestroyLiveDataBag) {
            forEach { it.removeObservers(this@BaseActivity) }
            clear()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    open fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }

        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            else
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT > 22) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        } else {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
    }

    fun setActionBarLeftButtonIcon(@DrawableRes resId: Int) {
        supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this, resId))
    }

    fun setBackOnActionBarEnabled(isEnabled: Boolean) {
        supportActionBar?.apply {
            setHomeButtonEnabled(isEnabled)
            setDisplayHomeAsUpEnabled(isEnabled)
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun canRequestPermissionNextTime(permissionType: String): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ->
                shouldShowRequestPermissionRationale(permissionType)
            else -> true
        }
    }

    fun permissionIsGranted(permissionType: String): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ->
                checkSelfPermission(permissionType) == PackageManager.PERMISSION_GRANTED

            else -> true
        }
    }

    fun doPermissionRequest(vararg permissions: String, requestCode: Int = 1) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    fun LiveData<*>.removeObserversOnDestroy() {
        onDestroyLiveDataBag.add(this)
    }
}