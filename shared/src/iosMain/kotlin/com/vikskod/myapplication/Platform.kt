package com.vikskod.myapplication

import com.vikskod.myapplication.presentation.RestaurantListViewModel
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    //single or factory can be used to get a view-model object for swiftui

    single {
        RestaurantListViewModel(get())
    }
}


/**
 * viewmodels object implements koin component thus its able to get any
 * dependency that is listed in platform module we can call getRestaurantListViewModel()
 * in swift ui to get an object of HomeViewModel
 */
object ViewModels : KoinComponent {
    fun getRestaurantListViewModel() = get<RestaurantListViewModel>()
}