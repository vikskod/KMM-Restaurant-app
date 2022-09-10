package com.vikskod.myapplication

import com.vikskod.myapplication.presentation.RestaurantListViewModel
import org.koin.dsl.module
import io.ktor.client.engine.android.*
import org.koin.androidx.viewmodel.dsl.viewModel

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun platformModule() = module {
    single {
        Android.create()
    }

    /**
     *
     * for android koin has a special viewmodel scope that we can use
     * to create a viewmodel
     *
     */

    viewModel {
        RestaurantListViewModel(
            get()
        )
    }
}