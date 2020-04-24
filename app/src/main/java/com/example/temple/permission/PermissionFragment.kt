package com.example.temple.permission

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

class PermissionFragment : Fragment() {

    lateinit var liveData: MutableLiveData<PermissionResult>

    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissions(permissions: Array<out String>) {
        liveData = MutableLiveData()
        requestPermissions(permissions, REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            val denyPermissions = ArrayList<String>()
            val rationalePermissions = ArrayList<String>()
            for ((index, value) in grantResults.withIndex()) {
                if (value == PackageManager.PERMISSION_DENIED) {
                    if (shouldShowRequestPermissionRationale(permissions[index])) {
                        rationalePermissions.add(permissions[index])
                    } else {
                        denyPermissions.add(permissions[index])
                    }
                }
            }
            if (denyPermissions.isEmpty() && rationalePermissions.isEmpty()) {
                liveData.value = PermissionResult.Grant
            } else {
                if (rationalePermissions.isNotEmpty()) {
                    liveData.value = PermissionResult.Rationale(rationalePermissions)
                } else if (denyPermissions.isNotEmpty()) {
                    liveData.value = PermissionResult.Deny(denyPermissions)
                }
            }
        }
    }
}