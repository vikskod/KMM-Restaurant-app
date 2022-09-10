package com.vikskod.myapplication.android

import android.app.Application
import com.vikskod.myapplication.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


class RestaurantApp : Application(){

    override fun onCreate() {
        super.onCreate()

        initKoin(enableNetworkLogs = BuildConfig.DEBUG) {
            androidContext(this@RestaurantApp)
            // androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.INFO)
            modules(
                listOf(module {
                    /**
                     * android specific modules
                     */
                })
            )
        }
    }
}