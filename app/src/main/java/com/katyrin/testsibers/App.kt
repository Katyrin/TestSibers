package com.katyrin.testsibers

import android.app.Application
import com.katyrin.testsibers.di.application
import com.katyrin.testsibers.di.database
import com.katyrin.testsibers.di.homeModule
import com.katyrin.testsibers.di.network
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, homeModule, network, database))
        }
    }
}