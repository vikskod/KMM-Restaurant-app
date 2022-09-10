package com.vikskod.myapplication.data.remote.service

import com.vikskod.myapplication.data.remote.dto.RestaurantDTO
import com.vikskod.myapplication.domain.util.DataState

abstract class AbstractKtorService {

    abstract suspend fun getRestaurants(city: String): DataState<RestaurantDTO>

}

