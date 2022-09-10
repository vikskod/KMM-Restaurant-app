package com.vikskod.myapplication.data.repository

import com.vikskod.myapplication.data.remote.dto.RestaurantDTO
import com.vikskod.myapplication.domain.util.DataState

abstract class AbstractRepository {

    abstract suspend fun getAllRestaurants(city: String, count: Int): DataState<RestaurantDTO>
}