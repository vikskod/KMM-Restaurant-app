package com.vikskod.myapplication.domain.usecase

import com.vikskod.myapplication.data.repository.AbstractRepository
import com.vikskod.myapplication.domain.util.ResponseHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetRestaurantUseCase(
    private val repository: AbstractRepository,
    private val responsehandler: ResponseHandler
) {
    operator fun invoke(page: Int, pageSize: Int = 20, country: String = "us") = flow {

        val response =
            repository.getAllRestaurants(page = page, pageSize = pageSize, country = country)

        emit(responsehandler.handleSuccess(response.data))

    }.catch {
        emit(
            responsehandler.handleException(
                ((it.message ?: it.cause) ?: "Something Went wrong.").toString()
            )
        )
    }
}