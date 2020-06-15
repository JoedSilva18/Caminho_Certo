package com.truckroute

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.truckroute.di.dataModule
import com.truckroute.di.domainModule
import com.truckroute.di.viewModule
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


        // koin 1.0.0 will allow the application to load it's modules on activity creation
        // best approach would be to update it as soon as this new version gets stable
        // also, separate the modules by feature
        startKoin(
            this,
            listOf(
                dataModule,
                domainModule,
                viewModule
            )
        )
    }
}