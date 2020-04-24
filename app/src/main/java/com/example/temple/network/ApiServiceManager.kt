package com.example.temple.network

import retrofit2.Retrofit

object ApiServiceManager {

    init {
        Retrofit.Builder()
            .baseUrl("")
            .build()
    }

}