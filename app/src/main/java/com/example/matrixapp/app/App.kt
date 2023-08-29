package com.example.matrixapp.app

import android.app.Application
import android.content.Context




class App : Application() {
    companion object{
        lateinit var dm: DataManager
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        dm = DataManager(baseContext)
        dm.initEncryptedSharedPrefs(baseContext)
        appContext = applicationContext
    }
}