package com.vikskod.myapplication.domain.di

import com.vikskod.myapplication.data.remote.service.AbstractKtorService
import com.vikskod.myapplication.data.remote.service.ImplKtorService
import com.vikskod.myapplication.data.repository.AbstractRepository
import com.vikskod.myapplication.data.repository.ImplRepository
import com.vikskod.myapplication.domain.usecase.GetRestaurantUseCase
import com.vikskod.myapplication.domain.util.ResponseHandler
import com.vikskod.myapplication.platformModule
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(
    enableNetworkLogs: Boolean = false,
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(commonModule(enableNetworkLogs = enableNetworkLogs))
    }

// for iOS
fun initKoin() = initKoin(enableNetworkLogs = true) {}

fun commonModule(enableNetworkLogs: Boolean) =
    getUseCaseModule() + getDateModule(enableNetworkLogs) + platformModule() + getHelperModule()

fun getHelperModule() = module {

    single {
        ResponseHandler()
    }
}

fun getDateModule(enableNetworkLogs: Boolean) = module {

    single<AbstractRepository> {
        ImplRepository(
            get()
        )
    }

    single<AbstractKtorService> {
        ImplKtorService(
            get()
        )
    }

    single { createJson() }

    single {
        createHttpClient(
            get(),
            get(),
            enableNetworkLogs = enableNetworkLogs
        )
    }


}

fun getUseCaseModule() = module {
    single {
        GetRestaurantUseCase(get(), get())
    }
}


fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) = HttpClient(httpClientEngine) {
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

