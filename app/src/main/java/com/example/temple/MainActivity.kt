package com.example.temple

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.temple.permission.LivePermission
import com.example.temple.permission.PermissionResult
import com.example.temple.windowmanager.PipWindow

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        permissionTest()
        testWindowManager()
    }

    private fun permissionTest() {
        LivePermission(this)
            .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .observe(this, Observer {
                when (it) {
                    is PermissionResult.Grant -> {
                        Log.d(TAG, "permission granted")
                    }
                    is PermissionResult.Deny -> {
                        Log.d(TAG, "permission deny")
                    }
                    is PermissionResult.Rationale -> {
                        Log.d(TAG, "permission rationale")
                    }
                }
            })
    }

    private fun testWindowManager() {
        PipWindow().setUp(this)
    }
}
