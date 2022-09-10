package com.vikskod.myapplication.presentation

import com.vikskod.myapplication.data.remote.dto.RestaurantDTO

data class RestaurantListState(
    val isLoading: Boolean = true,
    val allRestaurantData: RestaurantDTO? = null,
    val error: Error = Error(),
    val isSuccess: Boolean = false,
    val city: String = "Sydney"
)

data class Error(
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong."
)