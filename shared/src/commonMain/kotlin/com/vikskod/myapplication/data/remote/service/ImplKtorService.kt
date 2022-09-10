package com.vikskod.myapplication.data.remote.service

import com.vikskod.myapplication.data.remote.HttpRoutes.API_KEY
import com.vikskod.myapplication.data.remote.HttpRoutes.BASE_URL
import com.vikskod.myapplication.data.remote.dto.RestaurantDTO
import com.vikskod.myapplication.domain.util.DataState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*

class ImplKtorService(
    private val httpClient: HttpClient,
) : AbstractKtorService() {

    override suspend fun getRestaurants(city: String): DataState<RestaurantDTO> {

        return try {
            DataState.Success(httpClient.get("$BASE_URL") {
                url("search")
                header("user-key", API_KEY)
                parameter("entity_id", 56625527)
                parameter("entity_type", "available")
                parameter("q", city)
                parameter("start", 1)
                parameter("count", 1)
                parameter("sort", "available")
                parameter("order", "available")
            }.body())
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            DataState.Error(DataState.CustomMessages.ExceptionMessage(e.response.status.description))
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            DataState.Error(DataState.CustomMessages.ExceptionMessage(e.response.status.description))
        } catch (e: ServerResponseException) {
            // 5xx - responses
            DataState.Error(DataState.CustomMessages.ExceptionMessage(e.response.status.description))
        } catch (e: Exception) {
            println("Error: ${e.message}")
            DataState.Error(
                DataState.CustomMessages.ExceptionMessage(
                    e.message ?: "Something went wrong"
                )
            )
        }
    }
}