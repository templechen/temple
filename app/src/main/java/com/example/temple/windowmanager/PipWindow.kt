package com.example.temple.windowmanager

import android.Manifest
import android.content.Context
import android.graphics.PixelFormat
import android.view.SurfaceView
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.temple.permission.LivePermission
import com.example.temple.permission.PermissionResult

class PipWindow {

    fun setUp(activity: AppCompatActivity, width: Int = 192, height: Int = 108) {
        LivePermission(activity).request(Manifest.permission.SYSTEM_ALERT_WINDOW)
            .observe(activity, Observer {
                if (it is PermissionResult.Grant) {
                    val layoutParams =
                        WindowManager.LayoutParams(width, height, 0, 0, PixelFormat.TRANSPARENT)
                    layoutParams.flags =
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE.or(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
                    val surfaceView = SurfaceView(activity)
                    val windowManager =
                        activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    windowManager.addView(surfaceView, layoutParams)
                }
            })
    }

}