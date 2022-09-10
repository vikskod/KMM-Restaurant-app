package com.vikskod.myapplication.presentation

sealed class RestaurantListSideEffects {
    object GetRestaurants : RestaurantListSideEffects()
}