package com.example.temple.permission

sealed class PermissionResult {
    object Grant : PermissionResult()
    class Deny(val permissions: ArrayList<String>) : PermissionResult()
    class Rationale(val permissions: ArrayList<String>) : PermissionResult()
}