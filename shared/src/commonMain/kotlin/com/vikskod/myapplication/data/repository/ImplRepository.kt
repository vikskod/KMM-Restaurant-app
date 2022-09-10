package com.vikskod.myapplication.data.repository

import com.vikskod.myapplication.data.remote.service.AbstractKtorService


class ImplRepository(
    private val ktorService: AbstractKtorService
) : AbstractRepository() {

    override suspend fun getAllRestaurants(city: String, count: Int) =
        ktorService.getRestaurants(city = city, count = count)

}