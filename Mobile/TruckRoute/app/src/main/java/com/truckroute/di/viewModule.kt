package com.truckroute.di

import com.core.base.navigateTo
import com.home.views.HomeActivity
import com.home.views.HomeViewModel
import com.map.views.MapFragment
import com.map.views.MapViewModel
import com.splash.views.SplashActivity
import com.splash.views.SplashViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext


val viewModule = applicationContext {

    // SPLASH

    factory { SplashActivity() }

    bean {
        SplashViewModel()
    }

    viewModel { SplashViewModel() }
    // HOME

    bean { HomeViewModel() }

    factory(HomeActivity::class.java.canonicalName + "Screen") {
        HomeViewModel().navigateTo<HomeActivity>(it, get())
    }

    viewModel { HomeViewModel() }

    // MAP

    bean { MapViewModel() }

    viewModel { MapViewModel() }
}