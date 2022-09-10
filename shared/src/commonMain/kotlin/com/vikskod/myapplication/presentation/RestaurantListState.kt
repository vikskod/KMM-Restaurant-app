package com.vikskod.myapplication.presentation

import com.vikskod.myapplication.data.remote.dto.Restaurant

data class RestaurantListState(
    val isLoading: Boolean = true,
    val allRestaurantData: List<Restaurant> = emptyList(),
    val error: Error = Error(),
    val isSuccess: Boolean = false,
    val page: Int = 1
)

data class Error(
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong."
)