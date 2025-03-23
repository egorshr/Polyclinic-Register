package com.example.polyclinicregister

import android.app.Application
import com.example.polyclinicregister.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PolyclinicRegisterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PolyclinicRegisterApp)
            modules(appModule())
        }
    }
}