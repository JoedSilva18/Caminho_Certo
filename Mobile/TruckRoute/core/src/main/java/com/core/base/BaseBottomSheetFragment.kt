package com.core.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.CallSuper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                activity?.checkSelfPermission(permissionType) == PackageManager.PERMISSION_GRANTED

            else -> true
        }
    }

    fun doPermissionRequest(vararg permissions: String, requestCode: Int = 1) {
        requestPermissions(permissions, requestCode)
    }
}