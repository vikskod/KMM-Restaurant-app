package com.vikskod.myapplication.domain.domainmodel

import com.vikskod.myapplication.data.remote.dto.Location

data class RestaurantDomainModel(
    val featuredImage: String,
    val id: String,
    val location: Location,
    val name: String
)