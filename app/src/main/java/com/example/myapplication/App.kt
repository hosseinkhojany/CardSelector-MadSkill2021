package com.example.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    companion object{
        lateinit var appContext: Context
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appContext = baseContext
    }
}