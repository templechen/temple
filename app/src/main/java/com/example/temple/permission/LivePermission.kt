package com.example.temple.permission

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData

class LivePermission {

    constructor(activity: AppCompatActivity) {
        getInstance(activity.supportFragmentManager)
    }

    constructor(fragment: Fragment) {
        getInstance(fragment.childFragmentManager)
    }

    @Volatile
    private lateinit var permissionFragment: PermissionFragment

    private fun getInstance(fragmentManager: FragmentManager) {
        if (!this::permissionFragment.isInitialized) {
            permissionFragment = PermissionFragment()
        }
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            fragmentManager.beginTransaction().add(permissionFragment, TAG).commitNow()
        }
    }

    fun request(vararg permissions: String): MutableLiveData<PermissionResult> {
        permissionFragment.requestPermissions(permissions)
        return permissionFragment.liveData
    }

    companion object {
        private const val TAG = "TEMPLE_PERMISSION_FRAGMENT_TAG"
    }

}